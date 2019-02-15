package au.com.digio.glidecropper.glide

import android.content.res.Resources
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher

/**
 * Glide data fetcher for the CroppedImage type. It creates data input (CroppedImageDecoderInput) from a model
 * (CroppedImage).
 */
class CroppedImageDataFetcher(
        val resources: Resources,
        val model: CroppedImage) : DataFetcher<CroppedImageDecoderInput> {

    override fun getDataClass(): Class<CroppedImageDecoderInput> = CroppedImageDecoderInput::class.java

    override fun getDataSource(): DataSource = DataSource.LOCAL

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in CroppedImageDecoderInput>) {
        val intermediate = CroppedImageDecoderInput(
                resId = model.resId,
                viewHeight = model.viewHeight,
                viewWidth = model.viewWidth,
                verticalOffset = model.verticalOffset,
                horizontalOffset = model.horizontalOffset
        )
        callback.onDataReady(intermediate)
    }

    override fun cancel() { }

    override fun cleanup() { }
}