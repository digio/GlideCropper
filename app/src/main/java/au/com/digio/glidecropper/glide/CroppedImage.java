package au.com.digio.glidecropper.glide;

import android.net.Uri;

import java.util.Objects;

public class CroppedImage {
    public Uri uri;
    public int imageWidth;
    public int imageHeight;
    public int horizontalOffset = 0;
    public int verticalOffset = 0;

    public CroppedImage(Uri uri, int imageWidth, int imageHeight, int horizontalOffset, int verticalOffset) {
        this.uri = uri;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
    }

    // Methods needed for Glide caching.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CroppedImage that = (CroppedImage) o;
        return uri == that.uri &&
                imageWidth == that.imageWidth &&
                imageHeight == that.imageHeight &&
                horizontalOffset == that.horizontalOffset &&
                verticalOffset == that.verticalOffset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, imageWidth, imageHeight, horizontalOffset, verticalOffset);
    }
}
