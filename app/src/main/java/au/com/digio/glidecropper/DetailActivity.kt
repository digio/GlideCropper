package au.com.digio.glidecropper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Activity that shows the products details, with a background image that fills the whole screen.
 */
class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onStart() {
        super.onStart()

        val product = intent.getSerializableExtra(MainActivity.PRODUCT_KEY) as Product? ?: return

        productImage.setImageResource(product.imageRes)
        productTitle.text = product.title
        productDescription.text = product.description
        productTitle.setTextColor(ContextCompat.getColor(this, product.colour))
        productDescription.setTextColor(ContextCompat.getColor(this, product.colour))
    }
}