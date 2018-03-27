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
import uqac8inf257.wikipersona.model.DamageType;
import uqac8inf257.wikipersona.model.Shadow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        DatabaseHelper db = new DatabaseHelper(this);

        try {
            db.createDatabase();
            db.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        Vector<Shadow> result = db.getDBShadow().byArcana("Fool");

        StringBuilder stuff = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            Shadow sh = result.get(i);
            stuff.append(sh.getId());
            stuff.append(sh.getFakeName());
            stuff.append(sh.getRealName());
            stuff.append(sh.getHistory());
            stuff.append(sh.getArcana().getName());
            stuff.append(sh.getPersonality().getName());
            stuff.append(sh.getStats().getStrength());
            stuff.append(sh.getStats().getMagic());
            stuff.append(sh.getStats().getLuck());
            stuff.append(sh.getStats().getAgility());
            stuff.append(sh.getStats().getEndurance());

            Vector<DamageType> resist = sh.getResistances();
            Vector<DamageType> weak = sh.getWeaknesses();
            for (int j = 0; j < resist.size(); j++) {
                DamageType dmgType = resist.get(j);
                stuff.append(dmgType.getId());
                stuff.append(dmgType.getName());
            }
            for (int j = 0; j < weak.size(); j++) {
                DamageType dmgType = weak.get(j);
                stuff.append(dmgType.getId());
                stuff.append(dmgType.getName());
            }
        }

        db.closeDatabase();
        Log.v("wiki", stuff.toString());

        Toast toast = Toast.makeText(getApplicationContext(), stuff, Toast.LENGTH_LONG);
        toast.show();

    }
}
