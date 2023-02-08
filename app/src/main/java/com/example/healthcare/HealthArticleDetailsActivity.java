package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView img;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);

        tvTitle = findViewById(R.id.textViewHADTitle);
        img = findViewById(R.id.imageViewHAD);
        backButton = findViewById(R.id.buttonHADBack);

        Intent it = getIntent();
        tvTitle.setText(it.getStringExtra("text1"));

        Bundle bundle =  getIntent().getExtras();
        if(bundle !=null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleDetailsActivity.this, HealthArticlesActivity.class));
            }
        });
    }
}