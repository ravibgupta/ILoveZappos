package in.ravi.ilovezappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.clans.fab.FloatingActionButton;

import in.ravi.ilovezappos.databinding.ActivityProductPageBinding;
import in.ravi.ilovezappos.helper.SQLiteHandler;
import in.ravi.ilovezappos.model.Product;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductPageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_page);
        final Product product = getIntent().getParcelableExtra("product");
        binding.setProduct(product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        final ImageView productImage = (ImageView) findViewById(R.id.productImage);

        Glide.with(ProductPage.this)
                .load(product.getThumbnailImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.placeholder)
                .into(productImage);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setShowProgressBackground(true);
                fab.setIndeterminate(true);
                SQLiteHandler db = new SQLiteHandler(getApplicationContext());

                db.addToCart(String.valueOf(db.getTableSize() + 1), product.getThumbnailImageUrl(), product.getBrandName(), product.getProductName(), product.getPrice());

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                        fab.setShowProgressBackground(false);
                        fab.setIndeterminate(false);
                        fab.setEnabled(false);
                        fab.setColorDisabled(Color.parseColor("#8BC34A"));
                    }
                }, 1000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(ProductPage.this, CartActivity.class));
        }

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}