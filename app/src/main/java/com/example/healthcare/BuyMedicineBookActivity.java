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

import java.util.Date;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edFullname, edPinCode, edAddress, edContact;
    Button bookButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edAddress = findViewById(R.id.editTextBMBAddress);
        edPinCode = findViewById(R.id.editTextBMBPinCode);
        edFullname = findViewById(R.id.editTextBMBFullname);
        edContact = findViewById(R.id.editTextBMBContact);
        bookButton = findViewById(R.id.buttonBMBBook);

        Intent it = getIntent();
        String [] price = it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(BuyMedicineBookActivity.this,"mctech_healthcare", null, 1);

                db.addCartOrder(username, edFullname.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),edPinCode.getText().toString(),date.toString(),"No time",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username, "medicine");
                Toast.makeText(BuyMedicineBookActivity.this, "Medicine ordered successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
            }
        });
    }
}