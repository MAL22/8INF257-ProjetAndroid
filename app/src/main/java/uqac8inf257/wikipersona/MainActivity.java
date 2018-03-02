package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testClick(View v) {
        Log.v("wiki", "CLICK!");

        String result = "VIDE";
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDatabase();
            result = db.getDatabaseData();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.closeDatabase();
        }

        Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
        toast.show();

    }
}
