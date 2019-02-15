package au.com.digio.glidecropper.glide

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import com.bumptech.glide.load.*
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import java.io.InputStream

/**
 * Glide bitmap decoder that creates a smaller bitmap based on the view's dimensions, instead of using an image
 * transformation. This bypasses the creation of two bitmaps and reduces memory usage.
 */
class CroppedBitmapDecoder(val resources: Resources): ResourceDecoder<CroppedImageDecoderInput, BitmapDrawable> {

    override fun handles(source: CroppedImageDecoderInput, options: Options): Boolean {
        return true
    }

    override fun decode(source: CroppedImageDecoderInput, width: Int, height: Int, options: Options): Resource<BitmapDrawable>? {
        val bitmap: Bitmap
        var decoder: BitmapRegionDecoder? = null
        var inputStream: InputStream? = null

        val bitmapFactoryOptions = BitmapFactory.Options().apply {
            // Decode image dimensions only, not content
            inJustDecodeBounds = true
        }

        // You may want to consider using a bitmap cache if your Use Case requires it -
        // see Glide's Downsampler.java for implementation details.

        // Determine the image's height and width
        BitmapFactory.decodeResource(resources, source.resId, bitmapFactoryOptions)
        val imageHeight = bitmapFactoryOptions.outHeight
        val imageWidth = bitmapFactoryOptions.outWidth

        try {
            inputStream = resources.openRawResource(source.resId)
            decoder = BitmapRegionDecoder.newInstance(inputStream, false)

            // Ensure the cropping and translation region doesn't exceed the image dimensions
            val region = Rect(source.horizontalOffset,
                              source.verticalOffset,
                              Math.min(source.viewWidth + source.horizontalOffset, imageWidth),
                              Math.min(source.viewHeight + source.verticalOffset, imageHeight))

            // Decode image content within the cropping region
            bitmapFactoryOptions.inJustDecodeBounds = false
            bitmap = decoder!!.decodeRegion(region, bitmapFactoryOptions)
        } finally {
            inputStream?.close()
            decoder?.recycle()
        }

        val drawable = BitmapDrawable(resources, bitmap)
        return SimpleResource<BitmapDrawable>(drawable)
    }
}