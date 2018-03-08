package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import android.database.SQLException;

import java.util.Vector;

import uqac8inf257.wikipersona.helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testClick(View v) {
        Log.v("wiki", "CLICK!");

        DatabaseHelper db = new DatabaseHelper(this);

        try {
            db.createDatabase();
        } catch (IOException e) {
            //throw new Error("Unable create db");
            e.printStackTrace();
        }

        try {
            db.openDatabase();
        } catch (SQLException sqle) {
            //throw sqle;
            sqle.printStackTrace();
        }
        Vector result = db.getDatabaseData();

        StringBuilder stuff = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            Vector inner = ((Vector) result.get(i));
            for (int j = 0; j < inner.size(); j++) {
                stuff.append(inner.get(j));
            }
        }
        Log.v("wiki",stuff.toString());

        Toast toast = Toast.makeText(getApplicationContext(), stuff, Toast.LENGTH_LONG);
        toast.show();

    }
}
