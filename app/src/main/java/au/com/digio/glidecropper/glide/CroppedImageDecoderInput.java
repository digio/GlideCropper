package au.com.digio.glidecropper.glide;

import android.net.Uri;

/**
 * This is a custom Glide input source for loading images that require cropping and translating prior to display.
 *
 * From a data point of view this is identical to CroppedImage, but it is a different type to differentiate between
 * a Glide input model (CroppedImage) and an input source for the decoder (CroppedImageDecodeInput).
 *
 * The built-in input types Glide supports for decoding (InputStream and ByteBuffer) do not provide enough context
 * for us to do the cropping and trimming work, hence this custom input source.
 */
public class CroppedImageDecoderInput {
    public Uri uri;
    public int viewWidth;
    public int viewHeight;
    public int horizontalOffset = 0;
    public int verticalOffset = 0;

    public CroppedImageDecoderInput(Uri uri, int viewWidth, int viewHeight, int horizontalOffset, int verticalOffset) {
        this.uri = uri;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
    }
}
