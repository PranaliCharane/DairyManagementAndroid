package com.sunbeaminfo.mobilestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.activity.ProductDetailsActivity;
import com.sunbeaminfo.mobilestore.entity.ProductDetails;

import java.util.List;
    public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {
        Context context;
        List<ProductDetails> productList;

        public ProductListAdapter(Context context, List<ProductDetails> productList) {
            this.context = context;
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.product_list,null);
            return new MyViewHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull ProductListAdapter.MyViewHolder holder, int position) {
            ProductDetails productDetails = productList.get(position);
            holder.textName.setText(productDetails.getName());
            holder.textPrice.setText(""+productDetails.getPrice());
//          holder.textDescription.setText(productDetails.getDescription());
            holder.textCompany.setText(productDetails.getCompany());
            Glide.with(context).load("http://192.168.1.6:4000/"+productDetails.getImage()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView image;
            TextView textName, textPrice, textDescription,textCompany;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                textName = itemView.findViewById(R.id.textName);
                textPrice = itemView.findViewById(R.id.textPrice);
                textDescription = itemView.findViewById(R.id.textDescription);
                textCompany = itemView.findViewById(R.id.textCompany);



                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProductDetailsActivity.class);
                        intent.putExtra("productDetails",productList.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                });
            }
        }
    }


