package uqac8inf257.wikipersona.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.controller.MainController;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MainController mainController;
    private DrawerLayout navigationDrawer;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(this, this);

        navigationDrawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        expListView = findViewById(R.id.lvExp);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add("lol");
        listDataHeader.add("Oh no!");

        List<String> lmao = new ArrayList<>();
        lmao.add("XD");
        lmao.add("wut");

        List<String> lmao2 = new ArrayList<>();
        lmao2.add("GG");

        listDataChild.put(listDataHeader.get(0), lmao);
        listDataChild.put(listDataHeader.get(1), lmao2);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        Log.i("wiki", String.valueOf(listDataHeader.size()));
        Log.i("wiki", String.valueOf(listDataChild.size()));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (navigationDrawer.isDrawerOpen(GravityCompat.START)) {
            navigationDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* switch (id) {
            case R.id.nav_Personality:
                Log.i("wiki", "nav_Personality");
                navigationDrawer.closeDrawer(GravityCompat.START);
                mainController.displayRandomShadow();
                //startActivity(new Intent(MainActivity.this, PersonaActivity.class));
                return true;

            case R.id.nav_Weakness:
                Log.i("wiki", "nav_Weakness");
                navigationDrawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_Resistance:
                Log.i("wiki", "nav_Resistance");
                navigationDrawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_Arcana:
                Log.i("wiki", "nav_Arcana");
                navigationDrawer.closeDrawer(GravityCompat.START);
                return true;
        }*/

        return true;
    }

    public void btnRandom_onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRandom:
                Log.v("wiki", "btnRandom_onClick");
                //mainController.displayRandomShadow();
                mainController.displayRandomShadow();
                break;

            default:
                break;
        }
    }
}
