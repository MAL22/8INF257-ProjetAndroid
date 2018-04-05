package uqac8inf257.wikipersona;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import uqac8inf257.wikipersona.controller.MainController;


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
                mainController.displayRandomShadow();
                break;

            default:
                break;
        }
    }
}
