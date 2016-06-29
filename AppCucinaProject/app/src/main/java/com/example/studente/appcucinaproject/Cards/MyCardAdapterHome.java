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
 * Created by Mattia on 06/05/2016.
 */
public class MyCardAdapterHome extends RecyclerView.Adapter<MyCardAdapterHome.ContactViewHolder> {
    ArrayList<RicettaDetails> ricette = new ArrayList<RicettaDetails>();
    //Activity a;

    Context ctx;//M

    public MyCardAdapterHome(ArrayList<RicettaDetails> ricette, Context a){     //M-Activity a

        this.ricette= ricette;
        this.ctx=a;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_hp,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        RicettaDetails CON = ricette.get(position);
        holder.person_img.setImageBitmap(CON.getImageRicetta());
        holder.person_name.setText(CON.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();  //prendo le info della posizione
                RicettaDetails ricetta = ricette.get(position);

                //startiamo la activity
                Intent intent = new Intent(ctx, Ricetta.class);     //  Intent intent = new Intent(a, Ricetta.class);
                intent.putExtra("img_id",ricetta.getImageRicetta());
                intent.putExtra("name",ricetta.getTitle());
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
            person_img = (ImageView) view.findViewById(R.id.imgMsgHP); //M
            person_name = (TextView) view.findViewById(R.id.txtMesgHP); //M

        }

    }

}
