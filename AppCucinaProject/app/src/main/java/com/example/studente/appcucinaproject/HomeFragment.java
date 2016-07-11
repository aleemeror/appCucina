package com.example.studente.appcucinaproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    TextView tx;
    TextView frase;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Typeface custom_font_3 = Typeface.createFromAsset(getActivity().getAssets(), "TCM.TTF");

        tx = (TextView)v.findViewById(R.id.textView9);
        frase = (TextView)v.findViewById(R.id.textView10);

        tx.setTypeface(custom_font_3);
        frase.setTypeface(custom_font_3);
        frase.setTextSize(20);

        return v;
    }

}
