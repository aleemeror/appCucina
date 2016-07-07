package com.example.studente.appcucinaproject.Ricetta;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.DatiCondivisi;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.ReadAndWriteXML.ReadXML;
import com.example.studente.appcucinaproject.ReadAndWriteXML.WriteXML;
import com.example.studente.appcucinaproject.Timer.Timer;
import com.example.studente.appcucinaproject.Timer.TimerOverActivity;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Ricetta extends AppCompatActivity {

    ImageView imageView;

    TextView txtname;
    TextView txtDescrizione;
    TextView txtPersone;
    TextView txtTempoImpiegato;
    TextView txtCalorie;
    TextView txtPortata;
    TextView txtIngredienti;
    TextView proc;
    TextView ingr;
    TextView txtDifficolta;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton preferito, timer, spesa,menu_gg;

    ArrayList<String> list_ingredienti= new ArrayList<>();
    private Intent setTimeFromRicetta;
    private String nameRicetta = "";
    private String tempoFromDatabase = "";

    private DatabaseAccess myDatabaseAccess;

    WriteXML write_xml_preferito=new WriteXML();
    ReadXML read_xml_preferito= new ReadXML();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricetta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface custom_font_3 = Typeface.createFromAsset(getAssets(), "TCM.TTF"); //per modificare il font

        imageView = (ImageView) findViewById(R.id.d_ricetta_image);
        txtname = (TextView) findViewById(R.id.d_ricetta_title);
        txtDescrizione = (TextView) findViewById(R.id.textView5);
        txtPersone = (TextView) findViewById(R.id.textView11);
        txtTempoImpiegato = (TextView) findViewById(R.id.textView12);
        txtCalorie = (TextView) findViewById(R.id.textView14);
        txtPortata = (TextView) findViewById(R.id.textView15);
        txtIngredienti = (TextView) findViewById(R.id.ID_ingredienti);
        proc=(TextView) findViewById(R.id.textViewTitoloProcedimento);
        ingr= (TextView) findViewById(R.id.textViewIngredient);
        txtDifficolta =(TextView) findViewById(R.id.textViewDifficolta);


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        preferito = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        timer = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        spesa = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        menu_gg = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);


        txtname.setTypeface(custom_font_3);
        txtDescrizione.setTypeface(custom_font_3);
        txtPersone.setTypeface(custom_font_3);
        txtTempoImpiegato.setTypeface(custom_font_3);
        txtCalorie.setTypeface(custom_font_3);
        txtPortata.setTypeface(custom_font_3);
        txtIngredienti.setTypeface(custom_font_3);
        proc.setTypeface(custom_font_3);
        ingr.setTypeface(custom_font_3);
        txtDifficolta.setTypeface(custom_font_3);

        txtIngredienti.setMovementMethod(new ScrollingMovementMethod());

        /*
        myDatabaseAccess = DatabaseAccess.getInstance(this);
        myDatabaseAccess.open();
        //QUESTO METODO FUNZIONA
        tempoFromDatabase = myDatabaseAccess.getTempoRicetta(nameRicetta);
        myDatabaseAccess.close();
        */

        //prendo le info dall'intent
        ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getByteArrayExtra("img_id"));
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        imageView.setImageBitmap(bitmap);
        txtname.setText(getIntent().getStringExtra("name"));
        myDatabaseAccess = DatabaseAccess.getInstance(this);
        myDatabaseAccess.open();
        txtPersone.setText(myDatabaseAccess.getNpersone(txtname.getText().toString()));
        txtCalorie.setText(myDatabaseAccess.getCalorie(txtname.getText().toString()));
        txtTempoImpiegato.setText(myDatabaseAccess.getTempoRicetta(txtname.getText().toString()));
        txtDifficolta.setText(myDatabaseAccess.getDifficolta(txtname.getText().toString()));
        txtPortata.setText(myDatabaseAccess.getPortata(txtname.getText().toString()));
        txtDescrizione.setText(myDatabaseAccess.getDescrizione(txtname.getText().toString()));
        list_ingredienti = myDatabaseAccess.getIngredienti(txtname.getText().toString());
        txtIngredienti.setText("");
        txtIngredienti.setMovementMethod(new ScrollingMovementMethod());
        for(int i=0;i<list_ingredienti.size();i++)
        {
            //String ingredienti = txtIngredienti.getText().toString() + "," + list_ingredienti.get(i);
            //txtIngredienti.setText(ingredienti);
            String ingredienti = list_ingredienti.get(i);
            txtIngredienti.append("•"+ingredienti+"\n");
        }
        myDatabaseAccess.close();

        setTitle(getIntent().getStringExtra("name"));
        nameRicetta = getIntent().getStringExtra("name");


        if(read_xml_preferito.ReadXML_Particular_Object(nameRicetta)){
            preferito.setImageResource(R.drawable.star);
            preferito.setColorFilter(Color.argb(255, 255, 235, 59));
            preferito.setActivated(true);
        }


        preferito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if(preferito.isActivated()){

                        preferito.setActivated(false);
                        //preferito.setColorFilter(Color.argb(255, 255, 255, 255));
                        preferito.setImageResource(R.drawable.white_star);

                        boolean c= write_xml_preferito.DeleteObjectToXML(nameRicetta);

                        if(c){
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(getApplicationContext(), "Ricetta eliminata dai preferiti",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(getApplicationContext(), "Ricetta NON eliminata dai preferiti",
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                    else{
                        preferito.setImageResource(R.drawable.star);
                        //preferito.setColorFilter(Color.argb(255, 255, 235, 59));
                        preferito.setActivated(true);


                        if(write_xml_preferito.WriteObjectToXML(nameRicetta)){

                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(getApplicationContext(), "Ricetta aggiunta ai preferiti",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(getApplicationContext(), "Ricetta NON aggiunta ai preferiti",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempoFromDatabase= txtTempoImpiegato.getText().toString();

                setTimeFromRicetta = new Intent(getApplicationContext(), Timer.class);
                setTimeFromRicetta.putExtra("name", nameRicetta);
                setTimeFromRicetta.putExtra("time", tempoFromDatabase);

                startActivity(setTimeFromRicetta);
            }
        });


        spesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(getApplicationContext(), "Ingredienti aggiunti alla lista della spesa",
                        Toast.LENGTH_LONG).show();

                //prendere il nome della ricetta e gli ingredienti e passarli alla lista dela spesa


            }
        });


        menu_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(getApplicationContext(), "Ricetta inserita nel menù edl giorno",
                        Toast.LENGTH_LONG).show();

                //prendere il nome della ricetta e gli ingredienti e passarli alla lista dela spesa
            }
        });
    }

}
