package fr.imt_atlantique.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private Button buttonvalidate;
    private Button buttonaddnumber;
    private Integer nb=0;

    private LinkedList<EditText> listphone= new LinkedList<>();
    DatePickerFragment datePickerFragment;

    EditText NomEdit;
    TextView NomView;

    private static final String Key_nom = "Key_nom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NomEdit= (EditText) findViewById(R.id.editNom);

        setContentView(R.layout.activity_main);
        datePickerFragment = new DatePickerFragment( (TextView) findViewById(R.id.BirthDate));

        Button birthdateBtn = (Button) findViewById(R.id.btnEnterBirthdate);
        birthdateBtn.setOnClickListener((View v) -> {
            datePickerFragment.show(getSupportFragmentManager(), "datePicker");
        });

        buttonvalidate = (Button) findViewById(R.id.button);
        buttonaddnumber= (Button) findViewById(R.id.button2);
        Log.i("Lifecycle", "onCreate method");


        buttonvalidate.setOnClickListener(new View.OnClickListener () {
            public void onClick(View v) {
                Snackbar.make(v,"vous avez " +nb + " numero de telephone", Snackbar.LENGTH_LONG).show();

                EditText EditTextprenom  = (EditText)findViewById(R.id.editPrenom);
                EditText EditTextnom = (EditText)findViewById(R.id.editNom);
                Spinner EditTextdepar = (Spinner) findViewById(R.id.spinner2);
                TextView EditTextbirthDate = (TextView) findViewById(R.id.BirthDate);

                String prenom = EditTextprenom.getText().toString();
                String nom = EditTextnom.getText().toString();
                String depar = EditTextdepar.getSelectedItem().toString();
                String date = EditTextbirthDate.getText().toString();
                LinkedList<String> listphoneStr= EdittexttoString(listphone);
                User user= new User(nom,prenom,depar,date,listphoneStr);

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("USER_INFO", user);
                startActivity(intent);
            }
        });




        buttonaddnumber.setOnClickListener(new View.OnClickListener () {
            public void onClick(View v) {
                nb+=1;

                LinearLayout phoneaddnumberLayout =(LinearLayout) findViewById(R.id.Layout);
                LinearLayout phonenumberLayout = new LinearLayout(MainActivity.this);
                phonenumberLayout.setOrientation(LinearLayout.HORIZONTAL);
                phonenumberLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));

                EditText phone= new EditText(MainActivity.this);
                phone.setHint("Ajouter un numéro"+nb);

                Button buttondelete= new Button(MainActivity.this);
                buttondelete.setHint("Supprimer");

                phonenumberLayout.addView(phone);
                phonenumberLayout.addView(buttondelete);
                phoneaddnumberLayout.addView(phonenumberLayout);
                listphone.add(phone);


                buttondelete.setOnClickListener(new View.OnClickListener () {
                    public void onClick(View v) {
                        nb-=1;
                        listphone.remove(phone);
                        phonenumberLayout.removeAllViews();

                    }
                });

            }

        });
        if (savedInstanceState != null){
            String editTextValue = savedInstanceState.getString(Key_nom);
            NomEdit.setText(editTextValue);

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void resetAction (MenuItem item){
        EditText prenom = (EditText)findViewById(R.id.editPrenom);
        EditText nom = (EditText)findViewById(R.id.editNom);
        TextView birthDate = (TextView) findViewById(R.id.BirthDate);
        Spinner departement = (Spinner) findViewById(R.id.spinner2);

        prenom.setText("");
        nom.setText("");
        birthDate.setText(R.string.dd_mm_yyyy);
        departement.setSelection(0);

        datePickerFragment.clearDialog();
        LinearLayout phoneNumbersLayout = (LinearLayout) findViewById(R.id.Layout);
        phoneNumbersLayout.removeAllViews();

        nb = 0;
    }

    public void WikiOption (MenuItem item){
        Spinner departement = (Spinner) findViewById(R.id.spinner2);
        String deparname = departement.getSelectedItem().toString();
        String URL= "http://fr.wikipedia.org/?search="+deparname;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(intent);
    }

    public void PartagerOption (MenuItem item){
        int id = item.getItemId();
        Spinner departement = (Spinner) findViewById(R.id.spinner2);
        String deparname = departement.getSelectedItem().toString();
        if (id == R.id.PartagerOption) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, deparname);
            startActivity(Intent.createChooser(shareIntent, "Partager le nom via"));
        }
    }




    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString(Key_nom,NomEdit.getText().toString() );

    }
    public LinkedList<String> EdittexttoString(LinkedList<EditText> listphone){
        LinkedList<String> phoneStr = new LinkedList<>();
        int l=listphone.size();
        for(int i=0;i<l;i++){
            phoneStr.add(listphone.get(i).getText().toString());
        }
        return phoneStr;
    }

}