package au.com.digio.glidecropper.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;

import java.io.IOException;
import java.io.InputStream;

public class CroppedBitmapDecoder2 implements ResourceDecoder<CroppedImageDecoderInput2, BitmapDrawable> {

    private Resources resources;

    public CroppedBitmapDecoder2(Resources resources) {
        this.resources = resources;
    }

    @Override
    public boolean handles(@NonNull CroppedImageDecoderInput2 source, @NonNull Options options) throws IOException {
        return true;
    }

    @Nullable
    @Override
    public Resource<BitmapDrawable> decode( @NonNull CroppedImageDecoderInput2 source,
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

        // Determine the image's height and width
        BitmapFactory.decodeResource(resources, source.resId, bitmapOptions);
        float imageHeight = bitmapOptions.outHeight;
        float imageWidth = bitmapOptions.outWidth;

        // This results in a bitmap that is exactly sized to the ImageView’s height and width,
        // rather than the much larger image file’s height and width for the normal decoder
        // (or downsampled if required — see Glide’s Downsampler.java for details)

        try {
            inputStream = resources.openRawResource(source.resId);
            decoder = BitmapRegionDecoder.newInstance(inputStream, false);

            // Ensure the cropping and translation region doesn't exceed the image dimensions
            Rect region = new Rect(source.horizontalOffset,
                    source.verticalOffset,
                    (int) Math.min(source.viewWidth + source.horizontalOffset, imageWidth),
                    (int) Math.min(source.viewHeight + source.verticalOffset, imageHeight));

            // Decode image content within the cropping region
            bitmapOptions.inJustDecodeBounds = false;
            bitmap = decoder.decodeRegion(region, bitmapOptions);
        } finally {
            inputStream.close();
            decoder.recycle();
        }

        BitmapDrawable drawable = new BitmapDrawable(resources, bitmap);
        return new SimpleResource<BitmapDrawable>(drawable);
    }
}
