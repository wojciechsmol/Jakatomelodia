package pl.wojciech.smol.jakatomelodia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        //Initializing newGameButton
        Button newGameButton = (Button) findViewById(R.id.button_new_game);

        //Setting onClickListener for the newGameButton
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayActivity.game = new Game();
                startActivity(new Intent(GameEndActivity.this, PlayActivity.class));
            }
        });

        // Initializing exit button
        Button exitButton = (Button) findViewById(R.id.button_exit);
        //Setting onClickListener for the exitButton
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Before we quit the app changes activity to MainActivity so that this activity is
                // visible when user goes to the app again
                finish();
                startActivity(new Intent(GameEndActivity.this, MainActivity.class));
                moveTaskToBack(true);

            }
        });

        // Initializing Textview for SCORE
        TextView scoreNumberTextView = (TextView) findViewById(R.id.score_number);
        // Setting text for the scoreNumberTextView
        scoreNumberTextView.setText(PlayActivity.game.getScore() + "/" + Game.MAX_SCORE);

    }

    @Override
    public void onBackPressed() {
        // When the back button is pressed the app goes to Main Menu
        startActivity(new Intent(GameEndActivity.this, MainActivity.class));
    }
}
