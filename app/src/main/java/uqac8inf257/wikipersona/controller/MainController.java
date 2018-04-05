package uqac8inf257.wikipersona.controller;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Vector;

import uqac8inf257.wikipersona.MainActivity;
import uqac8inf257.wikipersona.PersonaActivity;
import uqac8inf257.wikipersona.data.DamageType;
import uqac8inf257.wikipersona.data.Shadow;
import uqac8inf257.wikipersona.data.Skill;
import uqac8inf257.wikipersona.database.DatabaseHelper;

/**
 * Created by mimil on 2018-03-27.
 */

public class MainController {

    private DatabaseHelper db;
    private MainActivity main;

    public MainController(Context context, MainActivity main) {
        this.main = main;
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

    public void displayRandomShadow() {
        openDatabase();
        Shadow result = db.getDBShadow().getRandom();
        closeDatabase();

        Intent intent = new Intent(main, PersonaActivity.class);
        intent.putExtra("shadow", new Gson().toJson(result));
        main.startActivity(intent);

    }
}
