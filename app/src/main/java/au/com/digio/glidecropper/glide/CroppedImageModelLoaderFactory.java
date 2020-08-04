package au.com.digio.glidecropper.glide;


import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

/**
 * Glide model loader factory for Cropped Image Model loaders.
 */

public class CroppedImageModelLoaderFactory implements ModelLoaderFactory<CroppedImage, CroppedImageDecoderInput> {

    private Resources resources;

    public CroppedImageModelLoaderFactory(Resources resources){
        this.resources = resources;
    }

    @NonNull
    @Override
    public ModelLoader<CroppedImage, CroppedImageDecoderInput> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new CroppedImageModelLoader(resources);
    }

    @Override
    public void teardown() {

    }
}
