package uqac8inf257.wikipersona.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.Vector;

import uqac8inf257.wikipersona.model.Arcana;
import uqac8inf257.wikipersona.model.Personality;
import uqac8inf257.wikipersona.model.Shadow;
import uqac8inf257.wikipersona.model.Statistics;

/**
 * Created by mimil on 2018-03-08.
 */

public class SelectShadow extends DatabaseHelper {
    private final static String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
            "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
            "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.ID = ?";

    public SelectShadow(Context context) {
        super(context);
    }

    public Vector<Shadow> execute(int id) {
        try {
            super.createDatabase();
            super.openDatabase();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        Vector<Shadow> lstSh = new Vector<>();

        Cursor cursor = super.myDatabase.rawQuery(query, new String[]{String.valueOf(id)});

        String columns[] = cursor.getColumnNames();

        Log.v("wiki", cursor.getCount() + " éléments.");
        Log.v("wiki", columns.length + " colonnes.");

        for (String column : columns) {
            Log.v("wiki", "Nom : " + column);
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Shadow sh = new Shadow(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        new Arcana(
                                cursor.getInt(4),
                                cursor.getString(5)),
                        new Personality(
                                cursor.getInt(6),
                                cursor.getString(7)),
                        new Statistics(
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10),
                                cursor.getInt(11))
                );

                lstSh.add(sh);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lstSh;
    }
}
