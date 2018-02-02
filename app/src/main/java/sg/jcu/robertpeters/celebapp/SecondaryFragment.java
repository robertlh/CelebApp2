package sg.jcu.robertpeters.celebapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondaryFragment extends Fragment {

    private SharedPreferences preferences;

    public SecondaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secondary, container, false);
        preferences = getActivity().getSharedPreferences("CelebApp", MODE_PRIVATE);
        RadioGroup radioGroup = view.findViewById(R.id.numberChoices);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radioButton2) {
                   preferences.edit()
                           .putInt("numAnswer", 2)
                           .apply();
                } else if(checkedId == R.id.radioButton4) {
                    preferences.edit()
                            .putInt("numAnswer", 4)
                            .apply();
                } else {
                    preferences.edit()
                            .putInt("numAnswer", 6)
                            .apply();
                }
            }
        });
        return view;
    }


}
