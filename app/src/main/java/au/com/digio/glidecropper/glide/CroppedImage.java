package au.com.digio.glidecropper.glide;

import java.util.Objects;

public class CroppedImage {
    public int resId;
    public int viewWidth;
    public int viewHeight;
    public int horizontalOffset = 0;
    public int verticalOffset = 0;

    public CroppedImage(int resId, int viewWidth, int viewHeight, int horizontalOffset, int verticalOffset) {
        this.resId = resId;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
    }

    // Methods needed for Glide caching.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CroppedImage that = (CroppedImage) o;
        return resId == that.resId &&
                viewWidth == that.viewWidth &&
                viewHeight == that.viewHeight &&
                horizontalOffset == that.horizontalOffset &&
                verticalOffset == that.verticalOffset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, viewWidth, viewHeight, horizontalOffset, verticalOffset);
    }
}
