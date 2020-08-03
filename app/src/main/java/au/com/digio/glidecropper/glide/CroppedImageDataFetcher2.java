package au.com.digio.glidecropper.glide;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

public class CroppedImageDataFetcher2 implements DataFetcher<CroppedImageDecoderInput2> {
    private Resources resources;
    private CroppedImage2 model;

    public CroppedImageDataFetcher2(Resources resources, CroppedImage2 model) {
        this.resources = resources;
        this.model = model;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super CroppedImageDecoderInput2> callback) {
        CroppedImageDecoderInput2 intermediate = new CroppedImageDecoderInput2(
                model.resId,
                model.viewHeight,
                model.viewWidth,
                model.verticalOffset,
                model.horizontalOffset);
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
    public Class<CroppedImageDecoderInput2> getDataClass() {
        return CroppedImageDecoderInput2.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
