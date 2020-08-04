package au.com.digio.glidecropper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import au.com.digio.glidecropper.glide.GlideApp;
import au.com.digio.glidecropper.widget.CroppedImageView;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_KEY = "product";

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // These pictures selected from Unsplash are a lot larger than required but demonstrate the cropping and translation
        // image manipulation at load time.
        products.add(new Product(R.drawable.la_so_512424_unsplash,
                "Village Gelati",
                "Donec consequat vehicula velit, in posuere nulla dapibus quis.  amet nisl",
                R.color.green));
        products.add(new Product(R.drawable.tyler_nix_574395_unsplash,
                "Skyscraper Frost",
                "Aenean magna leo, molestie sit amet vestibulum ac. Donec ut nunc a convallis tempus.",
                R.color.orange));
        products.add(new Product(R.drawable.meric_dagli_1112362_unsplash,
                "Pumpkin Empire",
                "Vivamus rutrum enim sem. Fusce tempor, velit et egestas faucibus tortor.",
                R.color.purple));
        products.add(new Product(R.drawable.tom_gainor_737280_unsplash,
                "Lake Drift",
                "Sapien risus euismod massa, sed suscipit tellus enim sit Proin fermentum.",
                R.color.green));
        products.add(new Product(R.drawable.matt_seymour_1102794_unsplash,
                "Autumn Wish",
                "Quisque semper nisl lacus, ut pharetra ex elementum sit amet. Phasellus interdum.",
                R.color.white));
    }


    @Override
    protected void onStart() {
        super.onStart();

        GlideApp.get(this).clearMemory();
//        GlideApp.get(this).clearDiskCache(); // Do in background

        RecyclerView recyclerView = findViewById(R.id.productList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ProductsAdapter adapter = new ProductsAdapter(products, new OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                productSelected(product);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void productSelected(Product product) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(PRODUCT_KEY, product);
        startActivity(intent);
    }

    public class ProductsAdapter extends
            RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

        private OnItemClickListener onItemClickListener;
        private ArrayList<Product> products;

        public ProductsAdapter(ArrayList<Product> products, OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            this.products = products;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent,
                    false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            holder.productImage.clear();
            holder.productImage.setImageResource(products.get(position).imageRes);
            holder.productSummary.setText(products.get(position).title);
            holder.productSummary.setTextColor(ContextCompat.getColor(holder.productSummary.getContext(),
                    products.get(position).colour));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(products.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public CroppedImageView productImage;
            public TextView productSummary;

            public ProductViewHolder(@NonNull View itemView) {
                super(itemView);
                this.productImage = itemView.findViewById(R.id.productImage);
                this.productSummary = itemView.findViewById(R.id.productSummary);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}