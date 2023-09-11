package com.sunbeaminfo.mobilestore.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.adapter.ProductListAdapter;
import com.sunbeaminfo.mobilestore.entity.ProductDetails;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class ProductListFragment extends Fragment {

    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    List<ProductDetails> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        productList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(getContext(),productList);
        recyclerView.setAdapter(productListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        getAllProductDetails();
    }

    private void getAllProductDetails() {
        RetrofitClient.getInstance().getApi().getAllProductDetails().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                    for (JsonElement element :jsonArray) {
                        ProductDetails productDetails = new ProductDetails();
                        productDetails.setId(element.getAsJsonObject().get("id").getAsInt());
                        productDetails.setName(element.getAsJsonObject().get("name").getAsString());
                        productDetails.setPrice(element.getAsJsonObject().get("price").getAsDouble());
                        productDetails.setDescription(element.getAsJsonObject().get("description").getAsString());
                        productDetails.setCompany(element.getAsJsonObject().get("company").getAsString());
                        productDetails.setImage(element.getAsJsonObject().get("image").getAsString());
                        productList.add(productDetails);
                    }
                    productListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
