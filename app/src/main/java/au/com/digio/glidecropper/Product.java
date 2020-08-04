package au.com.digio.glidecropper;

import java.io.Serializable;

public class Product implements Serializable {

    public int imageRes;
    public String title;
    public String description;
    public int colour;

    public Product(int imageRes, String title, String description, int colour) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
        this.colour = colour;
    }
}
