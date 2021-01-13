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
import com.example.covidtracker.Adapter.StateAdapter;
import com.example.covidtracker.Model.StateModel;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Home_Fragment extends Fragment {

    private ArrayList<StateModel> arrayList;
    RecyclerView recyclerView;



    private  TextView mactive_state_cases;
    private  TextView mactive_cases;
    private  TextView mrecover_state_cases;
    private  TextView mdeath_state_cases;
    private  TextView mt2;
    private  TextView mt3;
    private  TextView mt4;
    CardView cardView1,cardView2,cardView3,cardView4;




    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
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
       view= inflater.inflate(R.layout.fragment_home_, container, false);

        cardView1=view.findViewById(R.id.card1);
        cardView2=view.findViewById(R.id.card2);
        cardView3=view.findViewById(R.id.card3);
        cardView4=view.findViewById(R.id.card4);

       viewinint();
       getDataApi();

       getstatedataApi();

       adaptersetup();

       return view;

    }

    private void getstatedataApi() {

        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());

        String url="https://api.rootnet.in/covid19-in/stats/latest";

        arrayList=new ArrayList<>();


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("regional");

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject data=jsonArray.getJSONObject(i);

                        arrayList.add(new StateModel(data.getString("loc"),data.getString("totalConfirmed")));


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

     private void adaptersetup()
    {
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StateAdapter stateAdapter=new StateAdapter(arrayList);

        recyclerView.setAdapter(stateAdapter);

    }



    private void getDataApi()
    {



        String url="https://api.covid19india.org/data.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONArray jsonArray=response.getJSONArray("statewise");
                    JSONObject jsonObject= jsonArray.getJSONObject(0);

                    String active_state_cases=jsonObject.getString("confirmed");
                    String t2=jsonObject.getString("deltaconfirmed");
                  mactive_state_cases.setText(active_state_cases);
                  mt2.setText(t2);


                    String active_cases=jsonObject.getString("active");
                    mactive_cases.setText(active_cases);


                    String recover_state_cases=jsonObject.getString("recovered");
                    String t3=jsonObject.getString("deltarecovered");
                    mrecover_state_cases.setText(recover_state_cases);
                    mt3.setText(t3);


                    String death_state_cases=jsonObject.getString("deaths");
                    String t4=jsonObject.getString("deltadeaths");
                    mdeath_state_cases.setText(death_state_cases);
                    mt4.setText(t4);



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

        int socketTime=7000;

        RetryPolicy retryPolicy=new DefaultRetryPolicy(socketTime,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjectRequest.setRetryPolicy(retryPolicy);
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
}


    private void viewinint()
    {
       mactive_state_cases=view.findViewById(R.id.active_state_cases);
        mactive_cases=view.findViewById(R.id.active_cases);
        mrecover_state_cases=view.findViewById(R.id.recover_state_cases);
        mdeath_state_cases=view.findViewById(R.id.death_state_cases);
        mt2=view.findViewById(R.id.t2);
        mt3=view.findViewById(R.id.t3);
        mt4=view.findViewById(R.id.t4);
        recyclerView=view.findViewById(R.id.statelist);



    }
}