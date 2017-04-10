package pl.wojciech.smol.jakatomelodia;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Wojtek on 09.04.2017.
 */

public class Game {

    // Max number of questions in a single game
    public static final int MAX_QUESTIONS = 10;
    // Current question number
    private int mquestionNumber;
    // Score
    private int mScore;
    //Set of questions for current game
    private List<Question> mQuestionsGame;
    // Random generator
    public static RandomDataGenerator generator;

    public Game() {
        this.mquestionNumber = 0;
        this.mScore = 0;
        setQuestions();
    }

    public Question getCurrentQuestion() {
        return mQuestionsGame.get(mquestionNumber);
    }

    public int getQuestionNumber() {
        return mquestionNumber;
    }

    public int getScore() {
        return mScore;
    }

    private void setQuestions() {
        // Initializing LinkedHashSet so that the questions do not duplicate
        mQuestionsGame = new ArrayList<Question>();

        int[] randomIndexes = new int[MAX_QUESTIONS];

        try {
            //nextPermutation generates an integer array without repetition
            randomIndexes = generator.nextPermutation((Question.mQuestions.length - 1), MAX_QUESTIONS);
        } catch (MathIllegalNumberException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < MAX_QUESTIONS; i++) {
            mQuestionsGame.add(Question.mQuestions[randomIndexes[i]]);
        }
    }
}
