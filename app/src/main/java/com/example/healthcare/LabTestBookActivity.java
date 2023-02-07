package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edFullname, edPinCode, edAddress, edContact;
    Button bookButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edAddress = findViewById(R.id.editTextLTBAddress);
        edPinCode = findViewById(R.id.editTextLTBPinCode);
        edFullname = findViewById(R.id.editTextLTBFullname);
        edContact = findViewById(R.id.editTextLTBContact);
        bookButton = findViewById(R.id.buttonLTBBook);

        Intent it = getIntent();
        String [] price = it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");
        String time = it.getStringExtra("time");

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(LabTestBookActivity.this,"mctech_healthcare", null, 1);
                db.addCartOrder(username, edFullname.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),edPinCode.getText().toString(),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username, "lab");
                Toast.makeText(LabTestBookActivity.this, "Order booked successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}