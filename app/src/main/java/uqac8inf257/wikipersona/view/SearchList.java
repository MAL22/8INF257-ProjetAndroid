package uqac8inf257.wikipersona.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.controller.MainController;
import uqac8inf257.wikipersona.data.Shadow;

public class SearchList extends AppCompatActivity {

    TextView mTxtResultHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle extras = getIntent().getExtras();
        final ArrayList<Shadow> shadows = (ArrayList<Shadow>) extras.getSerializable("shadows");
        final String search = extras.getString("search");
        final MainController mc = new MainController(this, this);

        mTxtResultHeader = findViewById(R.id.searchResultHeader);
        mTxtResultHeader.setText("Result(s) for : " + search);
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


