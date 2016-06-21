package com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione;


import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.Cards.MyCardAdapterRAvis;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntipastiFragment_visualizzazione extends Fragment {

    private ArrayList<String> listResults = new ArrayList<>();
    private ArrayList<String> listAntipasti = new ArrayList<>();
    private DatabaseAccess myDatabaseAccess;

    public AntipastiFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_antipasti_fragment_visualizzazione, container, false);

        listResults = getActivity().getIntent().getStringArrayListExtra("risultati");

        myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());
        myDatabaseAccess.open();
        for(int i=0;i<listResults.size();i++){
            listAntipasti.add(myDatabaseAccess.getRicettaAntipastoVisualizzazione(listResults.get(i)));
        }
        myDatabaseAccess.close();

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_antipasti_RAV);
        rv.setHasFixedSize(true);
        MyCardAdapterRAvis adapter = new MyCardAdapterRAvis(listAntipasti, this.getContext());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }

}
