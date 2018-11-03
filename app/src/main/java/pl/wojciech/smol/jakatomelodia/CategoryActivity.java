package pl.wojciech.smol.jakatomelodia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initialize();


    }

    private void initialize() {

        //Initializing buttons and onClickListeners

        Button popCategoryButton = (Button) findViewById(R.id.button_category_pop);
        popCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayActivity.game = new Game(Question.Category.POP);
                startActivity(new Intent(CategoryActivity.this, PlayActivity.class));
            }
        });

        Button rockCategoryButton = (Button) findViewById(R.id.button_category_rock);
        rockCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayActivity.game = new Game(Question.Category.ROCK);
                startActivity(new Intent(CategoryActivity.this, PlayActivity.class));
            }
        });

        Button seventiesAndEightiesCategoryButton = (Button) findViewById(R.id.button_category_seventiesandeighties);
        seventiesAndEightiesCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayActivity.game = new Game(Question.Category.SEVENTIESANDEIGHTIS);
                startActivity(new Intent(CategoryActivity.this, PlayActivity.class));
            }
        });




    }
}
