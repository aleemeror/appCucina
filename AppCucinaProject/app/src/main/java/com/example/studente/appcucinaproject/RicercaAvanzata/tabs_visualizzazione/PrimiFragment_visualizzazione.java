package com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studente.appcucinaproject.Cards.MyCardAdapterRAvis;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrimiFragment_visualizzazione extends Fragment {

    private ArrayList<String> listResults = new ArrayList<>();
    private ArrayList<String> listPrimi = new ArrayList<>();
    private ArrayList<String> listPrimiTemp = new ArrayList<>();
    private DatabaseAccess myDatabaseAccess;
    boolean creato = false;

    public PrimiFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_primi_fragment_visualizzazione, container, false);

        listResults = ((Activity)container.getRootView().getContext()).getIntent().getStringArrayListExtra("risultati");

        if(listResults != null && !listResults.isEmpty()) {
            if(!creato) {
                myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());

                myDatabaseAccess.open();
                listPrimi = myDatabaseAccess.getRicettaPrimo();
                myDatabaseAccess.close();

                for (int i = 0; i < listPrimi.size(); i++) {

                    for (int j = 0; j < listResults.size(); j++) {
                        if (listPrimi.get(i).equals(listResults.get(j))) {
                            listPrimiTemp.add(listResults.get(j));
                        }
                    }

                }
                creato = true;
            }

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_primi_RAV);
            rv.setHasFixedSize(true);
            MyCardAdapterRAvis adapter = new MyCardAdapterRAvis(listPrimiTemp, this.getContext());
            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
        } else{
            Toast.makeText(getContext(), "Nessun risultato", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

}
