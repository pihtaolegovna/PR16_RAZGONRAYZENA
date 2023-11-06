package com.example.perfectmovie;

        import android.os.Bundle;

        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import com.google.gson.Gson;
        import com.google.gson.JsonSyntaxException;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;

public class RatingFragment extends Fragment {
    private TextView textView;
    private ProgressBar spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_list, container, false);
        textView = view.findViewById(R.id.textView);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        String currentMonth = dateFormat.format(calendar.getTime()).toUpperCase();

        int currentYear = calendar.get(Calendar.YEAR);

        String apiUrl = "https://kinopoiskapiunofficial.tech/api/v2.2/films/premieres?year=" + currentYear + "&month=" + currentMonth;


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