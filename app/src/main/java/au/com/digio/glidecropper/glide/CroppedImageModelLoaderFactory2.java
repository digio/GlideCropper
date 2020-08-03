package au.com.digio.glidecropper.glide;


import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

/**
 * Glide model loader factory for Cropped Image Model loaders.
 */

public class CroppedImageModelLoaderFactory2 implements ModelLoaderFactory<CroppedImage2, CroppedImageDecoderInput2> {

    private Resources resources;

    public CroppedImageModelLoaderFactory2(Resources resources){
        this.resources = resources;
    }

    @NonNull
    @Override
    public ModelLoader<CroppedImage2, CroppedImageDecoderInput2> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new CroppedImageModelLoader2(resources);
    }

    @Override
    public void teardown() {

    }
}
