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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferitiFragment extends Fragment {


    RecyclerView recAntipasti;
    LinearLayoutManager layoutManager;
    private ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
    private RicettaDetails[] mDataset;
    boolean creato = false;

    int[] images = {R.drawable.dolce,R.drawable.dolce,R.drawable.dolce,R.drawable.dolce};

    public PreferitiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_preferiti, container, false);


        if(!creato) {
            //list.clear();
            String[] title = getResources().getStringArray(R.array.ricetta_name);
            int count = 0;
            for (String Name : title) {

                RicettaDetails ricetta = new RicettaDetails(images[count], Name);
                count++;
                list.add(ricetta);
            }

            creato = true;
        }


        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_preferiti);
        rv.setHasFixedSize(true);
        MyCardAdapter adapter = new MyCardAdapter(list, this.getContext());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return rootView;
    }

}
