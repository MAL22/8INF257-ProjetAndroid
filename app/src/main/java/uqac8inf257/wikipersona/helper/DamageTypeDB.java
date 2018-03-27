package uqac8inf257.wikipersona.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import uqac8inf257.wikipersona.model.Arcana;
import uqac8inf257.wikipersona.model.DamageType;
import uqac8inf257.wikipersona.model.Personality;
import uqac8inf257.wikipersona.model.Shadow;
import uqac8inf257.wikipersona.model.Statistics;

/**
 * Created by mimil on 2018-03-26.
 */

public class DamageTypeDB {

    SQLiteDatabase db;

    public DamageTypeDB(SQLiteDatabase db) {
        this.db = db;
    }

    private Vector<DamageType> executeQuery(String query, String params[]) {
        Cursor cursor;

        if (params == null || params.length == 0)
            cursor = db.rawQuery(query, null);
        else
            cursor = db.rawQuery(query, params);

        // Initialisation des structures requises pour l'obtention des données
        Vector<DamageType> lst = new Vector<>();
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

    public Vector<DamageType> getAll() {
        return null;
    }

    public Vector<DamageType> byID(int id) {
        return null;
    }

    public Vector<DamageType> byName(String name) {
        return null;
    }
}
