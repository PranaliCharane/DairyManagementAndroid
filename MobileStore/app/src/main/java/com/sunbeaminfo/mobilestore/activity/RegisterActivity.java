package com.sunbeaminfo.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sunbeaminfo.mobilestore.R;
import com.sunbeaminfo.mobilestore.entity.User;
import com.sunbeaminfo.mobilestore.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText editName,editEmail,editMobile,editPassword,editConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
    }

    public void register(View view){
        User user = validateUser();
        if(user!=null){
            RetrofitClient.getInstance().getApi().registerUser(user).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                    {
                        Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private User validateUser() {
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();
        if(password.equals(confirmPassword))
        {
            User user = new User();
            user.setUname(editName.getText().toString());
            user.setPassword(password);
            user.setEmail(editEmail.getText().toString());
            user.setMobile(editMobile.getText().toString());
            return user;
        }
        else {
            Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void cancel(View view){

    }
}