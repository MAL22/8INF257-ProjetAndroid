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

public class SelectShadows extends ShadowDB {

    private final static String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
            "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
            "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";

    public SelectShadows(Context context) {
        super(context);
    }

    public Vector<Shadow> getData() {
        return super.executeQuery(query, null);
    }
}
