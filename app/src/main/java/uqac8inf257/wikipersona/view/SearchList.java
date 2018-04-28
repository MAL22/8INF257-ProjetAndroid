package uqac8inf257.wikipersona.view;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.controller.MainController;
import uqac8inf257.wikipersona.data.Shadow;

public class SearchList extends AppCompatActivity {


    private TextView mTxtResultHeader;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle extras = getIntent().getExtras();
        final ArrayList<Shadow> shadows = (ArrayList<Shadow>) extras.getSerializable("shadows");
        final String search = extras.getString("search");
        final MainController mc = new MainController(this, this);

        toolbar = findViewById(R.id.searchToolbar);
        toolbar.setTitle("Result(s) for " + search + " :");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView searchView = findViewById(R.id.searchView);

        SearchListAdapter adapter = new SearchListAdapter(this, shadows);

        adapter.setOnItemClickListener(new SearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                mc.displayShadow(shadows.get(position));
            }
        });

        searchView.setAdapter(adapter);

        searchView.setHasFixedSize(true);

        searchView.setLayoutManager(new LinearLayoutManager(this));


    }
}


