package uqac8inf257.wikipersona;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import uqac8inf257.wikipersona.model.MainController;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    MainController mainController;
    private DrawerLayout navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(this, this);

        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        switch(id)
        {
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
        }

        return true;
    }

    public void btnRandom_onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRandom:
                Log.v("wiki", "btnRandom_onClick");
                //mainController.displayRandomShadow();
                mainController.displayAllShadows();
                break;

            default:
                break;
        }
    }
}
