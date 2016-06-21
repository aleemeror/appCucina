package com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private DatabaseAccess myDatabaseAccess;

    public PrimiFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_primi_fragment_visualizzazione, container, false);

        listResults = getActivity().getIntent().getStringArrayListExtra("risultati");

        myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());
        myDatabaseAccess.open();
        for(int i=0;i<listResults.size();i++){
            listPrimi.add(myDatabaseAccess.getRicettaPrimiVisualizzazione(listResults.get(i)));
        }
        myDatabaseAccess.close();

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_primi_RAV);
        rv.setHasFixedSize(true);
        MyCardAdapterRAvis adapter = new MyCardAdapterRAvis(listPrimi, this.getContext());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }

}
