package pl.wojciech.smol.jakatomelodia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.math3.exception.MathIllegalNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static pl.wojciech.smol.jakatomelodia.R.string.question;


public class PlayActivity extends AppCompatActivity {

    // number of possible answers
    private static final int MAX_ANSWERS = 4;
    //Game object
    public static Game game;
    // Toast duration in miliseconds
    private static final int TOAST_DURATION = 700;
    // Transition time in miliseconds
    private static final int TRANSITION_TIME = 1100;
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
    // Progress Bar indicating song fragment time
    private SeekBar progressBar;
    // Audio file startTime
    private double startTime = 0;
    //Was button clicked
    private boolean wasButtonBlocked;
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
     * Handles playback of the answer sound file (correct or wrong)
     */
    private MediaPlayer mAnswerMediaPlayer;


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

            //Once the audio file stops playing (ONLY AT THE END OF FILE)I clean up the resources
            releaseMediaPlayer();

            // only if this is the end of the file (none of the buttons was clicked)
            if (!wasButtonBlocked) {
                // Creating Media Player Object playing Wrong answer sound and starting it
                mAnswerMediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.wrong_answer);
                mAnswerMediaPlayer.start();
                //Blocking the buttons
                blockButtons();
                // Showing a toast with info that time's up
                final Toast toast = Toast.makeText(PlayActivity.this, getString(R.string.time_up), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                // Cancelling Toast so that it lasts time specified in TOAST_DURATION
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, TOAST_DURATION);

