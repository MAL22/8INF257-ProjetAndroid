package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.Vector;

import uqac8inf257.wikipersona.model.MainController;
import uqac8inf257.wikipersona.model.SearchController;
import uqac8inf257.wikipersona.model.SearchListAdapter;
import uqac8inf257.wikipersona.data.Shadow;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

      /*  String jsonMyObj;
        Bundle extras = getIntent().getExtras();
        jsonMyObj = extras.getString("shadows");
        MainController = new Gson().fromJson(jsonMyObj, Vector.class);*/

        final SearchController sc = new SearchController(this, this);
        final Vector<Shadow> shadows = sc.searchShadows();

        RecyclerView searchView = findViewById(R.id.searchView);

        SearchListAdapter adapter = new SearchListAdapter(this, shadows);

        adapter.setOnItemClickListener(new SearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                sc.displayShadow(shadows.get(position));
            }
        });

        searchView.setAdapter(adapter);

        searchView.setHasFixedSize(true);

        searchView.setLayoutManager(new LinearLayoutManager(this));


    }
}


