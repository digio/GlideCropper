package au.com.digio.glidecropper.glide;

import android.net.Uri;

import java.util.Objects;

public class CroppedImage {
    public Uri uri;
    public int imageWidth;
    public int imageHeight;
    public int horizontalOffset = 0;
    public int verticalOffset = 0;
    public int desImageWidth;


    /**
     * @param uri Uri of the image to be cropped
     * @param imageWidth the width of the cropped photo (less than the width of the image to be cropped)
     * @param imageHeight the height of the cropped photo (less than the height of the image to be cropped)
     * @param horizontalOffset horizontal Offset to start the crop after it (x)
     * @param verticalOffset vertical Offset to start the crop after it (y)
     * @param desImageWidth the width required for the final cropped image (imageWidth resized)
     */
    public CroppedImage(Uri uri, int imageWidth, int imageHeight, int horizontalOffset, int verticalOffset,
                        int desImageWidth) {
        this.uri = uri;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
        this.desImageWidth = desImageWidth;
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
                verticalOffset == that.verticalOffset &&
                desImageWidth == that.desImageWidth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, imageWidth, imageHeight, horizontalOffset, verticalOffset,
                desImageWidth);
    }
}
