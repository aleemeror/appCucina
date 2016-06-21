package com.example.studente.appcucinaproject.Spesa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;

public class InserisciSpesa extends AppCompatActivity {
    EditText nomeSpesa;
    Button bt;
    ImageButton bt_inserisci_ingrediente;
    MultiAutoCompleteTextView ins_ingrediente;
    TextView tv;
    String Ingredienti;


    ArrayList<String> titleSpesa = new ArrayList<>();
    ArrayList<SpesaObject> spesaListInsert = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_spesa_insert);

        nomeSpesa = (EditText) findViewById(R.id.editText8);
        bt = (Button) findViewById(R.id.buttonInserisciSpesa);
        bt_inserisci_ingrediente = (ImageButton) findViewById(R.id.imageButtonInsIngrediente);
        ins_ingrediente = (MultiAutoCompleteTextView)findViewById(R.id.editText7);
        tv = (TextView)findViewById(R.id.textView7);

        String[] ingredienti_array = getResources().getStringArray(R.array.ingredienti);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, ingredienti_array);

        setTitle("Inserisci Spesa");

        spesaListInsert= getIntent().getParcelableArrayListExtra("listaSpese");


        ins_ingrediente.setAdapter(adapter);
        ins_ingrediente.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        bt_inserisci_ingrediente.setOnClickListener(new View.OnClickListener(){ //Inerisco un ingrediente ad uno ad uno pigiando il pulsante
            @Override
            public void onClick(View v) {
                String str = ins_ingrediente.getText().toString();

                tv.append('\n'+str);    //faccio append degli ingredienti
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!nomeSpesa.getText().toString().isEmpty() && !tv.getText().toString().isEmpty()){

                    SpesaObject sp = new SpesaObject(nomeSpesa.getText().toString(),tv.getText().toString());

                    spesaListInsert.add(sp);


                    //titleSpesa.add("Spesa: "+nomeSpesa.getText().toString());
                    //Ingredienti = tv.getText().toString();

                    //startiamo la activity
                    Intent intent = new Intent(InserisciSpesa.this, Spesa.class);     //  Intent intent = new Intent(a, Ricetta.class);
                    //intent.putExtra("titleSpesa",titleSpesa);
                    //intent.putExtra("ingredientiSpesa",Ingredienti);
                    intent.putParcelableArrayListExtra("listaDiSpeseInput",spesaListInsert);    //ERROREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
                    startActivity(intent); //starta l'activity- M - a.startActivity(...);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "Campi incompleti",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}