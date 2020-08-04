package au.com.digio.glidecropper.glide;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

public class CroppedImageDataFetcher implements DataFetcher<CroppedImageDecoderInput> {
    private Resources resources;
    private CroppedImage model;

    public CroppedImageDataFetcher(Resources resources, CroppedImage model) {
        this.resources = resources;
        this.model = model;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super CroppedImageDecoderInput> callback) {
        CroppedImageDecoderInput intermediate = new CroppedImageDecoderInput(
                model.resId,
                model.viewWidth,
                model.viewHeight,
                model.horizontalOffset,
                model.verticalOffset);
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
