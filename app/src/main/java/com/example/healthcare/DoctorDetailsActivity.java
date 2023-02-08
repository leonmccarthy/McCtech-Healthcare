package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class  DoctorDetailsActivity extends AppCompatActivity {

    TextView tv;
    Button back;

    String [][] doctorDetails1 = {
            {"Doc Name : Ronald Edari", "Hospital Address : Kisimani", "Exp : 4yrs", "Tel No : 07215341652", "600"},
            {"Doc Name : Abdul Imu", "Hospital Address : Vescon", "Exp : 2yrs", "Tel No : 0776356282", "300"},
            {"Doc Name : Henry Sofali", "Hospital Address : Bishara", "Exp : 6yrs", "Tel No : 0793647732", "750"},
            {"Doc Name : Ronald Edari", "Hospital Address : Meru", "Exp : 3yrs", "Tel No : 0722847682", "400"},
            {"Doc Name : Paul Awiti", "Hospital Address : Ratna", "Exp : 4yrs", "Tel No : 07215341652", "500"},
    };

    String [][] doctorDetails2 = {
            {"Doc Name : Peter Hale", "Hospital Address : Mainland", "Exp : 3yrs", "Tel No : 07531652113", "660"},
            {"Doc Name : James Blanco", "Hospital Address : Mlolongo", "Exp : 5yrs", "Tel No : 0712361273", "370"},
            {"Doc Name : Mary Achieng", "Hospital Address : Migadini", "Exp : 2yrs", "Tel No : 07213563768", "730"},
            {"Doc Name : Rachel Munyau", "Hospital Address : Jomvu", "Exp : 6yrs", "Tel No : 07928131723", "480"},
            {"Doc Name : Collins Kipkemboi", "Hospital Address : Miritini", "Exp : 5yrs", "Tel No : 073665482", "520"},
    };

    String [][] doctorDetails3 = {
            {"Doc Name : Tom Abila", "Hospital Address : Mwagosi", "Exp : 4yrs", "Tel No : 073526513", "610"},
            {"Doc Name : Mercy Auma", "Hospital Address : Mikindani", "Exp : 6yrs", "Tel No : 0798217632", "350"},
            {"Doc Name : Kevin Saigut", "Hospital Address : KFA", "Exp : 5 yrs", "Tel No : 0766125723", "730"},
            {"Doc Name : Asca Jebet", "Hospital Address : Kingorani", "Exp : 2yrs", "Tel No : 07592389181", "480"},
            {"Doc Name : Paul Wekesa", "Hospital Address : Markiti", "Exp : 3yrs", "Tel No : 07091872391", "550"},
    };

    String [][] doctorDetails4 = {
            {"Doc Name : Josephine Mwende", "Hospital Address : Bondeni", "Exp : 6yrs", "Tel No : 0712392381", "670"},
            {"Doc Name : Amos Biwott", "Hospital Address : Mlaleo", "Exp : 4yrs", "Tel No : 0765342181", "390"},
            {"Doc Name : Molly Cheryuit", "Hospital Address : Bakarani", "Exp : 2yrs", "Tel No : 07123637172", "770"},
            {"Doc Name : Tello Kiptoo", "Hospital Address : Kasarani", "Exp : 5yrs", "Tel No : 0745716781", "430"},
            {"Doc Name : William Ruto", "Hospital Address : Mwandoni", "Exp : 6yrs", "Tel No : 0737637183", "540"},
    };

    String [][] doctorDetails5 = {
            {"Doc Name : Jackline Awour", "Hospital Address : Kisauni", "Exp : 3yrs", "Tel No : 0798764546", "610"},
            {"Doc Name : Maimuna Abdalah", "Hospital Address : Mvita", "Exp : 7yrs", "Tel No : 0766546546", "360"},
            {"Doc Name : Daniel Sianda", "Hospital Address : Changamwe", "Exp : 5yrs", "Tel No : 070876855", "420"},
            {"Doc Name : Emily Kadzo", "Hospital Address : Magongo", "Exp : 5yrs", "Tel No : 0723435525", "400"},
            {"Doc Name : Moody Awuor", "Hospital Address : Chaani", "Exp : 3yrs", "Tel No : 07764654", "200"},
    };

    String [][] doctorDetails = {};
    HashMap <String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            doctorDetails = doctorDetails1;
        } else if(title.compareTo("Dietician")==0){
            doctorDetails = doctorDetails2;
        } else if(title.compareTo("Dentist")==0){
            doctorDetails = doctorDetails3;
        } else if(title.compareTo("Cardiologist")==0){
            doctorDetails = doctorDetails4;
        } else {
            doctorDetails = doctorDetails5;
        }


        back = findViewById(R.id.buttonDDBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<doctorDetails.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", doctorDetails[i][0]);
            item.put("line2", doctorDetails[i][1]);
            item.put("line3", doctorDetails[i][2]);
            item.put("line4", doctorDetails[i][3]);
            item.put("line5", "Cons Fee : $ "+ doctorDetails[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_line,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctorDetails[i][0]);
                it.putExtra("text3", doctorDetails[i][1]);
                it.putExtra("text4", doctorDetails[i][3]);
                it.putExtra("text5", doctorDetails[i][4]);
                startActivity(it);
            }
        });
    }
}