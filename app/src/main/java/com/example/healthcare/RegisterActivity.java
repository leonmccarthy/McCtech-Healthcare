package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText edRegUsername, edRegEmail, edRegPassword, edRegConfirmPassword;
    Button registerBtn;
    TextView regLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edRegUsername = findViewById(R.id.editTextRegisterUsername);
        edRegEmail = findViewById(R.id.editTextRegisterEmail);
        edRegPassword = findViewById(R.id.editTextRegisterPassword);
        edRegConfirmPassword = findViewById(R.id.editTextRegisterConfirmPassword);
        registerBtn = findViewById(R.id.buttonRegister);
        regLogin = findViewById(R.id.textViewRegisterLogin);

        regLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}