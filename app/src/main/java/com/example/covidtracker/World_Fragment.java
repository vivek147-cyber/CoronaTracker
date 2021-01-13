package com.example.covidtracker;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.Adapter.CountryAdapter;
import com.example.covidtracker.Adapter.StateAdapter;
import com.example.covidtracker.Model.CountryModel;
import com.example.covidtracker.Model.StateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link World_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class World_Fragment extends Fragment {

    private ArrayList<CountryModel> arrayList;
    private TextView mactive_state_cases;
    private  TextView mactive_cases;
    private  TextView mrecover_state_cases;
    private  TextView mdeath_state_cases;
    private  TextView mt2;
   private  TextView mt3;
    private  TextView mt4;
    View view;
    RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public World_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment World_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static World_Fragment newInstance(String param1, String param2) {
        World_Fragment fragment = new World_Fragment();
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
        view=inflater.inflate(R.layout.fragment_world_, container, false);


        viewini();
        getDataApi();
        adaptersetup();
        getcountrydataapi();
        return  view;
    }

    private void adaptersetup() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CountryAdapter countryAdapter=new CountryAdapter(arrayList);

        recyclerView.setAdapter(countryAdapter);
    }

    private void getcountrydataapi() {

        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());

        String url="https://api.covid19api.com/summary";

        arrayList=new ArrayList<>();


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("Countries");

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject data=jsonArray.getJSONObject(i);

                        arrayList.add(new CountryModel(data.getString("Country"),data.getString("TotalConfirmed")));


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

    private void viewini() {


        mactive_state_cases=view.findViewById(R.id.active_state_cases);
        mactive_cases=view.findViewById(R.id.active_cases);
        mrecover_state_cases=view.findViewById(R.id.recover_state_cases);
        mdeath_state_cases=view.findViewById(R.id.death_state_cases);
        mt2=view.findViewById(R.id.t2);
       mt3=view.findViewById(R.id.t3);
        mt4=view.findViewById(R.id.t4);
        recyclerView=view.findViewById(R.id.countrylist);
    }

    private void getDataApi() {


        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());

        String url="https://api.covid19api.com/summary";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject =new JSONObject(response).getJSONObject("Global");

                   mactive_state_cases.setText(jsonObject.getString("TotalConfirmed"));
                   mt2.setText(jsonObject.getString("NewConfirmed"));


                  // mactive_cases.setText(jsonObject.getString("active_cases"));

                   mrecover_state_cases.setText(jsonObject.getString("TotalRecovered"));
                   mt3.setText(jsonObject.getString("NewRecovered"));


                   mdeath_state_cases.setText(jsonObject.getString("TotalDeaths"));
                   mt4.setText(jsonObject.getString("NewDeaths"));


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
}



