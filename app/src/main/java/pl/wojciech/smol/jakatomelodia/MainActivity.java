package pl.wojciech.smol.jakatomelodia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing newGameButton
        Button newGameButton = (Button) findViewById(R.id.button_new_game);

        //Setting onClickListener for the newGameButton
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if there is at least one category selected. Otherwise do not start the game
                if (validateCategorySettings()) {
                    PlayActivity.game = new Game();
                    startActivity(new Intent(MainActivity.this, PlayActivity.class));
                }
                else {

                    // Showing a toast with info that no category has been selected
                    final Toast toast = Toast.makeText(MainActivity.this, getString(R.string.no_category_set), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -200);
                    toast.show();
                }
            }
        });

        //Initializing set category button
        Button setCategoryButton = (Button) findViewById(R.id.button_set_categories);
        //Setting onClickListener for the setCategoryButton
        setCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CategorySettingsActivity.class));
            }
        });

        // Initializing exit button
        Button exitButton = (Button) findViewById(R.id.button_exit);
        //Setting onClickListener for the exitButton
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // QUIT the app
                finish();
                moveTaskToBack(true);

            }
        });



    }

    private boolean validateCategorySettings() {

        SharedPreferences mSharedPreferences = getSharedPreferences(CategorySettingsActivity.MY_CATEGORY_PREFERENCES, Context.MODE_PRIVATE);

        return mSharedPreferences.getBoolean(CategorySettingsActivity.POP, false)
                || mSharedPreferences.getBoolean(CategorySettingsActivity.ROCK, false)
                || mSharedPreferences.getBoolean(CategorySettingsActivity.SEVENTIES_AND_EIGHITES, false);

    }

    @Override
    public void onBackPressed() {
      // Just do nothing
    }



}
