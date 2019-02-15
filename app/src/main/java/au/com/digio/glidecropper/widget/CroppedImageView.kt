package au.com.digio.glidecropper.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import au.com.digio.glidecropper.R
import au.com.digio.glidecropper.glide.CroppedImage
import com.bumptech.glide.Glide

/**
 * An Image view which crops and translates the image to fit in the view's dimensions using background thread
 * image loading provided by the Glide library.
 */
class CroppedImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    private var horizontalOffset = 0
    private var verticalOffset = 0
    private var resId = 0
    private var loadRequested = false

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        scaleType = ScaleType.FIT_XY
        val ats = context.theme.obtainStyledAttributes(attrs, R.styleable.CroppedImageView, 0, 0)
        horizontalOffset = ats.getDimensionPixelOffset(R.styleable.CroppedImageView_horizontalOffset, 0)
        verticalOffset = ats.getDimensionPixelOffset(R.styleable.CroppedImageView_verticalOffset, 0)
        ats.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (resId != 0) {
            loadCroppedImage()
        }
    }

    override fun setImageResource(resId: Int) {
        this.resId = resId
        loadRequested = false
        if (height != 0 && width != 0) {
            loadCroppedImage()
        }
    }

    private fun loadCroppedImage() {
        if (resId == 0 || loadRequested) return
        loadRequested = true // Don't trigger multiple loads for the same resource
        val model = CroppedImage(resId = resId,
                                 viewWidth = width,
                                 viewHeight = height,
                                 horizontalOffset = horizontalOffset,
                                 verticalOffset = verticalOffset)
        Glide.with(context)
                .load(model)
                .into(this)
    }

    fun clear() {
        // Stop any in-progress image loading
        Glide.with(context).clear(this)
    }
}