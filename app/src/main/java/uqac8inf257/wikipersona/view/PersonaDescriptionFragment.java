package uqac8inf257.wikipersona.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.data.Shadow;

/**
 * Created by mimil on 2018-04-24.
 */

public class PersonaDescriptionFragment extends Fragment {

    private TextView mTxtRealName;
    private TextView mTxtFakeName;
    private TextView mTxtDescription;

    public PersonaDescriptionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        return inflater.inflate(R.layout.fragment_persona_description, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle extras = getActivity().getIntent().getExtras();
        Shadow shadow = (Shadow) extras.getSerializable("shadow");

        mTxtRealName = view.findViewById(R.id.fragmentPersonaRealName);
        mTxtFakeName = view.findViewById(R.id.fragmentPersonaFakeName);
        mTxtDescription = view.findViewById(R.id.fragmentPersonaDescription);

        mTxtRealName.setText(shadow.getRealName());
        mTxtFakeName.setText(shadow.getFakeName());
        mTxtDescription.setText(shadow.getHistory());
    }
}
