package uqac8inf257.wikipersona.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.controller.SearchController;
import uqac8inf257.wikipersona.data.Shadow;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle extras = getIntent().getExtras();
        final ArrayList<Shadow> shadows = (ArrayList<Shadow>) extras.getSerializable("shadows");
        final SearchController sc = new SearchController(this, this);

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


