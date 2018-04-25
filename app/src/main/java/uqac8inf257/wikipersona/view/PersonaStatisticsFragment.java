package uqac8inf257.wikipersona.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.data.Shadow;

/**
 * Created by mimil on 2018-04-24.
 */

public class PersonaStatisticsFragment extends Fragment {

    private TextView mTxt;
    private static String weakTxt = "WEAK";
    private static String resistTxt = "RESIST";

    public PersonaStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        return inflater.inflate(R.layout.fragment_persona_stats, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle extras = getActivity().getIntent().getExtras();
        Shadow shadow = (Shadow) extras.getSerializable("shadow");

        mTxt = view.findViewById(R.id.fragmentStatsPersonaRealName);
        mTxt.setText(shadow.getRealName());

        mTxt = view.findViewById(R.id.fragmentStatsPersonaFakeName);
        mTxt.setText(shadow.getFakeName());

        mTxt = view.findViewById(R.id.fragmentStatsPersonaLevel);
        mTxt.setText(String.valueOf(shadow.getLevel()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaHP);
        mTxt.setText(String.valueOf(shadow.getHp()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaSP);
        mTxt.setText(String.valueOf(shadow.getSp()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaType);
        mTxt.setText(shadow.getPersonality().getName());

        mTxt = view.findViewById(R.id.fragmentStatsPersonaEXP);
        mTxt.setText(String.valueOf(shadow.getExp()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaYen);
        mTxt.setText(String.valueOf(shadow.getYen()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaMaterialDrop);
        mTxt.setText(String.valueOf(shadow.getDrop()));

        mTxt = view.findViewById(R.id.fragmentStatsPersonaSkillCard);
        mTxt.setText(String.valueOf(shadow.getSkillCard()));

        for (int i = 0; i < shadow.getWeaknesses().size(); i++) {
            switch (shadow.getWeaknesses().get(i).getId()) {
                case 1:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaPhysical);
                    break;
                case 2:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaGun);
                    break;
                case 3:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaFire);
                    break;
                case 4:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaIce);
                    break;
                case 5:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaElectricity);
                    break;
                case 6:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaWind);
                    break;
                case 7:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaCursed);
                    break;
                case 8:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaBlessed);
                    break;
                case 9:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaNuclear);
                    break;
                case 10:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaAlmighty);
                    break;
                case 11:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaPsychic);
                    break;
            }
            mTxt.setText(weakTxt);
        }

        for (int i = 0; i < shadow.getResistances().size(); i++) {
            switch (shadow.getResistances().get(i).getId()) {
                case 1:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaPhysical);
                    break;
                case 2:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaGun);
                    break;
                case 3:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaFire);
                    break;
                case 4:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaIce);
                    break;
                case 5:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaElectricity);
                    break;
                case 6:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaWind);
                    break;
                case 7:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaCursed);
                    break;
                case 8:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaBlessed);
                    break;
                case 9:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaNuclear);
                    break;
                case 10:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaAlmighty);
                    break;
                case 11:
                    mTxt = view.findViewById(R.id.fragmentStatsPersonaPsychic);
                    break;
            }
            mTxt.setText(resistTxt);
        }
    }
}
