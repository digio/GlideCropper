package au.com.digio.glidecropper.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.bitmap.Downsampler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CroppedBitmapDecoder implements ResourceDecoder<CroppedImageDecoderInput, Bitmap> {

    private Context context;

    public CroppedBitmapDecoder(Context context) {
        this.context = context;
    }

    @Override
    public boolean handles(@NonNull CroppedImageDecoderInput source, @NonNull Options options) throws IOException {
        return true;
    }


    @Nullable
    @Override
    public Resource<Bitmap> decode( @NonNull CroppedImageDecoderInput source,
                                            int width,
                                            int height,
                                            @NonNull Options options) throws IOException {

        Bitmap bitmap;
        BitmapRegionDecoder decoder = null;
        InputStream inputStream = null;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;

        // You may want to consider using a bitmap cache if your Use Case requires it -
        // see Glide's Downsampler.java for implementation details.

        try {
            inputStream = context.getContentResolver().openInputStream(source.uri);
            BitmapFactory.decodeStream(inputStream, null, bitmapOptions);
        } catch (FileNotFoundException e) {
            // do something
        }

        // In following: ensure the cropping region doesn't exceed the image dimensions
        int horizontalOffset = source.horizontalOffset;
        int verticalOffset = source.verticalOffset;
        int imageWidth = bitmapOptions.outWidth;
        int imageHeight = bitmapOptions.outHeight;
        int croppedWidth = source.viewWidth;
        int croppedHeight = source.viewHeight;

        if (croppedWidth > imageWidth) {
            croppedWidth = imageWidth;
        }
        if (croppedHeight > imageHeight) {
            croppedHeight = imageHeight;
        }

        // From here it's sure that: croppedWidth <= imageWidth, and croppedHeight <= imageHeight
        if (croppedWidth + horizontalOffset > imageWidth){
            horizontalOffset = 0;
        }
        if (croppedHeight + verticalOffset > imageHeight){
            verticalOffset = 0;
        }

        int desCroppedWidth = source.desImageWidth;
        int desCroppedHeight = 0;
        if(desCroppedWidth == croppedWidth){
            desCroppedHeight = croppedHeight;
        }
        desCroppedHeight = (int) (((float) desCroppedWidth / croppedWidth) * croppedHeight);

        if (desCroppedWidth > imageWidth) {
            desCroppedWidth = imageWidth;
        }
        if (desCroppedHeight > imageHeight) {
            desCroppedHeight = imageHeight;
        }

        int desImageWidth = (int) (((float) imageWidth / croppedWidth) * desCroppedWidth);
        int desImageHeight = (int) (((float) imageHeight / croppedHeight) * desCroppedHeight);

        if (desImageWidth > imageWidth) {
            desImageWidth = imageWidth;
        }
        if (desImageHeight > imageHeight) {
            desImageHeight = imageHeight;
        }

//        int desHorizontalOffset = (int) (((float) horizontalOffset / imageWidth) * desImageWidth);
//        int desVerticalOffset = (int) (((float) verticalOffset / imageHeight) * desImageHeight);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = calculateInSampleSize(bitmapOptions, desImageWidth,
                desImageHeight);


        bitmapOptions.inScaled = true;
        bitmapOptions.inDensity = croppedWidth;
        bitmapOptions.inTargetDensity = desCroppedWidth * bitmapOptions.inSampleSize;

        try {
            // Note: exact resizing using (inScaled, inDensity, inTargetDensity) is not supported by
            // BitmapRegionDecoder. However, inSampleSize is used to scale down to 1/2, 1/4, 1/8 of
            // the cropped region directly
            inputStream = context.getContentResolver().openInputStream(source.uri);
            decoder = BitmapRegionDecoder.newInstance(inputStream, false);

            Rect region = new Rect(horizontalOffset, verticalOffset,
                    horizontalOffset + croppedWidth,
                    verticalOffset + croppedHeight);

            // Decode image content within the cropping region
            bitmap = decoder.decodeRegion(region, bitmapOptions);
            bitmap = Bitmap.createScaledBitmap(bitmap, desCroppedWidth, desCroppedHeight, true);
        } finally {
            inputStream.close();
            decoder.recycle();
        }

        return new SimpleResource<>(bitmap);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int newWidth, int newHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > newHeight || width > newWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= newHeight
                    && (halfWidth / inSampleSize) >= newWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
