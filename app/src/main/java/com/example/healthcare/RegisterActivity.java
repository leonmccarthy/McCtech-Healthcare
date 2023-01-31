package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edRegUsername.getText().toString();
                String email = edRegEmail.getText().toString();
                String password = edRegPassword.getText().toString();
                String confirmPassword = edRegConfirmPassword.getText().toString();

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirmPassword.length()==0){
                    Toast.makeText(RegisterActivity.this, "Please fill in all details!", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(confirmPassword)==0){
                        if (isValid(password)){
                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Password must contain atleast 8 charcters, having letter, digit and special character!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    //        Password Validation
    public static boolean isValid(String passwordcheck){
        int f1=0, f2=0, f3=0;
        if(passwordcheck.length()<8){
            return false;
        }else {
            for (int i = 0; i < passwordcheck.length(); i++){
                if(Character.isLetter(passwordcheck.charAt(i))){
                    f1 = 1;
                }
            }
            for (int i = 0; i < passwordcheck.length(); i++){
                if(Character.isDigit(passwordcheck.charAt(i))){
                   f2 = 1;
                }
            }
            for (int i = 0; i <passwordcheck.length(); i++){
                char c = passwordcheck.charAt(i);
                if (c>=33 && c<=46 || c==64){
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 ==1 && f3 == 1)
                return true;
            return false;
        }
    }
}