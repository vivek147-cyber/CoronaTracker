package com.example.covidtracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.Adapter.HelpAdapter;
import com.example.covidtracker.Adapter.StateAdapter;
import com.example.covidtracker.Model.HelpModel;
import com.example.covidtracker.Model.StateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Aware_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Aware_Fragment extends Fragment {

    private ArrayList<HelpModel> arrayList;
    RecyclerView recyclerView;
    Button mdial;
    TextView mhelpno;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Aware_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Aware_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Aware_Fragment newInstance(String param1, String param2) {
        Aware_Fragment fragment = new Aware_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aware_, container, false);

        recyclerView=view.findViewById(R.id.helplist);
        mdial=view.findViewById(R.id.dial);
        mhelpno=view.findViewById(R.id.helpno);

        adaptersetup();
        gethelpdataapi();


        return view;
    }



    private void gethelpdataapi() {

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        String url="https://api.rootnet.in/covid19-in/contacts";

        arrayList=new ArrayList<>();


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONObject("contacts").getJSONArray("regional");

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject data=jsonArray.getJSONObject(i);

                        arrayList.add(new HelpModel(data.getString("loc"),data.getString("number")));


                    }adaptersetup();




                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(getActivity(),""+e,Toast.LENGTH_LONG).show();

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.i("tag",String.valueOf(error));


                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

            }
        });



        requestQueue.add(stringRequest);




    }

    private void adaptersetup() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HelpAdapter helpAdapter=new HelpAdapter(arrayList);

        recyclerView.setAdapter(helpAdapter);
    }
}