package com.example.doctorlagbe;

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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
            {" Full Body Checkup", "", "", "", " 10000"},
            {" Blood Glucose Fasting", "", "", "", " 2000"},
            {" COVID-19 Antibody", "", "", "", " 3500"},
            {" Thyroid Checkup", "", "", "", " 2500"},
            {" Immunity Checkup", "", "", "", " 4200"},
    };

    private String[] package_details={
                             "Blood Glucose Fasting\n"
                            +"HbA1c\n"
                            +"Iron Studies\n"
                            +"kidney Function\n"+
                            "LDH Lactate Test\n"+
                            "Lipid Profile\n"+ "CBC\n"+
                            "liver Function\n",
                    "Blood Glucose Fasting",
                    "COVID-19 Antibody",
                    "Thyroid Checkup(T3, T4, & TSH Ultra-sensitive)",
                    "Complete Hemoglobin\n"+
                            "CRP\n"+
                            "CBC\n"+
                            "Serum\n" +
                            "kidney Function\n"+
                            "LDH Lactate Test\n"+
                            "Lipid Profile\n"+
                            "liver Function",

    };

    HashMap<String, String> items_p;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    Button btnGoToCart, btnBack1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.bmBtnGoCart);
        btnBack1=findViewById(R.id.bmBtnBack);
        listView=findViewById(R.id.listViewBuyMedivines);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i= 0; i<packages.length; i++){
            items_p = new HashMap<String, String>();
            items_p.put("line1", packages[i][0]);
            items_p.put("line2", packages[i][1]);
            items_p.put("line3", packages[i][2]);
            items_p.put("line4", packages[i][3]);
            items_p.put("line5","Cost:"+packages[i][4]+"/-");
            list.add(items_p);
        }

        simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this, LabTestDetailsActivity.class);

                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
//                End List View
       btnGoToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
           }
       });

    }
}