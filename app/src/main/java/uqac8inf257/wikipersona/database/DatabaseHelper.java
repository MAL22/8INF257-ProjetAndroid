package uqac8inf257.wikipersona.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import uqac8inf257.wikipersona.database.tables.ArcanaDB;
import uqac8inf257.wikipersona.database.tables.DamageTypeDB;
import uqac8inf257.wikipersona.database.tables.PersonalityDB;
import uqac8inf257.wikipersona.database.tables.ResistancesDB;
import uqac8inf257.wikipersona.database.tables.ShadowDB;
import uqac8inf257.wikipersona.database.tables.SkillsDB;
import uqac8inf257.wikipersona.database.tables.WeaknessesDB;

/**
 * Created by mimil on 2018-03-01.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDatabase;
    private final Context myContext;

    private static final String DATABASE_NAME = "personawiki.db";
    public final static String DATABASE_PATH = "/data/data/uqac8inf257.wikipersona/databases/";
    public static final int DATABASE_VERSION = 1;

    private ShadowDB DB_Shadow;
    private PersonalityDB DB_Personality;
    private ArcanaDB DB_Arcana;
    private SkillsDB DB_Skills;
    private DamageTypeDB DB_DamageType;
    private WeaknessesDB DB_Weaknesses;
    private ResistancesDB DB_Resistances;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    public void createDatabase() throws IOException {
        Log.v("wiki", "createDatabase");

        boolean dbExist = checkDatabase();

        if (dbExist) {
            //onUpgrade(myDatabase,myDatabase.getVersion(),DATABASE_VERSION);
        } else {
            Log.v("wiki", "Database does not exist");

            try {
                SQLiteDatabase db = this.getReadableDatabase();
                Log.v("wiki", "Database opened");
                if (db.isOpen()) {
                    db.close();
                }

                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDatabase() throws IOException {
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        Log.v("wiki", "outFileName : " + outFileName);
        Log.v("wiki", "inFileName : " + DATABASE_NAME);

        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        Log.v("wiki", "myInput : " + myInput);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            String test = "";
            for (byte b : buffer)
                test += (char) b;
            Log.v("wiki", test);

            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    private boolean checkDatabase() {
        boolean checkDB = false;

        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbFile = new File(myPath);
            checkDB = dbFile.exists();
            Log.v("wiki", "Database exists");

        } catch (SQLiteException e) {
            Log.v("wiki", "Database doesn't exist");
        }
        return checkDB;
    }

    public void db_Delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            Log.v("wiki", "Database deleted");
        }
    }

    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        DB_DamageType = new DamageTypeDB(myDatabase);
        DB_Resistances = new ResistancesDB(myDatabase);
        DB_Weaknesses = new WeaknessesDB(myDatabase);
        DB_Skills = new SkillsDB(myDatabase);
        DB_Personality = new PersonalityDB(myDatabase);
        DB_Arcana = new ArcanaDB(myDatabase);
        DB_Shadow = new ShadowDB(myDatabase, DB_Weaknesses, DB_Resistances, DB_Skills);
    }

    public synchronized void closeDatabase() throws SQLiteException {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.v("wiki", "Database version changed");
            db_Delete();
        }
    }

    public ShadowDB getDBShadow() {
        return DB_Shadow;
    }

    public PersonalityDB getDBPersonality() {
        return DB_Personality;
    }

    public ArcanaDB getDBArcana() {
        return DB_Arcana;
    }

    public SkillsDB getDBSkills() {
        return DB_Skills;
    }

    public DamageTypeDB getDBDamageType() {
        return DB_DamageType;
    }
}
