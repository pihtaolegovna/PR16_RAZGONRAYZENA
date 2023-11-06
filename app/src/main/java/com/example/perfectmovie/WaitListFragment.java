package com.example.perfectmovie;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaitListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaitListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textss;

    private ProgressBar spinner;
    public WaitListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaitListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaitListFragment newInstance(String param1, String param2) {
        WaitListFragment fragment = new WaitListFragment();
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

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_list, container, false);
        textView = view.findViewById(R.id.textView);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        String apiUrl = "https://kinopoiskapiunofficial.tech/api/v2.2/films?order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1000&yearTo=3000&page=1";


        new GetApiAsync(textView, apiUrl) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    try {
                        MovieList movieList = new Gson().fromJson(result, MovieList.class);
                        ItemAdapter itemAdapter = new ItemAdapter(movieList.getItems(), getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(itemAdapter);
                        textView.setText("");
                        spinner.setVisibility(View.GONE);
                    } catch (JsonSyntaxException e) {
                        textView.setText("JSON Parsing Error");
                    }
                } else {
                    textView.setText("An error occurred");
                }
            }
        }.execute();

        return view;
    }


}