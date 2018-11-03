package pl.wojciech.smol.jakatomelodia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    //New Game button.
    private Button newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing newGameButton
        newGameButton = (Button) findViewById(R.id.button_new_game);

        //Setting onClickListener for the newGameButton
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayActivity.game = new Game();
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
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

    @Override
    public void onBackPressed() {
      // Just do nothing
    }



}
