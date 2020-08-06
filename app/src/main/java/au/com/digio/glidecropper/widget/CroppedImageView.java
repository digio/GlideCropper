package au.com.digio.glidecropper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import au.com.digio.glidecropper.R;
import au.com.digio.glidecropper.glide.CroppedImage;

public class CroppedImageView extends AppCompatImageView {

    private Context context;
    private int horizontalOffset = 0;
    private int verticalOffset = 0;
    private int resId = 0;
    private Uri uri;
    private boolean loadRequested = false;

    public CroppedImageView(Context context) {
        super(context);
    }

    public CroppedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        setScaleType(ScaleType.FIT_XY);
        TypedArray ats = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CroppedImageView, 0, 0);
        horizontalOffset = ats.getDimensionPixelOffset(R.styleable.CroppedImageView_horizontalOffset, 0);
        verticalOffset = ats.getDimensionPixelOffset(R.styleable.CroppedImageView_verticalOffset, 0);
        ats.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (uri != null) {
            loadCroppedImage();
        }
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        this.uri = uri;
        loadRequested = false;
        if (getHeight() != 0 && getWidth() != 0) {
            loadCroppedImage();
        }
    }

    @Override
    public void setImageResource(int resId) {
        this.resId = resId;
        loadRequested = false;
        if (getHeight() != 0 && getWidth() != 0) {
            loadCroppedImage();
        }
    }

    private void loadCroppedImage() {
        if (uri == null || loadRequested){
            return;
        }
        loadRequested = true; // Don't trigger multiple loads for the same resource
        CroppedImage model = new CroppedImage(uri, getWidth(), getHeight(), horizontalOffset,
                verticalOffset);
        Glide.with(context)
                .load(model)
                .into(this);
    }

    public void clear() {
        // Stop any in-progress image loading
        Glide.with(context).clear(this);
    }
}
