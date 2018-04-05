package uqac8inf257.wikipersona;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;

import uqac8inf257.wikipersona.data.Shadow;

/**
 * Created by mimil on 2018-04-04.
 */

public class PersonaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona);

        String jsonMyObj;
        Bundle extras = getIntent().getExtras();
        jsonMyObj = extras.getString("shadow");
        Shadow shadow = new Gson().fromJson(jsonMyObj, Shadow.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
