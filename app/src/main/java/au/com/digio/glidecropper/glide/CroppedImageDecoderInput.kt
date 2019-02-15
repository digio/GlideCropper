package au.com.digio.glidecropper.glide

import androidx.annotation.*

/**
 * This is a custom Glide input source for loading images that require cropping and translating prior to display.
 *
 * From a data point of view this is identical to CroppedImage, but it is a different type to differentiate between
 * a Glide input model (CroppedImage) and an input source for the decoder (CroppedImageDecodeInput).
 *
 * The built-in input types Glide supports for decoding (InputStream and ByteBuffer) do not provide enough context
 * for us to do the cropping and trimming work, hence this custom input source.
 */
class CroppedImageDecoderInput(
        @RawRes @DrawableRes val resId: Int,
        val viewWidth: Int,
        val viewHeight: Int,
        val horizontalOffset: Int = 0,
        val verticalOffset: Int = 0
)
