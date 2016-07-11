package com.example.studente.appcucinaproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.ReadAndWriteXML.ReadXML;
import com.example.studente.appcucinaproject.ReadAndWriteXML.WriteXML;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferitiFragment extends Fragment {


    RecyclerView recAntipasti;
    LinearLayoutManager layoutManager;
    private ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
    ReadXML read_xml= new ReadXML();
    boolean creato = false;

    public PreferitiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_preferiti, container, false);


        if(!creato) {
            list=read_xml.ReadXMLtoObject();
            creato = true;
        }

        if(list!=null){
            recAntipasti = (RecyclerView) rootView.findViewById(R.id.recview_preferiti);
            recAntipasti.setHasFixedSize(true);
            MyCardAdapter adapter = new MyCardAdapter(list, this.getContext());
            recAntipasti.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            recAntipasti.setLayoutManager(llm);
        }

        return rootView;
    }

}
