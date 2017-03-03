package com.flairinfosystems.b2bmarket.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.flairinfosystems.b2bmarket.R;
import com.flairinfosystems.b2bmarket.activities.CardExpandActivity;
import com.flairinfosystems.b2bmarket.fragments.HomeFragment;
import com.flairinfosystems.b2bmarket.models.Product;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 28-02-2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ProductViewHolder> {
    //View Holder class
    HomeFragment homeFragment;
    public class ProductViewHolder extends RecyclerView.ViewHolder {
       private Product product;
        CardView card_view;
        TextView cardText;
         ImageView cardImage;

        // each data item is just a string in this case

        public ProductViewHolder(View v) {
            super(v);
            card_view= (CardView) v.findViewById(R.id.card_view);
            cardText= (TextView) v.findViewById(R.id.cardText);
            cardImage= (ImageView) v.findViewById(R.id.cardImage);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title= product.getText();
                    String image= product.getImage();
                    Intent intent = new Intent(context, CardExpandActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("image", image);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }
        private Context context;
    private ArrayList<Product> productArrayList;

    public HomeAdapter(Context context,ArrayList<Product> productArrayList){
    this.productArrayList=productArrayList;
        this.context=context;

    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product= productArrayList.get(position);
       // holder.cardImage.setImageResource(Integer.parseInt(product.getImage()));
        //context = holder.cardImage.getContext();
        Glide.with(context)
                .load(product.image)
                .placeholder(R.drawable.progress_bar)
                .into(holder.cardImage);
        holder.cardText.setText(product.text);
        holder.product=product;
    }

    @Override
    public int getItemCount() {
        if(productArrayList!= null){
        return productArrayList.size();
    }
        return 0;
    }


}
