package au.com.digio.glidecropper.glide

/**
 * A glide model class to initiate custom image loading behaviour.
 */
class CroppedImage(
        val resId: Int,
        val viewWidth: Int,
        val viewHeight: Int,
        val horizontalOffset: Int = 0,
        val verticalOffset: Int = 0
) {

    // Methods needed for Glide caching.
    override fun equals(other: Any?): Boolean {
        if (other is CroppedImage) {
            return resId == other.resId
                   && viewWidth == other.viewWidth
                   && viewHeight == other.viewHeight
                   && horizontalOffset == other.horizontalOffset
                   && verticalOffset == other.verticalOffset
        }
        return this === other
    }

    override fun hashCode(): Int {
        return (resId.toString() +
                viewWidth.toString() +
                viewHeight.toString() +
                horizontalOffset.toString() +
                verticalOffset.toString()).hashCode()
    }
}