                // The transition goes after TRANSITION TIME:
                myHandler.postDelayed(new Runnable() {
                    public void run() {

                        // If this is the end of the game go to GameEndActivity
                        if (game.endOfAGame()) {
                            game.wrongAnswer();
                            startActivity(new Intent(PlayActivity.this, GameEndActivity.class));
                        }  // Otherwise go to next question
                        else {
                            game.wrongAnswer();
                            startActivity(new Intent(PlayActivity.this, PlayActivity.class));
                        }
                    }
                }, TRANSITION_TIME);
            }
        }


    };

    // LISTENER FOR ALL FOUR BUTTONS
    private View.OnClickListener buttonAnswerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Blocking the buttons
            blockButtons();
            // Stop playback and release resource
            releaseMediaPlayer();
            // Answer button initialization
            Button answerButton = (Button) v;
            // Text from the button selected
            String buttonText = answerButton.getText().toString();

            // IF THE ANSWER WAS CORRECT (buttonText is equal to the song title)
            if (buttonText.equals(game.getCurrentQuestion().getAnswer())) {

                // Creating Media Player Object playing Correct answer sound and starting it
                mAnswerMediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.correct_answer);
                mAnswerMediaPlayer.start();
                // Showing a toast with info that the answer was correct
                final Toast toast = Toast.makeText(PlayActivity.this, getString(R.string.correct_answer), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                // Cancelling Toast so that it lasts time specified in TOAST_DURATION
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, TOAST_DURATION);

                // The transition goes after TRANSITION TIME:
                myHandler.postDelayed(new Runnable() {
                                          public void run() {
                                              // If this is the end of the game go to GameEndActivity
                                              if (game.endOfAGame()) {
                                                  game.correctAnswer();
                                                  startActivity(new Intent(PlayActivity.this, GameEndActivity.class));
                                              } // Otherwise go to next question
                                              else {
                                                  game.correctAnswer();
                                                  startActivity(new Intent(PlayActivity.this, PlayActivity.class));
                                              }

                                          }
                                      }

                        , TRANSITION_TIME);

            } // IF THE ANSWER WAS WRONG:
            else {
                // Creating Media Player Object playing wrong answer sound and starting it
                mAnswerMediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.wrong_answer);
                mAnswerMediaPlayer.start();
                // Showing a toast with info that the answer was wrong
                final Toast toast = Toast.makeText(PlayActivity.this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                // Cancelling Toast so that it lasts time specified in TOAST_DURATION
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, TOAST_DURATION);

                // The transition goes after TRANSITION TIME:
                myHandler.postDelayed(new Runnable() {
                    public void run() {
                        // If this is the end of the game go to GameEndActivity
                        if (game.endOfAGame()) {
                            game.wrongAnswer();
                            startActivity(new Intent(PlayActivity.this, GameEndActivity.class));
                        } // Otherwise go to next question
                        else {
                            game.wrongAnswer();
                            startActivity(new Intent(PlayActivity.this, PlayActivity.class));
                        }
                    }
                }, TRANSITION_TIME);
            }
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
        if (mMediaPlayer != null)
            mMediaPlayer.pause();

    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume the media player
        if (mMediaPlayer != null)
            mMediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Clean up resources
        releaseMediaPlayer();
    }

    // When BACK button is Pressed I invoke the alert
    @Override
    public void onBackPressed() {
        showEndOfGameAlert();
    }

    //Initializes required elements
    private void initialization() {
        buttonAnswer0 = (Button) findViewById(R.id.button_answer0);
        buttonAnswer0.setOnClickListener(buttonAnswerListener);
        buttonAnswer1 = (Button) findViewById(R.id.button_answer1);
        buttonAnswer1.setOnClickListener(buttonAnswerListener);
        buttonAnswer2 = (Button) findViewById(R.id.button_answer2);
        buttonAnswer2.setOnClickListener(buttonAnswerListener);
        buttonAnswer3 = (Button) findViewById(R.id.button_answer3);
        buttonAnswer3.setOnClickListener(buttonAnswerListener);
        wasButtonBlocked = false;
        ImageButton exitIconButton = (ImageButton) findViewById(R.id.exit_icon);
        // Setting onClickLister for the ImageButton
        exitIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If user clicks on exit icon then the alert is shown
                showEndOfGameAlert();
            }
        });
        progressBar = (SeekBar) findViewById(R.id.progress_bar);
        progressBar.setClickable(false);
        // Initializing mAudioManager
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Setting Question text (with correct number)
        TextView questionText = (TextView) findViewById(R.id.question_text);
        questionText.setText(getString(question, (game.getQuestionNumber() + 1), Game.MAX_QUESTIONS));
        // filling mPossibleAnswer Array:
        setPossibleAnswers();


        // set Answers Texts on the Buttons
        buttonAnswer0.setText(mPossibleAnswers.get(0).getAnswer());
        buttonAnswer1.setText(mPossibleAnswers.get(1).getAnswer());
        buttonAnswer2.setText(mPossibleAnswers.get(2).getAnswer());
        buttonAnswer3.setText(mPossibleAnswers.get(3).getAnswer());
    }

    // Sets Other possible answers for the buttons
    private void setPossibleAnswers() throws MathIllegalNumberException {
        // Creating array of possible answers and adding correct answer to this
        mPossibleAnswers = new ArrayList<>(MAX_ANSWERS);
        mPossibleAnswers.add(game.getCurrentQuestion());

        //randomIndexes to get questions from Question class
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

                } // while the array already contains this answer
                while (mPossibleAnswers.contains(Question.mQuestions[j]));
                // add question to Array
                mPossibleAnswers.add(Question.mQuestions[j]);
            }

        }
        // shuffle answers so that the correct answer isn't always at first place
        Collections.shuffle(mPossibleAnswers);

    }

    // Starting main audio file (song audio file)
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

            // setting startTime for the progressBar
            startTime = mMediaPlayer.getCurrentPosition();
            // setting finalTime for the progressBar
            double finalTime = mMediaPlayer.getDuration();
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

    // This process updates progressBar
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            // Updating progressBar each second based on audio being played
            if (mMediaPlayer != null) {
                startTime = mMediaPlayer.getCurrentPosition();
                progressBar.setProgress((int) startTime);
                myHandler.postDelayed(this, 10);
            }
        }
    };

    // Blocking the buttons
    private void blockButtons() {
        buttonAnswer0.setClickable(false);
        buttonAnswer1.setClickable(false);
        buttonAnswer2.setClickable(false);
        buttonAnswer3.setClickable(false);
        wasButtonBlocked = true;
    }

    // stop Audio and release resources from mediaPlayers
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

        // If the media player is not null, then it may be currently playing a sound.
        if (mAnswerMediaPlayer != null) {

            mAnswerMediaPlayer.release();
            mAnswerMediaPlayer = null;
        }
    }

    // This alert ensures that the user really want to cancel the game
    private void showEndOfGameAlert() {
        // Initializing alertBuilder
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PlayActivity.this);
        alertBuilder.setMessage(getString(R.string.cancel_game));
        alertBuilder.setCancelable(true);

        // Setting positive button
        alertBuilder.setPositiveButton(
                getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Go to GameEndActivity
                        startActivity(new Intent(PlayActivity.this, GameEndActivity.class));
                        dialog.cancel();
                    }
                });

        // Setting negative button
        alertBuilder.setNegativeButton(
                getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        //Finally creating alert using builder
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
