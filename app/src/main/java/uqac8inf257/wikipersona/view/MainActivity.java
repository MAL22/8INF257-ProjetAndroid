package uqac8inf257.wikipersona.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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

    private Toolbar toolbar;
    private EditText editText;

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

        toolbar = findViewById(R.id.mainToolbar);
        toolbar.setTitle("Pocket Persona");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationDrawer.openDrawer(GravityCompat.START);
            }
        });

        expListView = findViewById(R.id.lvExp);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        editText = header.findViewById(R.id.nav_header_searchPersonaEditText);

       /* editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && editText.getText() != null || editText.getText().toString() != ""){
                    mainController.searchShadows(editText.getText().toString().trim());
                }

            }
        });*/

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        mainController.searchShadows(v.getText().toString().trim());
                        return true;
                    }
                }
                return false;
            }
        });

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

        for (int i = 0; i < listDataHeader.size(); i++) {
            expListView.expandGroup(i);
        }


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
                    mainController.searchShadows(search.getText().toString().trim());
                }

                break;
            default:
                Log.i("wiki", "Click!");
                break;
        }
    }
}
