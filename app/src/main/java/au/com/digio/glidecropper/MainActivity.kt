package au.com.digio.glidecropper

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import au.com.digio.glidecropper.glide.GlideApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_product.view.*
import org.jetbrains.anko.doAsync

/**
 * Activity that shows the list of products using cropped and translated images.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_KEY = "product"
    }

    private val products = listOf(
            Product(R.drawable.la_so_512424_unsplash,
                    "Village Gelati",
                    "Donec consequat vehicula velit, in posuere nulla dapibus quis.  amet nisl",
                    R.color.green),
            Product(R.drawable.tyler_nix_574395_unsplash,
                    "Skyscraper Frost",
                    "Aenean magna leo, molestie sit amet vestibulum ac. Donec ut nunc a convallis tempus.",
                    R.color.orange),
            Product(R.drawable.meric_dagli_1112362_unsplash,
                    "Pumpkin Empire",
                    "Vivamus rutrum enim sem. Fusce tempor, velit et egestas faucibus tortor.",
                    R.color.blue),
            Product(R.drawable.tom_gainor_737280_unsplash,
                    "Lake Drift",
                    "Sapien risus euismod massa, sed suscipit tellus enim sit Proin fermentum.",
                    R.color.green),
            Product(R.drawable.matt_seymour_1102794_unsplash,
                    "Autumn Wish",
                    "Quisque semper nisl lacus, ut pharetra ex elementum sit amet. Phasellus interdum.",
                    R.color.white)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        GlideApp.get(this@MainActivity).clearMemory()
        doAsync {
            GlideApp.get(this@MainActivity).clearDiskCache()
        }


        productList.apply {
            adapter = ProductsAdapter { product -> productSelected(product) }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun productSelected(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PRODUCT_KEY, product)
        startActivity(intent)
    }

    inner class ProductsAdapter(val itemListener: (Product) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val vh = ProductViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
            )
            vh.itemView.setOnClickListener { itemListener.invoke(vh.product) }
            return vh
        }

        override fun getItemCount() = products.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ProductViewHolder) {
                holder.bind(products[position])
            }
        }

        override fun getItemViewType(position: Int): Int {
            return 1
        }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val productImage = view.productImage
        private val productSummary = view.productSummary
        lateinit var product: Product

        fun bind(product: Product) {
            this.product = product
            // Stop any images that were still loading from the last bind, in case this row was scrolled and recycled
            productImage.clear()
            productImage.setImageResource(product.imageRes)
            productSummary.text = product.title
            productSummary.setTextColor(ContextCompat.getColor(productSummary.context, product.colour))
        }
    }
}
