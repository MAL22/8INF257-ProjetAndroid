package uqac8inf257.wikipersona.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import uqac8inf257.wikipersona.view.PersonaActivity;
import uqac8inf257.wikipersona.view.SearchList;
import uqac8inf257.wikipersona.data.Shadow;
import uqac8inf257.wikipersona.database.DatabaseHelper;

/**
 * Created by mimil on 2018-04-11.
 */

public class SearchController {

    private DatabaseHelper db;
    private Activity activity;

    public SearchController(Context context, Activity activity) {
        db = new DatabaseHelper(context);
        this.activity = activity;
    }

    private void openDatabase() {
        try {
            db.createDatabase();
            db.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private void closeDatabase() {
        db.closeDatabase();
    }

    private void launchShadowIntent(Shadow shadow) {
        Intent intent = new Intent(activity, PersonaActivity.class);
        intent.putExtra("shadow", shadow);
        activity.startActivity(intent);
    }

    private void launchSearchIntent(ArrayList<Shadow> shadows) {
        Intent intent = new Intent(activity, SearchList.class);
        intent.putExtra("shadows", shadows);
        activity.startActivity(intent);
    }

    public void displayShadow(Shadow shadow) {
        launchShadowIntent(shadow);
    }

    public void displayShadow(int id) {
        openDatabase();
        Shadow shadow = db.getDBShadow().byID(id);
        closeDatabase();

        launchShadowIntent(shadow);
    }

    public ArrayList<Shadow> searchShadows() {
        openDatabase();
        ArrayList<Shadow> result = db.getDBShadow().getAll();
        closeDatabase();
        return result;
    }

}
