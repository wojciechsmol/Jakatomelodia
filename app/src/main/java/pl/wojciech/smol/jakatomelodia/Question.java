package pl.wojciech.smol.jakatomelodia;

/**
 * Created by Wojtek on 08.04.2017.
 */

public class Question {

    // Audio ID to be played
    private int mAudioResource;
    // Answer
    private String mAnswer;

    //Array of Questions available in the app
    public static final Question[] mQuestions = {
            new Question(R.raw.again, "Again"),
            new Question(R.raw.always, "Always"),
            new Question(R.raw.touch, "Touch By Touch"),
            new Question(R.raw.another_one_bites, "Another One Bites The Dust"),
            new Question(R.raw.baska, "Baśka"),
            new Question(R.raw.biala_flaga, "Biała Flaga"),
            new Question(R.raw.come_as_you_are, "Come As You Are"),
            new Question(R.raw.cryin, "Cryin'"),
            new Question(R.raw.feeling_good, "Feeling Good"),
            new Question(R.raw.help, "Help"),
            new Question(R.raw.kashmir, "Kashmir"),
            new Question(R.raw.one, "One"),
            new Question(R.raw.ruby, "Ruby"),
            new Question(R.raw.sex_on_fire, "Sex On Fire"),
            new Question(R.raw.starlight, "Starlight"),
            new Question(R.raw.this_love, "This Love"),
            new Question(R.raw.wonderwall, "Wonderwall")
    };

    /**
     * Creaate a new Question object.
     *
     * @param AudioResource is the resource ID for the audio file associated with this question
     * @param answer        is the correct answer for the question (4)
     */
    private Question(int AudioResource, String answer) {
        mAudioResource = AudioResource;
        mAnswer = answer;
    }

    public int getAudioResource() {
        return mAudioResource;
    }

    public String getAnswer() {
        return mAnswer;
    }


    @Override
    public String toString() {
        return "Question{" +
                "mAudioResource=" + mAudioResource +
                ", mAnswer='" + mAnswer + '\'' +
                '}';
    }
}
