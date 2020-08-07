package au.com.digio.glidecropper.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

public class CroppedImageDataFetcher implements DataFetcher<CroppedImageDecoderInput> {
    private Context context;
    private CroppedImage model;

    public CroppedImageDataFetcher(Context context, CroppedImage model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super CroppedImageDecoderInput> callback) {
        CroppedImageDecoderInput intermediate = new CroppedImageDecoderInput(
                model.uri,
                model.imageWidth,
                model.imageHeight,
                model.horizontalOffset,
                model.verticalOffset,
                model.desImageWidth);
        callback.onDataReady(intermediate);
    }

    @Override
    public void cleanup() {
        // Intentionally empty only because we're not opening an InputStream or another I/O resource!
    }

    @Override
    public void cancel() {
        // Intentionally empty.
    }

    @NonNull
    @Override
    public Class<CroppedImageDecoderInput> getDataClass() {
        return CroppedImageDecoderInput.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
