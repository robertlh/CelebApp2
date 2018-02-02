package sg.jcu.robertpeters.celebapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView gridView;
    private ArrayAdapter<String> names;
    private ImageView celebImage;
    private int celebNumber;
    private SharedPreferences preferences;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        names = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);


        preferences = getActivity().getSharedPreferences("CelebApp", MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                updateChoices();
            }
        });
        gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(names);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] namesSet = getResources().getStringArray(R.array.namesArray);
                if (namesSet[celebNumber].equals(names.getItem(position))) {
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Wrong :(", Toast.LENGTH_SHORT).show();
                }
                imageSelection();
            }
        });
        celebImage = view.findViewById(R.id.celebPicture);
        imageSelection();
        return view;
    }

    private void imageSelection() {
        celebNumber = (int) ((Math.random() * 5));
        int picture = getResources().getIdentifier("pic" + celebNumber, "drawable", getActivity().getPackageName());
        celebImage.setImageResource(picture);
        updateChoices();
    }

    private void updateChoices() {
        String[] namesSet = getResources().getStringArray(R.array.namesArray);
        ArrayList<String> tempNames = new ArrayList<String>();
        tempNames.add(namesSet[celebNumber]);
        int i = 1;
        while (i < preferences.getInt("numAnswer", 4)) {
            int temp = (int) ((Math.random() * namesSet.length));
            if (!tempNames.contains(namesSet[temp])) {
                tempNames.add(namesSet[temp]);
                i++;
            }
        }

        Collections.shuffle(tempNames);
        names.clear();
        for (String st : tempNames) {
            names.add(st);
        }
    }


}
