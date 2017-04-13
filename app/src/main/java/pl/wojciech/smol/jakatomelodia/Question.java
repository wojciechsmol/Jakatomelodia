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
            new Question(R.raw.wonderwall, "Wonderwall"),
            new Question(R.raw.kolory, "Kolory Tańczą w Twoich Oczach"),
            new Question(R.raw.highway_to_hell, "Highway To Hell"),
            new Question(R.raw.radio_hello, "Radio Hello"),
            new Question(R.raw.shape_of_you, "Shape Of You"),
            new Question(R.raw.jest_juz_ciemno, "A Gdy Jest Już Ciemno"),
            new Question(R.raw.chained_to_the_rhytm, "Chained To The Rhytm"),
            new Question(R.raw.shed_a_light, "Shed A Light"),
            new Question(R.raw.cold, "Cold"),
            new Question(R.raw.gangam_style, "Gangam Style"),
            new Question(R.raw.na_jednej_z_dzikich_plaz, "Na Jednej Z Dzikich Plaż"),
            new Question(R.raw.ide_na_plaze, "Idę Na Plażę"),
            new Question(R.raw.wake_me_up, "Wake Me Up Before You Go"),
            new Question(R.raw.moves_like_jagger, "Moves Like Jagger"),
            new Question(R.raw.on_my_way, "On My Way"),
            new Question(R.raw.take_on_me, "Take On Me"),
            new Question(R.raw.black_and_white, "Black And White"),
            new Question(R.raw.zostawcie_titanica, "Zostawcie Titanica"),
            new Question(R.raw.skrzydlate_rece, "Skrzydlate Ręce"),
            new Question(R.raw.its_my_life, "It's My Life"),
            new Question(R.raw.mirrors, "Mirrors"),
            new Question(R.raw.jak_zapomniec, "Jak Zapomnieć"),
            new Question(R.raw.stayin_alive, "Stayin' Alive"),
            new Question(R.raw.dancing_queen, "Dancing Queen"),
            new Question(R.raw.ona_tanczy_dla_mnie, "Ona Tańczy Dla Mnie"),
            new Question(R.raw.living_on_a_prayer, "Living On A Prayer"),
            new Question(R.raw.hello, "Hello"),
            new Question(R.raw.w_dobra_strone, "W Dobrą Stronę"),
            new Question(R.raw.cake_by_the_ocean, "Cake By The Ocean"),
            new Question(R.raw.sorry, "Sorry"),
            new Question(R.raw.sugar, "Sugar"),

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
