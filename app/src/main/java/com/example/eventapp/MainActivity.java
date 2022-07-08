package com.example.eventapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventapp.Model.Register;
import com.example.eventapp.Services.RegisterApi;
import com.example.eventapp.Services.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        EditText firstname = findViewById(R.id.fname);
        EditText lastname = findViewById(R.id.lname);
        EditText email = findViewById(R.id.email);
        EditText phoneno = findViewById(R.id.phoneno);
        EditText address = findViewById(R.id.addres);
        Button Save = (Button) findViewById(R.id.button);

        RetrofitService retrofitService = new RetrofitService();
        RegisterApi registerApi = retrofitService.getRetrofit().create(RegisterApi.class);

        Save.setOnClickListener(view -> {
            String name = String.valueOf(firstname.getText());
            String last = String.valueOf(lastname.getText());
            String emal = String.valueOf(email.getText());
            String phone = String.valueOf(phoneno.getText());
            String addss = String.valueOf(address.getText());

            Register register = new Register();
            register.setFirstname(name);
            register.setLastname(last);
            register.setEmail(emal);
            register.setPhoneno(phone);
            register.setAddress(addss);

            registerApi.save(register)
                    .enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Toast.makeText( MainActivity.this, "Save Successful.!",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {
                            Toast.makeText(  MainActivity.this, "Failed to save..!!!:",Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured",t );

                        }
                    });
        });

    }
}