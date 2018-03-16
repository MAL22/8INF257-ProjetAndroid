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
import uqac8inf257.wikipersona.helper.SelectShadow;
import uqac8inf257.wikipersona.helper.SelectShadows;
import uqac8inf257.wikipersona.model.Shadow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*try {
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
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void testClick(View v) {
        Log.v("wiki", "CLICK!");

        //SelectShadows db = new SelectShadows(this);
        SelectShadow db = new SelectShadow(this);
        Vector<Shadow> result = db.executeQuery(1);

        StringBuilder stuff = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            Shadow inner = result.get(i);
            stuff.append(inner.getId());
            stuff.append(inner.getFakeName());
            stuff.append(inner.getRealName());
            stuff.append(inner.getHistory());
            stuff.append(inner.getArcana().getName());
            stuff.append(inner.getPersonality().getName());
            stuff.append(inner.getStats().getStrength());
            stuff.append(inner.getStats().getMagic());
            stuff.append(inner.getStats().getLuck());
            stuff.append(inner.getStats().getAgility());
            stuff.append(inner.getStats().getEndurance());
        }

        Log.v("wiki", stuff.toString());

        Toast toast = Toast.makeText(getApplicationContext(), stuff, Toast.LENGTH_LONG);
        toast.show();

    }
}
