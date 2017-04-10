package pl.wojciech.smol.jakatomelodia;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.math3.exception.MathIllegalNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayActivity extends AppCompatActivity {

    // number of possible answers
    private static final int MAX_ANSWERS = 4;
    //Game object
    public static Game game;
    // List of possible answers
    List<Question> mPossibleAnswers;
    // Answer button top-left
    private Button buttonAnswer0;
    // Answer button top-right
    private Button buttonAnswer1;
    // Answer button bottom-left
    private Button buttonAnswer2;
    // Answer button bottom-right
    private Button buttonAnswer3;
    //Question TextView
    private TextView questionText;
    // Progress Bar indicating song fragment time
    private SeekBar progressBar;
    // Audio file startTime
    private double startTime = 0;
    //Audio file finalTime
    private double finalTime = 0;
    // Handler for Runnable
    private final Handler myHandler = new Handler();
    /**
     * Handles playback of the sound file
     */
    private MediaPlayer mMediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            //Once the audio file stops playing I clean up the resources
            releaseMediaPlayer();
            //!!!! HERE'S THE PROBLEM FOR EXAMPLE. THE ACTIVITY QUITS HERE BUT IT SHOULD NOT!

            for (int i = 3; i >= 0; i--) {
                final int iFinal = i;

                myHandler.postDelayed(new Runnable() {
                    public void run() {
                        Toast toast = Toast.makeText(PlayActivity.this, "KONIEC CZASU. Kolejne pytanie za: " + iFinal, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 200);
                        toast.show();
                    }
                }, 100);
            }
            myHandler.postDelayed(new Runnable() {
                public void run() {
                    Toast.makeText(PlayActivity.this, "Huhu", Toast.LENGTH_SHORT).show();
                    //setGravity(Gravity.TOP, 0, 0).
                }
            }, 400);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initializing required elements
        initialization();


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Clean up resources
        releaseMediaPlayer();
        //Start audio
        startAudio();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Pause the media player
        mMediaPlayer.pause();

    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume the media player
        mMediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Clean up resources
        releaseMediaPlayer();
        // HERE'S THE PROBLEM TOO WHEN I CLICK "BACK" BUTTON IT GOES TO THE MAIN ACTIVITY BUT
        // THE MAIN ACTIVITY QUITS
    }

    //Initializes required elements
    private void initialization() {
        buttonAnswer0 = (Button) findViewById(R.id.button_answer0);
        buttonAnswer1 = (Button) findViewById(R.id.button_answer1);
        buttonAnswer2 = (Button) findViewById(R.id.button_answer2);
        buttonAnswer3 = (Button) findViewById(R.id.button_answer3);
        questionText = (TextView) findViewById(R.id.question_text);
        progressBar = (SeekBar) findViewById(R.id.progress_bar);
        progressBar.setClickable(false);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        questionText.setText(getString(R.string.question, (game.getQuestionNumber() + 1), Game.MAX_QUESTIONS));
        // filling mPossibleAnswer Array:
        setPossibleAnswers();


        // set Answers Texts on the Buttons
        buttonAnswer0.setText(mPossibleAnswers.get(0).getAnswer());
        buttonAnswer1.setText(mPossibleAnswers.get(1).getAnswer());
        buttonAnswer2.setText(mPossibleAnswers.get(2).getAnswer());
        buttonAnswer3.setText(mPossibleAnswers.get(3).getAnswer());
    }

    private void setPossibleAnswers() {
        mPossibleAnswers = new ArrayList<Question>();
        mPossibleAnswers.add(game.getCurrentQuestion());

        int[] randomIndexes = new int[MAX_ANSWERS - 1];

        try {
            //nextPermutation generates an integer array without repetition
            randomIndexes = Game.generator.nextPermutation((Question.mQuestions.length - 1), MAX_ANSWERS - 1);
        } catch (MathIllegalNumberException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < MAX_ANSWERS - 1; i++) {
            // if there isn't such question in ArrayList add it
            if (!mPossibleAnswers.contains(Question.mQuestions[randomIndexes[i]])) {
                mPossibleAnswers.add(Question.mQuestions[randomIndexes[i]]);
            } else {
                // new index
                int j;
                do {
                    // searching for unique question
                    j = Game.generator.nextInt(0, Question.mQuestions.length - 1);

                } while (mPossibleAnswers.contains(Question.mQuestions[j]));
                // add question to Array
                mPossibleAnswers.add(Question.mQuestions[j]);
            }

        }
        // shuffle answers so that the correct answer isn't always at first place
        Collections.shuffle(mPossibleAnswers);

    }

    private void startAudio() {

        // Request audio focus so in order to play the audio file.
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // We have audio focus now.

            // Create and setup the {@link MediaPlayer} for the audio resource associated
            // with the current word
            mMediaPlayer = MediaPlayer.create(PlayActivity.this, game.getCurrentQuestion().getAudioResource());

            // Start the audio file
            mMediaPlayer.start();

            startTime = mMediaPlayer.getCurrentPosition();
            finalTime = mMediaPlayer.getDuration();
            // Setup a listener on the media player, so that we can stop and release the
            // media player once the sound has finished playing.
            mMediaPlayer.setOnCompletionListener(mCompletionListener);

            // initializing progressBar
            progressBar.setProgress((int) startTime);
            progressBar.setMax((int) finalTime);
            // Starting progress Bar
            myHandler.postDelayed(UpdateSongTime, 100);
        }


    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            // Updating progressBar each second based on audio being played
            startTime = mMediaPlayer.getCurrentPosition();
            progressBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
