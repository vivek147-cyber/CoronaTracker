package com.example.covidtracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.Model.CountryModel;
import com.example.covidtracker.Model.StateModel;
import com.example.covidtracker.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private ArrayList<CountryModel> arrayList;

    public CountryAdapter(ArrayList<CountryModel> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.countrycase,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {


       CountryModel countryModel=arrayList.get(position);

        holder.countryname.setText(countryModel.getCountryname());
        holder.countrycase.setText(countryModel.getCountrycase());


    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryname,countrycase;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryname=itemView.findViewById(R.id.countrycase);
            countrycase=itemView.findViewById(R.id.cases);

        }
    }
}
