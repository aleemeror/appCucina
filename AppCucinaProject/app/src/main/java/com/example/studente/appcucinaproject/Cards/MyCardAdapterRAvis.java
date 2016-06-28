package com.example.studente.appcucinaproject.Cards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricetta.Ricetta;

import java.util.ArrayList;

/**
 * Created by Mattia on 21/06/2016.
 */
public class MyCardAdapterRAvis extends RecyclerView.Adapter<MyCardAdapterRAvis.ContactViewHolder> {
    ArrayList<String> ricette = new ArrayList<String>();
    //Activity a;

    Context ctx;//M

    public MyCardAdapterRAvis(ArrayList<String> ricette, Context a){     //M-Activity a

        this.ricette= ricette;
        this.ctx=a;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        String CON = ricette.get(position);
        //holder.person_img.setImageResource(CON.getImageRicetta());
        holder.person_name.setText(ricette.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();  //prendo le info della posizione
                String ricetta = ricette.get(position);

                //startiamo la activity
                Intent intent = new Intent(ctx, Ricetta.class);     //  Intent intent = new Intent(a, Ricetta.class);
                //intent.putExtra("img_id",ricetta.getImageRicetta());
                intent.putExtra("nameRicVis",ricetta);
                ctx.startActivity(intent); //starta l'activity- M - a.startActivity(...);
            }

        });
    }

    @Override
    public int getItemCount() {
        return ricette.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        ImageView person_img;
        TextView person_name;

        public ContactViewHolder(View view){
            super(view);
            person_img = (ImageView) view.findViewById(R.id.imgMsg); //M
            person_name = (TextView) view.findViewById(R.id.txtMesg); //M

        }

    }

}