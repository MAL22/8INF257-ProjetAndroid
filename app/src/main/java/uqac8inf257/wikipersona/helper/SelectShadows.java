package uqac8inf257.wikipersona.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import uqac8inf257.wikipersona.model.Shadow;
import uqac8inf257.wikipersona.model.Statistics;

/**
 * Created by mimil on 2018-03-08.
 */

public class SelectShadows extends DatabaseHelper {

    private final static String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.Name as `Arcana`,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
            "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
            "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";

    public SelectShadows(Context context) {
        super(context);
    }

    public List<Shadow> getData() {
        try {
            super.createDatabase();
            super.openDatabase();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        Vector<Shadow> lstSh = new Vector<>();
        Cursor cursor = super.myDatabase.rawQuery(query, null);

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
                        cursor.getString(4),
                        cursor.getString(5),
                        new Statistics(
                                cursor.getInt(6),
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10)
                        )
                );

                lstSh.add(sh);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lstSh;
    }
}
