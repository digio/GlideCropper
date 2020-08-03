package au.com.digio.glidecropper.glide;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

public final class CroppedImageModelLoader2 implements ModelLoader<CroppedImage2, CroppedImageDecoderInput2> {

    private Resources resources;

    public CroppedImageModelLoader2(Resources resources) {
        this.resources = resources;
    }

    @Nullable
    @Override
    public LoadData<CroppedImageDecoderInput2> buildLoadData(@NonNull CroppedImage2 model,
                                                             int width,
                                                             int height,
                                                             @NonNull Options options) {
        return new LoadData<CroppedImageDecoderInput2>(new ObjectKey(model),
        new CroppedImageDataFetcher2(resources, model));
    }

    @Override
    public boolean handles(@NonNull CroppedImage2 model) {
        return true;
    }
}
