package com.example.studente.appcucinaproject.Ricettario.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studente.appcucinaproject.Cards.MyFragmentsCardAdapter;
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;

import java.util.ArrayList;
import java.util.List;

public class AntipastiFragment extends Fragment{

    RecyclerView recAntipasti;
    LinearLayoutManager layoutManager;
    //private ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();     MOD
    private RicettaDetails [] list = new RicettaDetails[4];
    private String[] mDataset;

    int[] images = {R.drawable.antipasto,R.drawable.antipasto,R.drawable.antipasto,R.drawable.antipasto};
    String[] title = {"Antipasto 1","Antipasto 2","Antipasto 3","Antipasto 4"};

    public AntipastiFragment() {
        // Required empty public constructor


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_antipasti, container, false);

        /*title = getResources().getStringArray(R.array.ricetta_name);
        //int count =0;
        for(int count =0; count < title.length; count++){ //String Name:title

            String Name = title[count];
            RicettaDetails ricetta = new RicettaDetails(images[count],Name);
            //count++;
            list.add(ricetta);
        }*/


        //title = getResources().getStringArray(R.array.ricetta_name);

        //int count =0;
        for(int count =0; count < title.length; count++){ //String Name:title

            RicettaDetails ricetta = new RicettaDetails(images[count],title[count]);
            //count++;
            list[count]= ricetta;
            //list.add(ricetta);
        }

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_antipasti);
        rv.setHasFixedSize(true);
        MyFragmentsCardAdapter adapter = new MyFragmentsCardAdapter(list,this.getContext()); //MyCardAdapter adapter = new MyCardAdapter(list,this.getContext());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        // Inflate the layout for this fragment
        return rootView;

    }
}