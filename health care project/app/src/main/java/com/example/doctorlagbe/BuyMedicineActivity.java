package com.example.doctorlagbe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] medicines = {
            {" Paracetamol 500mg Tablets", "", "", "", " 10"},
            {" Maxpro 20 mg", "", "", "", " 98"},
            {" Fexo 120 mg Tablet", "", "", "", " 120"},
            {" Rocal-D VITA", "", "", "", " 250"},
            {" NATURAL VITAMIN E CAPSULES", "", "", "", " 600"},
    };

    private String[] medicines_details={
            "Paracetamol 500 MG Tablet is used a pain-relieving medication.\n"+
                    "It is primarily used to treat acute pain.\n"+
                    "As it is a pain reliever, it is unlikely that you will miss a dose.\n",

            "In order to treat erosive esophagitis"
                    +"For the support of erosive esophagitis healing"+
                    "Syndrome of Zollinger-Ellison",

            "Fexo 120mg Tablet is an anti-allergy medicine used" +
                    "in the treatment of allergic symptoms such" +
                    " as runny nose, congestion or stuffiness, " +
                    "sneezing, itching, swelling, and watery eyes.",

                    "Treatment of osteoporosis, rickets, osteomalacia, tetany and hypoparathyroidism.\n" +
                    "In pregnancy and lactation due to increase demand.\n" +
                    "In kidney disease and pancreatitis.",

            "100 % vegan plant based vitamin E supplement.\n" +
                    " 30 liquid filled capsules.\n" +
                    "rich in antioxidant and helps with skin and hair health",
    };

    HashMap <String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    ListView listView;
    Button btnBack, btnGoToCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.listViewBuyMedivines);
        btnBack = findViewById(R.id.bmBtnBack);
        btnGoToCart = findViewById(R.id.bmBtnGoCart);

    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
        }
    });

    btnGoToCart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity));
        }
    });

        list=new ArrayList();

        for(int i=0; i<medicines.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", medicines[i][0]);
            item.put("line2", medicines[i][1]);
            item.put("line3", medicines[i][2]);
            item.put("line4", medicines[i][3]);
            item.put("line5", "Cost: "+medicines[i][4]+"/=");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4", "line5" },
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(simpleAdapter);

    }
}