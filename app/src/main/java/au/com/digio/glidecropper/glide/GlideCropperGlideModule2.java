package au.com.digio.glidecropper.glide;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

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
public class GlideCropperGlideModule2 extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.prepend(CroppedImage2.class, CroppedImageDecoderInput2.class,
                new CroppedImageModelLoaderFactory2(context.getResources()))

                .prepend(CroppedImageDecoderInput2.class, BitmapDrawable.class,
                new CroppedBitmapDecoder2(context.getResources()));
    }
}
