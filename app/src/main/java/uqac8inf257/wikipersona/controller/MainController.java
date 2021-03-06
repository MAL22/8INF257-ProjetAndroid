package uqac8inf257.wikipersona.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import uqac8inf257.wikipersona.data.Arcana;
import uqac8inf257.wikipersona.data.DamageType;
import uqac8inf257.wikipersona.data.Personality;
import uqac8inf257.wikipersona.view.PersonaActivity;
import uqac8inf257.wikipersona.view.PersonaInformations;
import uqac8inf257.wikipersona.view.SearchList;
import uqac8inf257.wikipersona.data.Shadow;
import uqac8inf257.wikipersona.database.DatabaseHelper;

/**
 * Created by mimil on 2018-03-27.
 */

public class MainController {

    private DatabaseHelper db;
    private Activity activity;

    public MainController(Context context, Activity activity) {
        this.activity = activity;
        db = new DatabaseHelper(context);
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

    private void launchShadowIntentV2(Shadow shadow) {
        Intent intent = new Intent(activity, PersonaInformations.class);
        intent.putExtra("shadow", shadow);
        activity.startActivity(intent);
    }

    private void launchShadowIntent(Shadow shadow) {
        launchShadowIntentV2(shadow);
        /*Intent intent = new Intent(activity, PersonaActivity.class);
        intent.putExtra("shadow", shadow);
        activity.startActivity(intent);*/
    }

    private void launchSearchIntent(ArrayList<Shadow> shadows, String search) {
        Intent intent = new Intent(activity, SearchList.class);
        intent.putExtra("shadows", shadows);
        intent.putExtra("search", search);
        activity.startActivity(intent);
    }

    public void searchShadows(String search) {
        openDatabase();
        ArrayList<Shadow> shadows = new ArrayList<>();

        shadows.addAll(db.getDBShadow().byName(search));

        launchSearchIntent(shadows, search);
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

    public void displayRandomShadow() {
        displayRandomShadowV2();
      /*  openDatabase();
        Shadow shadow = db.getDBShadow().getRandom();
        closeDatabase();
        launchShadowIntent(shadow);*/
    }

    public void displayRandomShadowV2() {
        openDatabase();
        Shadow shadow = db.getDBShadow().getRandom();
        closeDatabase();
        launchShadowIntentV2(shadow);
    }

    public void displayAllShadows() {
        openDatabase();
        ArrayList<Shadow> shadows = db.getDBShadow().getAll();
        closeDatabase();

        launchSearchIntent(shadows, null);
    }

    public void searchByPersonality(String personality) {
        openDatabase();
        ArrayList<Shadow> shadows = db.getDBShadow().byPersonality(personality);
        closeDatabase();

        launchSearchIntent(shadows, personality);
    }

    public void searchByArcana(String arcana) {
        openDatabase();
        ArrayList<Shadow> shadows = db.getDBShadow().byArcana(arcana);
        closeDatabase();

        launchSearchIntent(shadows, arcana);
    }

    public void searchByResistances(String damageType) {
        openDatabase();
        ArrayList<Shadow> shadows = db.getDBShadow().byResistance(damageType);
        closeDatabase();

        launchSearchIntent(shadows, damageType);
    }

    public void searchByWeaknesses(String damageType) {
        openDatabase();
        ArrayList<Shadow> shadows = db.getDBShadow().byWeakness(damageType);
        closeDatabase();

        launchSearchIntent(shadows, damageType);
    }

    public ArrayList<Personality> getPersonalities() {
        openDatabase();
        ArrayList<Personality> personalities = db.getDBPersonality().getAll();
        closeDatabase();

        return personalities;
    }

    public ArrayList<Arcana> getArcanas() {
        openDatabase();
        ArrayList<Arcana> arcanas = db.getDBArcana().getAll();
        closeDatabase();

        return arcanas;
    }

    public ArrayList<DamageType> getDamageTypes() {
        openDatabase();
        ArrayList<DamageType> damageTypes = db.getDBDamageType().getAll();
        closeDatabase();

        return damageTypes;
    }
}
