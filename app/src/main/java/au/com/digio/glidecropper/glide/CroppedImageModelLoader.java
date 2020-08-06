package au.com.digio.glidecropper.glide;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

public final class CroppedImageModelLoader implements ModelLoader<CroppedImage, CroppedImageDecoderInput> {

    private Context context;

    public CroppedImageModelLoader(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public LoadData<CroppedImageDecoderInput> buildLoadData(@NonNull CroppedImage model,
                                                            int width,
                                                            int height,
                                                            @NonNull Options options) {
        return new LoadData<CroppedImageDecoderInput>(new ObjectKey(model),
        new CroppedImageDataFetcher(context, model));
    }

    @Override
    public boolean handles(@NonNull CroppedImage model) {
        return true;
    }
}
