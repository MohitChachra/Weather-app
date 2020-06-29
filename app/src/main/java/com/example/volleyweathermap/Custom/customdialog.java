package com.example.volleyweathermap.Custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyweathermap.R;

public class customdialog extends Dialog {
    String[] cities;
    Context context;
    RecyclerView rec;
    OnCitySelectListener listnerd;
    public customdialog(@NonNull Context context,int LayoutResource ,String[] c,OnCitySelectListener d){
        super(context);
        this.context=context;
        this.cities=c;


        this.setContentView(LayoutResource);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.listnerd=d;
        rec=this.findViewById(R.id.rc);
    rec.setLayoutManager((new LinearLayoutManager(context)));
    rec.setAdapter(new Adapter(context,cities,listnerd));
    }




}

class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
        Context context;
        String[] cities;
        OnCitySelectListener onCitySelectListener;

    public Adapter(Context context, String[] cities,OnCitySelectListener listener) {
        this.context = context;
        this.cities = cities;
        this.onCitySelectListener=listener;
    }

    @NonNull
    @Override
    public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.card,viewGroup,false);


        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int i) {
        final int replace=i;
        holder.citytext.setText(cities[i]);
        holder.note.setText(""+cities[i].toCharArray()[0]);
        holder.citytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCitySelectListener.OnSelected(replace);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.length;
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView note,citytext;
        public Holder(@NonNull View itemView) {
            super(itemView);
            note=itemView.findViewById(R.id.note);
            citytext=itemView.findViewById(R.id.citytext);
        }
    }
}
