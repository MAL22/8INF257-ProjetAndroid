package uqac8inf257.wikipersona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

        boolean dbExist = checkDatabase();

        if (dbExist) {
            Log.v("wiki", "Database exists");
        } else {
            Log.v("wiki", "Database does not exist");

            try {
                this.getReadableDatabase();
                Log.v("wiki", "Database exists");
                this.close();
                Log.v("wiki", "Database exists");
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

        Log.v("DIM", "myInput : " + myInput);

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
        Log.v("wiki", "Database exists");

        boolean checkDB = false;

        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            Log.v("wiki", "Database exists");

            File dbFile = new File(myPath);
            Log.v("wiki", "Database exists");

            checkDB = dbFile.exists();
            Log.v("wiki", "Database exists");

        } catch (SQLiteException e) {
            Log.v("wiki", "Database exists");
        }
        return checkDB;
    }

    public void db_Delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            System.out.println("Delete db file");
        }
    }

    public void openDatabase() throws SQLiteException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDatabase() throws SQLiteException {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.v("db", "db version incremented");
            db_Delete();
        }
    }

    public String getDatabaseData() {

        StringBuilder sb = new StringBuilder();

        Log.v("DIM", "isOpen : " + myDatabase.isOpen());
        Log.v("DIM", "isReadOnly : " + myDatabase.isReadOnly());
        Log.v("DIM", "NAME : " + myDatabase.getPath());

        Cursor cursor = myDatabase.rawQuery("select * from Shadows", null);

        Log.v("DIM", "MyDataBase : " + myDatabase.getPath() + " */* " + myDatabase.toString());

        String nomColonnes[] = cursor.getColumnNames();

        Log.v("DIM", "Nombre Colonnes : " + nomColonnes.length);

        for (String nomColonne : nomColonnes)
            Log.v("DIM", "NOM DE COLONNE : " + nomColonne);

        Log.v("DIM", "cursor.getCount() : " + cursor.getCount());
        Log.v("DIM", "cursor.getCount() : " + cursor.getCount());

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String nom2 = cursor.getString(2);
                String nom3 = cursor.getString(3);
                String nom4 = cursor.getString(4);
                String nom5 = cursor.getString(5);

                sb.append("\n").append(id).append("\n").append(nom).append("\n").append(nom2).append("\n").append(nom3).append("\n").append(nom4).append("\n").append(nom5);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sb.toString();
    }
}
