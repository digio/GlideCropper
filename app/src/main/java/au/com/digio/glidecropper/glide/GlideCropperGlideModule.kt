package au.com.digio.glidecropper.glide

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import com.bumptech.glide.*
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Glide configuration for the GlideCropper app.
 *
 * Firstly we register a custom model - CroppedImage so that cropping and translation only happen for
 * this type. Regular images will continue to be loaded by Glide with normal image processing.
 *
 * Secondly we register the custom input - CroppedImageDecoderInput, as the default input types of InputStream or
 * ByteBuffer do not convey enough context (such as crop dimensions or translation offsets) for our custom decoder.
 *
 * CroppedImageModelLoader, CroppedImageModelLoaderFactory, CroppedImageDataFetcher are all mostly boilerplate but
 * necessary because Glide does not provide an easy to add a custom decoder, unlike adding a custom transformation
 * for example.
 */
@GlideModule
class GlideCropperGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(CroppedImage::class.java,
                         CroppedImageDecoderInput::class.java,
                         CroppedImageModelLoaderFactory(context.resources))
                .prepend(CroppedImageDecoderInput::class.java,
                         BitmapDrawable::class.java,
                         CroppedBitmapDecoder(context.resources))
    }
}