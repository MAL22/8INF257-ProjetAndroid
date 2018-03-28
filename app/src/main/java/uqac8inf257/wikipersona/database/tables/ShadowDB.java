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
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getInt(10),
                                cursor.getInt(11)),
                        DB_Skills.byShadowID(cursor.getInt(0)),
                        DB_Weaknesses.byShadowID(cursor.getInt(0)),
                        DB_Resistances.byShadowID(cursor.getInt(0))
                );
                lst.add(shadow);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lst;
    }

    public Vector<Shadow> getAll() {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";
        return executeQuery(query, null);
    }

    public Vector<Shadow> byID(int id) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.ID = ?";
        return executeQuery(query, new String[]{String.valueOf(id)});
    }

    public Vector<Shadow> byRealName(String name) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.RealName = ?";
        return executeQuery(query, new String[]{name});
    }

    public Vector<Shadow> byFakeName(String name) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and sh.FakeName = ?)";
        return executeQuery(query, new String[]{name});
    }

    public Vector<Shadow> byArcana(String arcana) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and a.Name = ?";
        return executeQuery(query, new String[]{arcana});
    }

    public Vector<Shadow> byPersonality(String personality) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID and p.Name = ?";
        return executeQuery(query, new String[]{personality});
    }

    public Vector<Shadow> byWeakness(int id) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID";
        return executeQuery(query, new String[]{String.valueOf(id)});
    }

    public Vector<Shadow> byResistance(int id) {
        String query = "SELECT sh.id as `ID`, sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.id,a.Name as `Arcana`,p.id,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = ?";
        return executeQuery(query, new String[]{String.valueOf(id)});
    }
/*
    public Vector<DamageType> getWeaknesses(int id) {
        return DB_Weaknesses.byShadowID(id);
    }

    public Vector<DamageType> getResistances(int id) {
        return DB_Resistances.byShadowID(id);
    }*/
}
