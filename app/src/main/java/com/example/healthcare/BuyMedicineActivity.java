package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String [][] packages = {
            {"Uprise-D3 Capsule", "","","","50"},
            {"Vitamin-H2 Capsule", "","","","230"},
            {"Protein-L1 Capsule", "","","","410"},
            {"Dolo-P7 Capsule", "","","","30"},
            {"Feronia-J4 Capsule", "","","","780"},
            {"Tata-A3 Capsule", "","","","190"},
            {"Masala-K22 Capsule", "","","","640"},
            {"Opsilon-MN45 Capsule", "","","","100"},
            {"Walam-Y56 Capsule", "","","","80"},
    };

    private String [] package_details = {
        "Protects your bones against fractures\n"+
                "Prevent brain and spinal birth defects in offspring\n"+
                "Keeps dental cavities from starting or worsening",
            "Helps form and maintain healthy teeth, bones, soft tissue, mucous membranes, and skin\n"+
                    "Helps form red blood cells and maintain brain function\n"+
                    "Helps the body absorb iron and maintain healthy tissue",
            "Helps maintain healthy skin and nerves\n"+
                    "Production of hormones and cholesterol",
            "Body growth and the production of red blood cells",
            "Helps the body to change fatty acids into energy",
            "Reduces Appetite and Hunger Levels\n"+
                    "Increases Muscle Mass and Strength",
            "Reduces Cravings and Desire for Late-Night Snacking"+
                    "Boosts Metabolism and Increases Fat Burning",
            "Help fuel your brain, kidneys, heart muscles, and central nervous system\n"+
                    "Aids in digestion",
            "Keeps blood cholesterol levels in check\n"+
                    "Helps you feel full"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;
    Button backButton, goToCartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.listViewBM);
        backButton = findViewById(R.id.buttonBMBack);
        goToCartButton = findViewById(R.id.buttonBMGoToCart);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost : $ "+ packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_line,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });

        goToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
    }
}