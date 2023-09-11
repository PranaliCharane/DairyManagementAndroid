package com.sunbeaminfo.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.entity.User;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail, editPassword;
    CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
    }

    public void login(View view) {
        User user = new User();
        user.setEmail(editEmail.getText().toString());
        user.setPassword(editPassword.getText().toString());

        if (checkBoxRememberMe.isChecked())
            getSharedPreferences("mobileStore", MODE_PRIVATE).edit().putBoolean("login_status", true).apply();

        RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray array = response.body().getAsJsonObject().get("data").getAsJsonArray();
                if (array.size() != 0) {
                    JsonObject object = array.get(0).getAsJsonObject();
                    getSharedPreferences("mobileStore", MODE_PRIVATE).edit()
                            .putInt("uid", object.get("id").getAsInt()).apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}