package fr.imt_atlantique.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class DisplayActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("USER_INFO");

        TextView textViewNom = findViewById(R.id.nom);
        TextView textViewPrenom = findViewById(R.id.prenom);
        TextView textViewDepar = findViewById(R.id.depar);
        TextView textViewDate = findViewById(R.id.date);
        //TextView textViewPhone = findViewById(R.id.phone);

        textViewNom.setText(user.getnom());
        textViewPrenom.setText(user.getprenom());
        textViewDepar.setText(user.getdepar());
        textViewDate.setText(user.getdate());
        //textViewPhone.setText(user.getlistphone().get(0));
        LinearLayout phoneaddnumb =(LinearLayout) findViewById(R.id.phonelayout);
        LinkedList<String> listphone=user.getlistphone();
        int l=listphone.size();
        for(int i=0;i<l;i++){
            TextView phone= new TextView(DisplayActivity.this);
            phone.setText(listphone.get(i));
            phoneaddnumb.addView(phone);


        }

    }
}
