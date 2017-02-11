package in.ravi.ilovezappos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.reflect.Field;
import java.util.List;

import in.ravi.ilovezappos.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private List<Cart> cartList;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fav;
        private ImageView productImage;
        private TextView productPrice;
        private TextView brandName;
        private TextView productName;

        MyViewHolder(View view) {
            super(view);
            fav = (ImageView) view.findViewById(R.id.fav);
            brandName = (TextView) view.findViewById(R.id.brandName);
            productName = (TextView) view.findViewById(R.id.productName);
            productPrice = (TextView) view.findViewById(R.id.productPrice);
            productImage = (ImageView) view.findViewById(R.id.productImage);

        }
    }


    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.brandName.setText(cart.getBrand());
        holder.productName.setText(cart.getProduct());
        holder.productPrice.setText(cart.getPrice());

        Glide.with(holder.productImage.getContext())
                .load(cart.getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.placeholder)
                .into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
