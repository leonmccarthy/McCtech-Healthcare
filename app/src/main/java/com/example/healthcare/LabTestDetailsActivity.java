package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button addToCartButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLTDPackageName);
        tvTotalCost = findViewById(R.id.textViewLTDTotalCost);
        edDetails = findViewById(R.id.editTextLTDMultiline);
        addToCartButton = findViewById(R.id.buttonLTDAddToCart);
        backButton = findViewById(R.id.buttonLTDBack);

        edDetails.setKeyListener(null);

        Intent it = getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        edDetails.setText(it.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : $"+it.getStringExtra("text3"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db = new Database(LabTestDetailsActivity.this,"mctech_healthcare", null, 1);
                if (db.checkCart(username, product)==1){
                    Toast.makeText(LabTestDetailsActivity.this, "Product already added!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addToCart(username, product, price, "lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Order entered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });
    }
}