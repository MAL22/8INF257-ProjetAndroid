package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.Vector;

import uqac8inf257.wikipersona.model.SearchController;
import uqac8inf257.wikipersona.model.SearchListAdapter;
import uqac8inf257.wikipersona.data.Shadow;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        String jsonMyObj;
        Bundle extras = getIntent().getExtras();
        jsonMyObj = extras.getString("shadows");
        Vector<Shadow> shadows = new Gson().fromJson(jsonMyObj, Vector.class);

        SearchController sc = new SearchController(this);
        shadows = sc.searchShadows();

        RecyclerView searchView = findViewById(R.id.searchView);

        SearchListAdapter adapter = new SearchListAdapter(this, shadows);

        searchView.setAdapter(adapter);

        searchView.setLayoutManager(new LinearLayoutManager(this));
    }
}
