package au.com.digio.glidecropper.glide

import android.content.res.Resources
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey

/**
 * Glide model loader to support the CroppedImage model.
 */
class CroppedImageModelLoader(val resources: Resources) : ModelLoader<CroppedImage, CroppedImageDecoderInput> {

    override fun handles(model: CroppedImage): Boolean = true

    override fun buildLoadData(
            model: CroppedImage,
            width: Int,
            height: Int,
            options: Options): ModelLoader.LoadData<CroppedImageDecoderInput>? {

        return ModelLoader.LoadData<CroppedImageDecoderInput>(
                ObjectKey(model),
                CroppedImageDataFetcher(resources, model))
    }
}