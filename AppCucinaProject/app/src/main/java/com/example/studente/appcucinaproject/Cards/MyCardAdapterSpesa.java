package com.example.studente.appcucinaproject.Cards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Spesa.SpesaDetailsActivity;

import java.util.ArrayList;

/**
 * Created by Mattia on 06/05/2016.
 */
public class MyCardAdapterSpesa extends RecyclerView.Adapter<MyCardAdapterSpesa.ContactViewHolder> {
    ArrayList<String> Spese = new ArrayList<String>();
    //Activity a;

    Context ctx;//M
    int pos_da_eliminare=0;

    public MyCardAdapterSpesa(ArrayList<String> Spese, Context a){     //M-Activity a

        this.Spese= Spese;
        this.ctx=a;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_spesa,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        String SP = Spese.get(position);
        holder.spesa_title.setText(SP.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();  //prendo le info della posizione
                String spesa = Spese.get(position);

                //startiamo la activity
                Intent intent = new Intent(ctx, SpesaDetailsActivity.class);     //  Intent intent = new Intent(a, Ricetta.class);
                intent.putExtra("nameSpesa",spesa);
                ctx.startActivity(intent); //starta l'activity- M - a.startActivity(...);
            }

        });

        holder.image_button_delete.setOnClickListener(new View.OnClickListener() {      //Metodo per eliminare la lista delle spesa in questione, nel caso si preme la " X " rossa

            int position = holder.getAdapterPosition();     //prendo la posizione
            @Override
            public void onClick(View v) {   //quando la " X " è cliccata
                Spese.remove(position); //rimuovo la lista della spesa in questione dalla lista di spese
                notifyItemRemoved(position);    //notifico i registri che ho eliminato un item
                notifyItemRangeChanged(position, Spese.size()); //notifico i registri che ho scalato la posizione degli elementi sottostanti

            }

        });
    }

    public void removeAt(int position) {
        Spese.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, Spese.size());
    }




    @Override
    public int getItemCount() {
        return Spese.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView spesa_title;
        ImageButton image_button_delete;

        public ContactViewHolder(View view){
            super(view);
            spesa_title = (TextView) view.findViewById(R.id.txtMesgSPESA); //M
            image_button_delete = (ImageButton) view.findViewById(R.id.imageButtonDelete_spesa);
        }

    }

}