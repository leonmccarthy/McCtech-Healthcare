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

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edMultiline;

    Button backButton, addToCartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        tvTotalCost  = findViewById(R.id.textViewBMDTotalCost);
        edMultiline = findViewById(R.id.editTextBMDMultiline);
        edMultiline.setKeyListener(null);
        backButton = findViewById(R.id.buttonBMDBack);
        addToCartButton = findViewById(R.id.buttonBMDAddToCart);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        Intent it = getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        edMultiline.setText(it.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : $"+it.getStringExtra("text3"));

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db = new Database(BuyMedicineDetailsActivity.this,"mctech_healthcare", null, 1);
                if (db.checkCart(username, product)==1){
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Medicine already added!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addToCart(username, product, price, "medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Medicine ordered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}