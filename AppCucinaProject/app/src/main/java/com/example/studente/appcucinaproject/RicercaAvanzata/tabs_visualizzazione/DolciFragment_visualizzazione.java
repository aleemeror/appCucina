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
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricetta.Ricetta;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DolciFragment_visualizzazione extends Fragment {

    private ArrayList<String> listResults = new ArrayList<>();
    private ArrayList<RicettaDetails> listDolci = new ArrayList<>();
    private ArrayList<RicettaDetails> listDolciTemp = new ArrayList<>();
    private DatabaseAccess myDatabaseAccess;
    boolean creato = false;


    public DolciFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dolci_fragment_visualizzazione, container, false);

        listResults = ((Activity)container.getRootView().getContext()).getIntent().getStringArrayListExtra("risultati");

        if(listResults != null && !listResults.isEmpty()) {
            if(!creato) {
                myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());

                myDatabaseAccess.open();
                listDolci = myDatabaseAccess.getRicettaDolciConImage();
                myDatabaseAccess.close();

                for (int i = 0; i < listDolci.size(); i++) {

                    for (int j = 0; j < listResults.size(); j++) {
                        if (listDolci.get(i).getTitle().equals(listResults.get(j))) {
                            //listAntipastiTemp.add(listResults.get(j));
                            listDolciTemp.add(listDolci.get(i));
                        }
                    }

                }
                creato = true;
            }


            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_dolci_RAV);
            rv.setHasFixedSize(true);
            MyCardAdapterRAvis adapter = new MyCardAdapterRAvis(listDolciTemp, this.getContext());
            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
        } else{
            Toast.makeText(getContext(), "Nessun risultato", Toast.LENGTH_SHORT).show();
        }


        return rootView;
    }

}
