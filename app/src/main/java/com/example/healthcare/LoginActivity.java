package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button loginBtn;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editTextLoginUsername);
        editPassword = findViewById(R.id.editTextLoginPassword);
        loginBtn = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.textViewLoginRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                if(username.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this, "Please fill in the details!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}