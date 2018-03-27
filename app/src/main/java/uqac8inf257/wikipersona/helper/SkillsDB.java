package uqac8inf257.wikipersona.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.Vector;

import uqac8inf257.wikipersona.model.Arcana;
import uqac8inf257.wikipersona.model.Skill;

/**
 * Created by mimil on 2018-03-09.
 */

public class SkillsDB {

    private SQLiteDatabase myDatabase;

    public SkillsDB(SQLiteDatabase myDatabase) {

    }

    protected Vector<Skill> executeQuery(String query, String params[]) {
        Cursor cursor;

        if (params == null || params.length == 0)
            cursor = myDatabase.rawQuery(query, null);
        else
            cursor = myDatabase.rawQuery(query, params);

        // Initialisation des structures requises pour l'obtention des données
        Vector<Skill> lst = new Vector<>();
        String cols[] = cursor.getColumnNames();

        Log.v("wiki", cursor.getCount() + " éléments.");
        Log.v("wiki", cols.length + " colonnes.");

        for (String col : cols) {
            Log.v("wiki", "Nom : " + col);
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Skill skill = new Skill(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                lst.add(skill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lst;
    }
}
