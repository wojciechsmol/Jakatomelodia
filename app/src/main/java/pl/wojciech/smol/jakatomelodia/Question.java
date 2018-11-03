package pl.wojciech.smol.jakatomelodia;

public class Question {

    public enum Category {
        POP, ROCK, SEVENTIESANDEIGHTIS
    }

    // Audio ID to be played
    private int mAudioResource;
    // Answer
    private String mAnswer;
    // Category
    private Category mCategory;

    //Array of Questions available in the app
    public static final Question[] mQuestions = {

            new Question(R.raw.again, "Again", Category.ROCK),
            new Question(R.raw.always, "Always", Category.POP),
            new Question(R.raw.touch, "Touch By Touch", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.another_one_bites, "Another One Bites The Dust", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.baska, "Baśka", Category.ROCK),
            new Question(R.raw.biala_flaga, "Biała Flaga", Category.ROCK),
            new Question(R.raw.come_as_you_are, "Come As You Are", Category.ROCK),
            new Question(R.raw.cryin, "Cryin'", Category.ROCK),
            new Question(R.raw.feeling_good, "Feeling Good", Category.ROCK),
            new Question(R.raw.help, "Help", Category.ROCK),
            new Question(R.raw.kashmir, "Kashmir", Category.ROCK),
            new Question(R.raw.one, "One", Category.ROCK),
            new Question(R.raw.ruby, "Ruby", Category.ROCK),
            new Question(R.raw.sex_on_fire, "Sex On Fire", Category.ROCK),
            new Question(R.raw.starlight, "Starlight", Category.ROCK),
            new Question(R.raw.this_love, "This Love", Category.POP),
            new Question(R.raw.wonderwall, "Wonderwall", Category.ROCK),
            new Question(R.raw.kolory, "Kolory Tańczą w Twoich Oczach", Category.POP),
            new Question(R.raw.highway_to_hell, "Highway To Hell", Category.ROCK),
            new Question(R.raw.radio_hello, "Radio Hello", Category.POP),
            new Question(R.raw.shape_of_you, "Shape Of You", Category.POP),
            new Question(R.raw.jest_juz_ciemno, "A Gdy Jest Już Ciemno", Category.POP),
            new Question(R.raw.chained_to_the_rhytm, "Chained To The Rhytm", Category.POP),
            new Question(R.raw.shed_a_light, "Shed A Light", Category.POP),
            new Question(R.raw.cold, "Cold", Category.POP),
            new Question(R.raw.gangam_style, "Gangam Style", Category.POP),
            new Question(R.raw.na_jednej_z_dzikich_plaz, "Na Jednej Z Dzikich Plaż", Category.POP),
            new Question(R.raw.ide_na_plaze, "Idę Na Plażę", Category.POP),
            new Question(R.raw.wake_me_up, "Wake Me Up Before You Go", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.moves_like_jagger, "Moves Like Jagger", Category.POP),
            new Question(R.raw.on_my_way, "On My Way", Category.POP),
            new Question(R.raw.take_on_me, "Take On Me", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.black_and_white, "Black And White", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.zostawcie_titanica, "Zostawcie Titanica", Category.ROCK),
            new Question(R.raw.skrzydlate_rece, "Skrzydlate Ręce", Category.POP),
            new Question(R.raw.its_my_life, "It's My Life", Category.ROCK),
            new Question(R.raw.mirrors, "Mirrors", Category.POP),
            new Question(R.raw.jak_zapomniec, "Jak Zapomnieć", Category.POP),
            new Question(R.raw.stayin_alive, "Stayin' Alive", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.dancing_queen, "Dancing Queen", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.ona_tanczy_dla_mnie, "Ona Tańczy Dla Mnie", Category.POP),
            new Question(R.raw.living_on_a_prayer, "Living On A Prayer", Category.ROCK),
            new Question(R.raw.hello, "Hello", Category.POP),
            new Question(R.raw.w_dobra_strone, "W Dobrą Stronę", Category.POP),
            new Question(R.raw.cake_by_the_ocean, "Cake By The Ocean", Category.POP),
            new Question(R.raw.sorry, "Sorry", Category.POP),
            new Question(R.raw.sugar, "Sugar", Category.POP),
            new Question(R.raw.daddy_cool, "Daddy Cool", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.czarne_oczy, "Czarne Oczy", Category.POP),
            new Question(R.raw.two_princes, "Two Princes", Category.ROCK),
            new Question(R.raw.teksanski, "Teksański", Category.ROCK),
            new Question(R.raw.one_way_ticket, "One Way Ticket", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.gimme_gimme, "Gimme Gimme", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.mniej_niz_zero, "Mniej Niż Zero", Category.ROCK),
            new Question(R.raw.cykady_na_cykladach, "Cykady Na Cykladach", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.du_hast, "Du Hast", Category.ROCK),
            new Question(R.raw.rock_around_the_clock, "Rock Around The Clock", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.crazy_in_love, "Crazy In Love", Category.POP),
            new Question(R.raw.run_to_you, "Run To You", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.dream_on, "Dream On", Category.ROCK),
            new Question(R.raw.you_give_love_a_bad_name, "You Give Love A Bad Name", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.enjoy_the_silence, "Enjoy The Silence", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.hotel_california, "Hotel California", Category.ROCK),
            new Question(R.raw.na_falochronie, "Na Falochronie", Category.ROCK),
            new Question(R.raw.jesus_he_knows_me, "Jesus He Knows Me", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.sweet_child_o_mine, "Sweet Child O' Mine", Category.ROCK),
            new Question(R.raw.fly_away, "Fly Away", Category.POP),
            new Question(R.raw.whiskey_in_the_jar, "Whiskey In The Jar", Category.ROCK),
            new Question(R.raw.sprzedawcy_marzen, "Sprzedawcy Marzeń", Category.ROCK),
            new Question(R.raw.dont_look_back_in_anger, "Don't Look Back In Anger", Category.ROCK),
            new Question(R.raw.all_the_right_moves, "All The Right Moves", Category.POP),
            new Question(R.raw.i_want_to_break_free, "I Want To Break Free", Category.SEVENTIESANDEIGHTIS),
            new Question(R.raw.dani_california, "Dani California", Category.ROCK),
            new Question(R.raw.should_i_stay_or_should_i_go, "Should I Stay Or Should I Go", Category.ROCK),
            new Question(R.raw.my_generation, "My Generation", Category.ROCK),
            new Question(R.raw.with_or_without_you, "With Or Without You", Category.SEVENTIESANDEIGHTIS)

    };

    /**
     * Creaate a new Question object.
     *
     * @param AudioResource is the resource ID for the audio file associated with this question
     * @param answer        is the correct answer for the question (4)
     */
    private Question(int AudioResource, String answer, Category category) {
        mAudioResource = AudioResource;
        mAnswer = answer;
        mCategory = category;
    }

    public int getAudioResource() {
        return mAudioResource;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mAudioResource=" + mAudioResource +
                ", mAnswer='" + mAnswer + '\'' +
                '}';
    }
}
