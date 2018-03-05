package uqac8inf257.wikipersona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mimil on 2018-03-01.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDatabase;
    private final Context myContext;

    private static final String DATABASE_NAME = "personawiki.db";
    public final static String DATABASE_PATH = "/data/data/uqac8inf257.wikipersona/databases/";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    public void createDatabase() throws IOException {
        Log.v("wiki", "createDatabase");

        SQLiteDatabase db = this.getReadableDatabase();
        if (checkDatabase()) {
            Log.v("wiki", "Database found");
            onUpgrade(db, db.getVersion(), DATABASE_VERSION);
            Log.v("wiki", "Database upgraded");
            db.close();
        } else {
            Log.v("wiki", "Database does not exist");

            try {
                db.close();
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

        /*if (checkDatabase()) {
            openDatabase();
            onUpgrade(myDatabase,myDatabase.getVersion(),DATABASE_VERSION);
            Log.v("wiki", "Database upgraded");
        } else {
            Log.v("wiki", "Database does not exist");

            try {
                this.getReadableDatabase();
                Log.v("wiki", "Database opened");
                this.close();
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }*/
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
    }

    public synchronized void closeDatabase() throws SQLiteException {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.v("wiki", "Database version changed");
            db_Delete();
        }
    }

    public Vector getDatabaseData() {

        StringBuilder sb = new StringBuilder();

        Log.v("wiki", "isOpen : " + myDatabase.isOpen());
        Log.v("wiki", "isReadOnly : " + myDatabase.isReadOnly());
        Log.v("wiki", "NAME : " + myDatabase.getPath());

        Cursor cursor = myDatabase.rawQuery("SELECT sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.Name as `Arcana`,p.Name AS `Personality`,sh.Strength as `Strength`,sh.Magic as `Magic`,sh.Endurance as `Endurance`,sh.Agility as `Agility`,sh.Luck as `Luck`\n" +
                "FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p'\n" +
                "WHERE sh.Arcana_ID = a.ID and sh.Personality_ID = p.ID", null);//myDatabase.rawQuery("select * from Shadows", null);

        Log.v("wiki", "MyDataBase : " + myDatabase.getPath() + " */* " + myDatabase.toString());

        String nomColonnes[] = cursor.getColumnNames();

        Log.v("wiki", "Nombre Colonnes : " + nomColonnes.length);

        for (String nomColonne : nomColonnes)
            Log.v("wiki", "NOM DE COLONNE : " + nomColonne);

        Log.v("wiki", "cursor.getCount() : " + cursor.getCount());

        Vector<Vector<String>> struct = new Vector();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Vector<String> shadow = new Vector();

                shadow.add(cursor.getString(0));
                shadow.add(cursor.getString(1));
                shadow.add(cursor.getString(2));
                shadow.add(cursor.getString(3));
                shadow.add(cursor.getString(4));
                shadow.add(cursor.getString(5));
                shadow.add(cursor.getString(6));
                shadow.add(cursor.getString(7));
                shadow.add(cursor.getString(8));
                shadow.add(cursor.getString(9));

                struct.add(shadow);

                /*String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String nom2 = cursor.getString(2);
                String nom3 = cursor.getString(3);
                String nom4 = cursor.getString(4);
                String nom5 = cursor.getString(5);

                sb.append("\n").append(id).append("\n").append(nom).append("\n").append(nom2).append("\n").append(nom3).append("\n").append(nom4).append("\n").append(nom5);*/
            } while (cursor.moveToNext());
            cursor.close();
        }
        return struct;//sb.toString();
    }
}
