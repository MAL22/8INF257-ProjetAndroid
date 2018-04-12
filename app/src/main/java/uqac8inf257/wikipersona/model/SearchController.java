package uqac8inf257.wikipersona.model;

import android.content.Context;
import android.database.SQLException;

import java.io.IOException;
import java.util.Vector;

import uqac8inf257.wikipersona.data.Shadow;
import uqac8inf257.wikipersona.database.DatabaseHelper;

/**
 * Created by mimil on 2018-04-11.
 */

public class SearchController {

    private DatabaseHelper db;

    public SearchController(Context context) {
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

    public Vector<Shadow> searchShadows() {
        openDatabase();
        Vector<Shadow> result = db.getDBShadow().getAll();
        closeDatabase();
        return result;
    }

}
