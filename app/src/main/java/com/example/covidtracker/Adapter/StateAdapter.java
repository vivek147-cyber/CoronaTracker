package com.example.covidtracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.Model.StateModel;
import com.example.covidtracker.R;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {


    public StateAdapter(ArrayList<StateModel> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<StateModel> arrayList;

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.statecase,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {

        StateModel stateModel=arrayList.get(position);

        holder.statename.setText(stateModel.getStatename());
        holder.statecase.setText(stateModel.getStatecase());

    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView statename,statecase;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statename=itemView.findViewById(R.id.statecase);
            statecase=itemView.findViewById(R.id.number);
        }
    }
}
