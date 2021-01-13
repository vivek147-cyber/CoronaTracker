package com.example.covidtracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.Model.HelpModel;
import com.example.covidtracker.Model.StateModel;
import com.example.covidtracker.R;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {

    private ArrayList<HelpModel> arrayList;

    public HelpAdapter(ArrayList<HelpModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HelpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.helpline,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpAdapter.ViewHolder holder, int position) {

        HelpModel helpModel=arrayList.get(position);

        holder.stateName.setText(helpModel.getStateName());
        holder.helpno.setText(helpModel.getHelpno());

    }

    @Override
    public int getItemCount() {
         return arrayList!=null?arrayList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName,helpno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName=itemView.findViewById(R.id.statename);
            helpno=itemView.findViewById(R.id.helpno);
        }
    }
}
