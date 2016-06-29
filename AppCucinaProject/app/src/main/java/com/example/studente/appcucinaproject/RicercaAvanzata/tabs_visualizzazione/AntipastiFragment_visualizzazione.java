package com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.Cards.MyCardAdapterRAvis;
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntipastiFragment_visualizzazione extends Fragment {

    private ArrayList<String> listResults = new ArrayList<>();
    //private ArrayList<String> listAntipasti = new ArrayList<>();
    private ArrayList<RicettaDetails> listAntipasti = new ArrayList<RicettaDetails>();
    private ArrayList<RicettaDetails> listAntipastiTemp = new ArrayList<RicettaDetails>(); //String
    private DatabaseAccess myDatabaseAccess;
    boolean creato = false;

    public AntipastiFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_antipasti_fragment_visualizzazione, container, false);

        listResults = ((Activity)container.getRootView().getContext()).getIntent().getStringArrayListExtra("risultati");

        if(listResults != null && !listResults.isEmpty()) {

            if(!creato) {
                myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());

                myDatabaseAccess.open();
                listAntipasti = myDatabaseAccess.getRicettaAntipastoConImage();
                myDatabaseAccess.close();

                for (int i = 0; i < listAntipasti.size(); i++) {

                    for (int j = 0; j < listResults.size(); j++) {
                        if (listAntipasti.get(i).getTitle().equals(listResults.get(j))) {
                            //listAntipastiTemp.add(listResults.get(j));
                            listAntipastiTemp.add(listAntipasti.get(i));
                        }
                    }

                }
                creato = true;
            }

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_antipasti_RAV);
            rv.setHasFixedSize(true);
            MyCardAdapterRAvis adapter = new MyCardAdapterRAvis(listAntipastiTemp, this.getContext());
            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
        } else{
            Toast.makeText(getContext(), "Nessun risultato", Toast.LENGTH_SHORT).show();
        }


        return rootView;
    }

}
