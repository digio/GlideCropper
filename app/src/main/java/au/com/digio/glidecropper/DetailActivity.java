package au.com.digio.glidecropper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import au.com.digio.glidecropper.widget.CroppedImageView;


/**
 * Activity that shows the products details, with a background image that fills the whole screen.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Product product = (Product) getIntent().getSerializableExtra(MainActivity.PRODUCT_KEY);

        ((CroppedImageView) findViewById(R.id.productImage)).setImageResource(product.imageRes);
        ((TextView) findViewById(R.id.productTitle)).setText(product.title);
        ((TextView) findViewById(R.id.productDescription)).setText(product.description);
        ((TextView) findViewById(R.id.productTitle)).setTextColor(product.colour);
        ((TextView) findViewById(R.id.productDescription)).setTextColor(product.colour);
    }

}