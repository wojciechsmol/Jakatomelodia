package pl.wojciech.smol.jakatomelodia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class CategorySettingsActivity extends AppCompatActivity {

    public static final String MY_CATEGORY_PREFERENCES = "MyCategoryPref";
    public static final String POP = "popKey";
    public static final String ROCK = "rockKey";
    public static final String SEVENTIES_AND_EIGHITES = "seventiesAndEightiesKey";

    SharedPreferences mSharedPreferences;

    private ToggleButton popCategoryButton;
    private ToggleButton rockCategoryButton;
    private ToggleButton seventiesAndEightiesCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateToggleButtons();
    }

    private void initialize() {

        mSharedPreferences = getSharedPreferences(MY_CATEGORY_PREFERENCES, Context.MODE_PRIVATE);
        popCategoryButton = (ToggleButton) findViewById(R.id.toggle_button_pop_category);
        rockCategoryButton = (ToggleButton) findViewById(R.id.toggle_button_rock_category);
        seventiesAndEightiesCategoryButton = (ToggleButton) findViewById(R.id.toggle_button_seventies_and_eighties_category);

        updateToggleButtons();

        popCategoryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private SharedPreferences.Editor editor = mSharedPreferences.edit();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    editor.putBoolean(POP, isChecked).apply();
                    editor.commit();
                }
                else
                {
                    editor.putBoolean(POP, isChecked).apply();
                    editor.commit();
                }
            }
        });

        rockCategoryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private SharedPreferences.Editor editor = mSharedPreferences.edit();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    editor.putBoolean(ROCK, isChecked).apply();
                    editor.commit();
                }
                else
                {
                    editor.putBoolean(ROCK, isChecked).apply();
                    editor.commit();
                }

            }
        });

        seventiesAndEightiesCategoryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private SharedPreferences.Editor editor = mSharedPreferences.edit();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    editor.putBoolean(SEVENTIES_AND_EIGHITES, isChecked).apply();
                    editor.commit();
                }
                else
                {
                    editor.putBoolean(SEVENTIES_AND_EIGHITES, isChecked).apply();
                    editor.commit();
                }

            }
        });

        updateToggleButtons();
    }

    private void updateToggleButtons() {

        popCategoryButton.setChecked(mSharedPreferences.getBoolean(POP, false));
        rockCategoryButton.setChecked(mSharedPreferences.getBoolean(ROCK, false));
        seventiesAndEightiesCategoryButton.setChecked(mSharedPreferences.getBoolean(SEVENTIES_AND_EIGHITES, false));
    }
}
