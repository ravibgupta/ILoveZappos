package in.ravi.ilovezappos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.List;

import in.ravi.ilovezappos.model.Product;
import in.ravi.ilovezappos.model.ProductResponse;
import in.ravi.ilovezappos.rest.ApiClient;
import in.ravi.ilovezappos.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    List<Product> products;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("I LOVE ZAPPOS");

        final TextView homeText = (TextView) findViewById(R.id.homeText);

        final FloatingSearchView searchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                searchView.showProgress();

                if (currentQuery.equals("")) {
                    homeText.setText("Nothing to search!");
                    homeText.setVisibility(View.VISIBLE);
                    searchView.hideProgress();
                    return;
                }

                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<ProductResponse> call = apiService.getProductsByTerm(currentQuery, "b743e26728e16b81da139182bb2094357c31d331");
                call.enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        products = response.body().getResults();
                        Log.d(TAG, "Number of products received: " + products.size());
                        searchView.hideProgress();

                        Product product = products.get(0);
                        Intent i = new Intent(MainActivity.this, ProductPage.class);
                        i.putExtra("product", product);
                        startActivity(i);

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
