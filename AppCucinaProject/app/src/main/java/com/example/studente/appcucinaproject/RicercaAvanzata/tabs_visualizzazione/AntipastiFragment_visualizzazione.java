package com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione;


import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntipastiFragment_visualizzazione extends Fragment {

    private ArrayList<String> listResults = new ArrayList<>();

    public AntipastiFragment_visualizzazione() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //listResults = getActivity().getIntent().getStringArrayListExtra("risultati");


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antipasti_fragment_visualizzazione, container, false);
    }

}
