package uqac8inf257.wikipersona.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

import uqac8inf257.wikipersona.data.DamageType;

/**
 * Created by mimil on 2018-03-26.
 */

public class WeaknessesDB {

    SQLiteDatabase db;

    public WeaknessesDB(SQLiteDatabase db) {
        this.db = db;
    }

    private ArrayList<DamageType> executeQuery(String query, String params[]) {
        Cursor cursor;

        if (params == null || params.length == 0)
            cursor = db.rawQuery(query, null);
        else
            cursor = db.rawQuery(query, params);

        // Initialisation des structures requises pour l'obtention des données
        ArrayList<DamageType> lst = new ArrayList<>();
        String cols[] = cursor.getColumnNames();

        Log.v("wiki", cursor.getCount() + " éléments.");
        Log.v("wiki", cols.length + " colonnes.");

        for (String col : cols) {
            Log.v("wiki", "Nom : " + col);
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                DamageType dmgType = new DamageType(
                        cursor.getInt(0),
                        cursor.getString(1)
                );

                lst.add(dmgType);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lst;
    }

    public ArrayList<DamageType> byShadowID(int id) {
        String query = "SELECT dt.ID, dt.Name \n" +
                "FROM DamageTypes as 'dt', Weaknesses as 'w' \n" +
                "WHERE w.ID_Shadow = ? and dt.ID = w.ID_DamageType";
        return executeQuery(query, new String[]{String.valueOf(id)});
    }
}
