package uqac8inf257.wikipersona.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.data.Shadow;

/**
 * Created by mimil on 2018-04-04.
 */

public class PersonaActivity extends AppCompatActivity {

    private TextView mNomPersona;
    private TextView mNomShadow;
    private TextView mDescPersona;
    private TextView mStrength;
    private TextView mMagic;
    private TextView mEndurance;
    private TextView mAgility;
    private TextView mLuck;

    private TextView mTxtGun;
    private TextView mTxtFire;
    private TextView mTxtPhys;
    private TextView mTxtIce;
    private TextView mTxtElec;
    private TextView mTxtWind;
    private TextView mTxtPsy;
    private TextView mTxtNucl;
    private TextView mTxtBless;
    private TextView mTxtCurse;
    private TextView mTxtAlmi;

    private TextView mTxtArcana;
    private TextView mTxtLevel;
    private TextView mTxtHP;
    private TextView mTxtSP;
    private TextView mTxtType;
    private TextView mTxtEXP;
    private TextView mTxtYen;
    private TextView mTxtMatDrop;
    private TextView mTxtSkillCard;

    private static final String weakTxt = "WEAK";
    private static final String resistTxt = "RESIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona);

        Bundle extras = getIntent().getExtras();
        Shadow shadow = (Shadow) extras.getSerializable("shadow");

        mNomPersona = findViewById(R.id.nomPersona);
        mNomShadow = findViewById(R.id.nomShadow);
        mDescPersona = findViewById(R.id.descriptionPersona);

        mStrength = findViewById(R.id.strengthStat);
        mMagic = findViewById(R.id.magicStat);
        mEndurance = findViewById(R.id.enduranceStat);
        mAgility = findViewById(R.id.agilityStat);
        mLuck = findViewById(R.id.luckStat);

        mTxtGun = findViewById(R.id.tableTxtGun);
        mTxtFire = findViewById(R.id.tableTxtFire);
        mTxtPhys = findViewById(R.id.tableTxtPhys);
        mTxtIce = findViewById(R.id.tableTxtIce);
        mTxtElec = findViewById(R.id.tableTxtElec);
        mTxtWind = findViewById(R.id.tableTxtWind);
        mTxtPsy = findViewById(R.id.tableTxtPsy);
        mTxtNucl = findViewById(R.id.tableTxtNucl);
        mTxtBless = findViewById(R.id.tableTxtBless);
        mTxtCurse = findViewById(R.id.tableTxtCurse);
        mTxtAlmi = findViewById(R.id.tableTxtAlmi);

        mTxtArcana = findViewById(R.id.tableTxtArcana);
        mTxtLevel = findViewById(R.id.tableTxtLevel);
        mTxtHP = findViewById(R.id.tableTxtHP);
        mTxtSP = findViewById(R.id.tableTxtSP);
        mTxtType = findViewById(R.id.tableTxtType);
        mTxtEXP = findViewById(R.id.tableTxtEXP);
        mTxtYen = findViewById(R.id.tableTxtYen);
        mTxtMatDrop = findViewById(R.id.tableTxtMaterialDrop);
        mTxtSkillCard = findViewById(R.id.tableTxtSkillCard);

        //--- SETTING ELEMENTS VALUES ---

        mNomPersona.setText(shadow.getRealName());
        mNomShadow.setText(shadow.getFakeName());
        mDescPersona.setText(shadow.getHistory());

        mStrength.setText(String.valueOf(shadow.getStats().getStrength()));
        mAgility.setText(String.valueOf(shadow.getStats().getAgility()));
        mEndurance.setText(String.valueOf(shadow.getStats().getEndurance()));
        mMagic.setText(String.valueOf(shadow.getStats().getMagic()));
        mLuck.setText(String.valueOf(shadow.getStats().getLuck()));

        mTxtArcana.setText(shadow.getArcana().getName());
        mTxtLevel.setText(String.valueOf(shadow.getLevel()));
        mTxtHP.setText(String.valueOf(shadow.getHp()));
        mTxtSP.setText(String.valueOf(shadow.getSp()));
        mTxtType.setText(shadow.getPersonality().getName());
        mTxtEXP.setText(String.valueOf(shadow.getExp()));
        mTxtYen.setText(String.valueOf(shadow.getYen()));
        mTxtMatDrop.setText(shadow.getDrop());
        mTxtSkillCard.setText(shadow.getSkillCard());


        for (int i = 0; i < shadow.getWeaknesses().size(); i++) {
            switch (shadow.getWeaknesses().get(i).getId()) {
                case 1:
                    mTxtPhys.setText(weakTxt);
                    break;
                case 2:
                    mTxtGun.setText(weakTxt);
                    break;
                case 3:
                    mTxtFire.setText(weakTxt);
                    break;
                case 4:
                    mTxtIce.setText(weakTxt);
                    break;
                case 5:
                    mTxtWind.setText(weakTxt);
                    break;
                case 6:
                    mTxtNucl.setText(weakTxt);
                    break;
                case 7:
                    mTxtPsy.setText(weakTxt);
                    break;
                case 8:
                    mTxtBless.setText(weakTxt);
                    break;
                case 9:
                    mTxtCurse.setText(weakTxt);
                    break;
                case 10:
                    mTxtElec.setText(weakTxt);
                    break;
                case 11:
                    mTxtAlmi.setText(weakTxt);
                    break;
            }
        }

        for (int i = 0; i < shadow.getResistances().size(); i++) {
            switch (shadow.getResistances().get(i).getId()) {
                case 1:
                    mTxtPhys.setText(resistTxt);
                    break;
                case 2:
                    mTxtGun.setText(resistTxt);
                    break;
                case 3:
                    mTxtFire.setText(resistTxt);
                    break;
                case 4:
                    mTxtIce.setText(resistTxt);
                    break;
                case 5:
                    mTxtWind.setText(resistTxt);
                    break;
                case 6:
                    mTxtNucl.setText(resistTxt);
                    break;
                case 7:
                    mTxtPsy.setText(resistTxt);
                    break;
                case 8:
                    mTxtBless.setText(resistTxt);
                    break;
                case 9:
                    mTxtCurse.setText(resistTxt);
                    break;
                case 10:
                    mTxtElec.setText(resistTxt);
                    break;
                case 11:
                    mTxtAlmi.setText(resistTxt);
                    break;
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


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
