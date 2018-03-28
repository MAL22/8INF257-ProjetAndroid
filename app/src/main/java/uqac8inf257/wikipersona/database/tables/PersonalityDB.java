package uqac8inf257.wikipersona.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import uqac8inf257.wikipersona.data.Personality;

/**
 * Created by mimil on 2018-03-09.
 */

public class PersonalityDB {

    private SQLiteDatabase db;

    public PersonalityDB(SQLiteDatabase db) {
        this.db = db;
    }

    private Vector<Personality> executeQuery(String query, String params[]) {
        Cursor cursor;

        if (params == null || params.length == 0)
            cursor = db.rawQuery(query, null);
        else
            cursor = db.rawQuery(query, params);

        Vector<Personality> lst = new Vector<>();
        String cols[] = cursor.getColumnNames();

        Log.v("wiki", cursor.getCount() + " éléments.");
        Log.v("wiki", cols.length + " colonnes.");

        for (String col : cols) {
            Log.v("wiki", "Nom : " + col);
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Personality personality = new Personality(
                        cursor.getInt(0),
                        cursor.getString(1)
                );

                lst.add(personality);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lst;
    }
}
