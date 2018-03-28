package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import android.database.SQLException;

import java.util.Vector;

import uqac8inf257.wikipersona.data.Skill;
import uqac8inf257.wikipersona.database.DatabaseHelper;
import uqac8inf257.wikipersona.data.DamageType;
import uqac8inf257.wikipersona.data.Shadow;

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

    public void btnRandom_onClick(View v) {
        Log.v("wiki", "btnRandom_onClick");

        switch (v.getId()) {
            case R.id.btnRandom:

                break;

            default:
                break;
        }
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

            Vector<Skill> skills = sh.getSkills();
            Vector<DamageType> resistances = sh.getResistances();
            Vector<DamageType> weaknesses = sh.getWeaknesses();
            for (int j = 0; j < skills.size(); j++) {
                Skill skill = skills.get(j);
                stuff.append(skill.getId());
                stuff.append(skill.getName());
                stuff.append(skill.getEffect());
            }
            for (int j = 0; j < resistances.size(); j++) {
                DamageType resistance = resistances.get(j);
                stuff.append(resistance.getId());
                stuff.append(resistance.getName());
            }
            for (int j = 0; j < weaknesses.size(); j++) {
                DamageType weakness = weaknesses.get(j);
                stuff.append(weakness.getId());
                stuff.append(weakness.getName());
            }
        }

        db.closeDatabase();
        Log.v("wiki", stuff.toString());

        Toast toast = Toast.makeText(getApplicationContext(), stuff, Toast.LENGTH_LONG);
        toast.show();

    }
}
