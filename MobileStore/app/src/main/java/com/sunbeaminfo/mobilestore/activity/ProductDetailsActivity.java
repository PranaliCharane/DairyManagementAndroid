package com.sunbeaminfo.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.entity.Order;
import com.sunbeaminfo.mobilestore.entity.ProductDetails;
import com.sunbeaminfo.mobilestore.utils.API;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    TextView textName,textPrice,textDescription,textCompany;
    ImageView imageView;
   ProductDetails productDetails;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_product_details);
        textName = findViewById(R.id.textName);
        textPrice = findViewById(R.id.textPrice);
        textDescription = findViewById(R.id.textDescription);
        textCompany = findViewById(R.id.textCompany);
        imageView = findViewById(R.id.imageView);
        productDetails =(ProductDetails)  getIntent().getSerializableExtra("productDetails");

        getProductDetails();
   }

    private void getProductDetails() {
        textName.setText("Name  :"+productDetails.getName());
        textPrice.setText("Price  :"+productDetails.getPrice());
        textDescription.setText("Description :"+productDetails.getDescription());
        textCompany.setText("Company  :"+productDetails.getCompany());

        Glide.with(this).load(API.BASE_URL+"/"+productDetails.getImage()).into(imageView);
    }

    public void buy(View view){
        int uid = getSharedPreferences("mobileStore",MODE_PRIVATE).getInt("uid",0);
        int mid = productDetails.getId();
        Order order = new Order();
        order.setUid(uid);
        order.setMid(mid);
        RetrofitClient.getInstance().getApi().placeOrder(order).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    Toast.makeText(ProductDetailsActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    finish();
                                    }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}