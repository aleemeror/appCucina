package com.example.studente.appcucinaproject.Ricettario.tabs;

/**
 * Created by Mattia on 22/04/2016.
 */
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;
import java.util.List;


public class DolciFragment extends Fragment{

    RecyclerView recAntipasti;
    LinearLayoutManager layoutManager;
    private ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
    boolean creato = false;

    int[] images = {R.drawable.dolce,R.drawable.dolce,R.drawable.dolce,R.drawable.dolce};
    DatabaseAccess databaseAccess;


    public DolciFragment(){
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dolci, container, false);

        if(!creato) {
            databaseAccess = DatabaseAccess.getInstance(this.getContext());

            databaseAccess.open();
            list= databaseAccess.getRicettaDolciConImage();
            databaseAccess.close();

            creato = true;
        }
            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recview_dolci);
            rv.setHasFixedSize(true);
            MyCardAdapter adapter = new MyCardAdapter(list, this.getContext());
            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);

        // Inflate the layout for this fragment
        return rootView;
    }
}





