package uqac8inf257.wikipersona.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.controller.MainController;
import uqac8inf257.wikipersona.data.Arcana;
import uqac8inf257.wikipersona.data.DamageType;
import uqac8inf257.wikipersona.data.Personality;


public class MainActivity extends AppCompatActivity {

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

        expListView = findViewById(R.id.lvExp);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add("Personalities");
        listDataHeader.add("Arcanas");
        listDataHeader.add("Resistances");
        listDataHeader.add("Weaknesses");

        ArrayList<String> personalities = new ArrayList<>();
        for (Personality personality : mainController.getPersonalities()) {
            personalities.add(personality.getName());
        }

        ArrayList<String> arcanas = new ArrayList<>();
        for (Arcana arcana : mainController.getArcanas()) {
            arcanas.add(arcana.getName());
        }

        ArrayList<String> resistances = new ArrayList<>();
        ArrayList<String> weaknesses = new ArrayList<>();
        for (DamageType damageType : mainController.getDamageTypes()) {
            resistances.add(damageType.getName());
            weaknesses.add(damageType.getName());
        }

        listDataChild.put(listDataHeader.get(0), personalities);
        listDataChild.put(listDataHeader.get(1), arcanas);
        listDataChild.put(listDataHeader.get(2), resistances);
        listDataChild.put(listDataHeader.get(3), weaknesses);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                //Toast.makeText(getApplicationContext(),listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();

                switch (groupPosition) {
                    case 0:
                        mainController.searchByPersonality(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                        break;
                    case 1:
                        mainController.searchByArcana(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                        break;

                    case 2:
                        mainController.searchByResistances(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                        break;

                    case 3:
                        mainController.searchByWeaknesses(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                        break;

                    default:

                        break;
                }

                return false;
            }
        });

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

    public void onClickEventHandler(View v) {
        switch (v.getId()) {
            case R.id.btnRandom:
                Log.v("wiki", "btnRandom_onClick");
                mainController.displayRandomShadow();
                break;
            case R.id.nav_header_searchButton:
                //Toast.makeText(getApplicationContext(),"Click!",Toast.LENGTH_SHORT).show();
                EditText search = findViewById(R.id.nav_header_searchPersonaEditText);

                if (search.getText() != null) {
                    mainController.searchShadows(search.getText().toString());
                }

                break;
            default:
                break;
        }
    }
}
