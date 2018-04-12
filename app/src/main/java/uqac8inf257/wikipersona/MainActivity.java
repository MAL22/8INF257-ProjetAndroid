package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import uqac8inf257.wikipersona.model.MainController;


public class MainActivity extends AppCompatActivity {

    MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void btnRandom_onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRandom:
                Log.v("wiki", "btnRandom_onClick");
                //mainController.displayRandomShadow();
                mainController.searchShadows();
                break;

            default:
                break;
        }
    }
}
