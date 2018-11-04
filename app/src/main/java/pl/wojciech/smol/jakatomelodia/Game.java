package pl.wojciech.smol.jakatomelodia;

import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    // Max number of questions in a single game
    public static final int MAX_QUESTIONS = 10;
    //Max score
    public static final int MAX_SCORE = 100;
    // Score for correct answer
    private static final int ONE_SCORE = 10;
    // Current question number
    private int mquestionNumber;
    // Score
    private int mScore;
    //Game category
    private Question.Category mGameCategory;
    //Set of questions for current game
    private List<Question> mQuestionsGame;
    // Random generator
    public static RandomDataGenerator generator;


    static {
        // Initializing RandomDataGenerator Object
        generator = new RandomDataGenerator();
    }

    // public Constructor
    public Game(Question.Category gameCategory) {
        this.mquestionNumber = 0;
        this.mScore = 0;
        this.mGameCategory = gameCategory;
        //Generating questions
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

    public Question.Category getmGameCategory() {
        return mGameCategory;
    }

    public void setmGameCategory(Question.Category mGameCategory) {
        this.mGameCategory = mGameCategory;
    }

    private void setQuestions() {
        // Initializing LinkedHashSet so that the questions do not duplicate
        mQuestionsGame = new ArrayList<>(MAX_QUESTIONS);

        //randomIndexes to get questions from Question class
        int[] randomIndexes = new int[MAX_QUESTIONS];

        try {
            //nextPermutation generates an integer array without repetition
            randomIndexes = generator.nextPermutation((Question.mQuestions.length), MAX_QUESTIONS);
        } catch (MathIllegalNumberException e) {
            e.printStackTrace();
        }

        // Adding questions to ArrayList
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            Question currentQuestion = Question.mQuestions[randomIndexes[i]];

            //Check if every question belongs to the correct category, If not pick random one and check if this one is ok
            if (currentQuestion.getmCategory() != getmGameCategory())
            {
                int myRandomInteger;
                do {
                    myRandomInteger = generator.nextInt(0, Question.mQuestions.length - 1);
                    currentQuestion = Question.mQuestions[myRandomInteger];
                } //repeat if the list already contains this song or the song has wrong category
                while (Arrays.asList(randomIndexes).contains(myRandomInteger) || currentQuestion.getmCategory() != getmGameCategory()
                        || mQuestionsGame.contains(currentQuestion));
            }

            mQuestionsGame.add(currentQuestion);
        }
    }

    // If the answer was correct
    public void correctAnswer() {
        // increase Score
        mScore += ONE_SCORE;
        // IF this is not the end of the game increase question number
        if (!endOfAGame())
            // Go to next question
            ++mquestionNumber;
    }

    // If the answer was incorrect
    public void wrongAnswer() {
        // IF this is not the end of the game increase question number
        if (!endOfAGame())
            // Go to next question
            ++mquestionNumber;
    }

    // Informs if it's the end of the game
    public boolean endOfAGame() {
        return mquestionNumber >= (MAX_QUESTIONS - 1);
    }
}
