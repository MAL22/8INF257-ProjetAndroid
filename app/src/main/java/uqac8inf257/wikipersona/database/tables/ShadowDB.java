package uqac8inf257.wikipersona.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Vector;

import uqac8inf257.wikipersona.data.Arcana;
import uqac8inf257.wikipersona.data.Personality;
import uqac8inf257.wikipersona.data.Shadow;
import uqac8inf257.wikipersona.data.Statistics;

/**
 * Created by mimil on 2018-03-09.
 */

public class ShadowDB {

    private WeaknessesDB DB_Weaknesses;
    private ResistancesDB DB_Resistances;
    private SkillsDB DB_Skills;
    private SQLiteDatabase db;

    private static String SELECT =
            "SELECT sh.ID," +
                    "sh.FakeName," +
                    "sh.RealName," +
                    "sh.History," +
                    "a.ID," +
                    "a.Name," +
                    "p.ID," +
                    "p.Name," +
                    "sh.Strength," +
                    "sh.Magic," +
                    "sh.Endurance," +
                    "sh.Agility," +
                    "sh.Luck," +
                    "sh.EXPLevel," +
                    "sh.HP," +
                    "sh.SP," +
                    "sh.ItemDrop," +
                    "sh.SkillCard," +
                    "sh.EXP," +
                    "sh.Yen";

    private static String FROM =
            "\nFROM Shadows as 'sh'," +
                    "Arcana as 'a'," +
                    "Personalities as 'p'";

    public ShadowDB(SQLiteDatabase db, WeaknessesDB weaknessesDB, ResistancesDB resistancesDB, SkillsDB skillsDB) {
        this.db = db;
        this.DB_Weaknesses = weaknessesDB;
        this.DB_Resistances = resistancesDB;
        this.DB_Skills = skillsDB;
    }

    private Vector<Shadow> executeQuery(String query, String params[]) {
        Cursor cursor;

        if (params == null || params.length == 0)
            cursor = db.rawQuery(query, null);
        else
            cursor = db.rawQuery(query, params);

        // Initialisation des structures requises pour l'obtention des données
        Vector<Shadow> lst = new Vector<>();
        String cols[] = cursor.getColumnNames();

        Log.v("wiki", cursor.getCount() + " éléments.");
        Log.v("wiki", cols.length + " colonnes.");

        for (String col : cols) {
            Log.v("wiki", "Nom : " + col);
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Shadow shadow = new Shadow(
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
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10),
                                cursor.getInt(11),
                                cursor.getInt(12)),
                        DB_Skills.byShadowID(cursor.getInt(0)),
                        DB_Weaknesses.byShadowID(cursor.getInt(0)),
                        DB_Resistances.byShadowID(cursor.getInt(0)),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getInt(18),
                        cursor.getInt(19)

                );
                lst.add(shadow);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lst;
    }

    public Vector<Shadow> getAll() {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";

        return executeQuery(query, null);
    }

    public Shadow byID(int id) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.ID = ?";

        return executeQuery(query, new String[]{String.valueOf(id)}).firstElement();
    }

    public Vector<Shadow> byRealName(String name) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.RealName = ?";

        return executeQuery(query, new String[]{name});
    }

    public Vector<Shadow> byFakeName(String name) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.FakeName = ?";

        return executeQuery(query, new String[]{name});
    }

    public Vector<Shadow> byArcana(String arcana) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and a.Name = ?";

        return executeQuery(query, new String[]{arcana});
    }

    public Vector<Shadow> byPersonality(String personality) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and p.Name = ?";

        return executeQuery(query, new String[]{personality});
    }

    public Vector<Shadow> byWeakness(int id) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";

        return executeQuery(query, new String[]{String.valueOf(id)});
    }

    public Vector<Shadow> byResistance(int id) {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";

        return executeQuery(query, new String[]{String.valueOf(id)});
    }

    public Shadow getRandom() {
        String query = SELECT + FROM +
                "\nWHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID" +
                "\nORDER BY RANDOM()" +
                "\nLIMIT 1";

        return executeQuery(query, null).firstElement();
    }
}
