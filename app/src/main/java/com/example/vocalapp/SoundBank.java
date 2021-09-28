package com.example.vocalapp;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controls the storage and playback of the sound files
 *
 * @author Sheldyn Smith
 */
public class SoundBank {
    private Context context;
    private SoundPool soundPool;
    private ProgressBar progressBar;
    private boolean soundsLoading;

    private static final String TAG = "SoundBank";

    private int sound00C2, sound01CS2, sound02D2, sound03DS2, sound04E2, sound05F2,
            sound06FS2, sound07G2, sound08GS2, sound09A2, sound10AS2, sound11B2,
            sound12C3, sound13CS3, sound14D3, sound15DS3, sound16E3, sound17F3,
            sound18FS3, sound19G3, sound20GS3, sound21A3, sound22AS3, sound23B3,
            sound24C4, sound25CS4, sound26D4, sound27DS4, sound28E4, sound29F4,
            sound30FS4, sound31G4, sound32GS4, sound33A4, sound34AS4, sound35B4,
            sound36C5, sound37CS5, sound38D5, sound39DS5, sound40E5, sound41F5,
            sound42FS5, sound43G5, sound44GS5, sound45A5, sound46AS5, sound47B5,
            sound48C6, sound49CS6, sound50D6, sound51DS6, sound52E6;

    private int[] pianoSounds;

    /**
     * Constructor for the SoundBank class
     *
     * Takes an Android Context object and stores it as a member object. Instantiates a SoundPool
     * object using and AudioAttributes Builder to to specify its settings if the Android version
     * is Lollipop or higher, otherwise uses the deprecated method of creating a SoundPool object.
     * Finally, calls the loadSounds function.
     *
     * @param context The context of the application
     * @param progressBar Reference to loading bar
     */
    public SoundBank(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
        soundsLoading = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        loadSounds();
    }

    /**
     * Loads the audio files into the SoundPool object.
     *
     * The Android Context object and a reference to the audio files are used as parameters to
     * the SoundPool load function. The priority of each sound is set to the same value. When a
     * sound is loaded an int is returned which identifies the specific sound. These ints are then
     * stored in an array.
     */
    private void loadSounds() {
        sound00C2 = soundPool.load(context, R.raw.piano00c2, 1);
        sound01CS2 = soundPool.load(context, R.raw.piano01cs2, 1);
        sound02D2 = soundPool.load(context, R.raw.piano02d2, 1);
        sound03DS2 = soundPool.load(context, R.raw.piano03ds2, 1);
        sound04E2 = soundPool.load(context, R.raw.piano04e2, 1);
        sound05F2 = soundPool.load(context, R.raw.piano05f2, 1);
        sound06FS2 = soundPool.load(context, R.raw.piano06fs2, 1);
        sound07G2 = soundPool.load(context, R.raw.piano07g2, 1);
        sound08GS2 = soundPool.load(context, R.raw.piano08gs2, 1);
        sound09A2 = soundPool.load(context, R.raw.piano09a2, 1);
        sound10AS2 = soundPool.load(context, R.raw.piano10as2, 1);
        sound11B2 = soundPool.load(context, R.raw.piano11b2, 1);

        sound12C3 = soundPool.load(context, R.raw.piano12c3, 1);
        sound13CS3 = soundPool.load(context, R.raw.piano13cs3, 1);
        sound14D3 = soundPool.load(context, R.raw.piano14d3, 1);
        sound15DS3 = soundPool.load(context, R.raw.piano15ds3, 1);
        sound16E3 = soundPool.load(context, R.raw.piano16e3, 1);
        sound17F3 = soundPool.load(context, R.raw.piano17f3, 1);
        sound18FS3 = soundPool.load(context, R.raw.piano18fs3, 1);
        sound19G3 = soundPool.load(context, R.raw.piano19g3, 1);
        sound20GS3 = soundPool.load(context, R.raw.piano20gs3, 1);
        sound21A3 = soundPool.load(context, R.raw.piano21a3, 1);
        sound22AS3 = soundPool.load(context, R.raw.piano22as3, 1);
        sound23B3 = soundPool.load(context, R.raw.piano23b3, 1);

        sound24C4 = soundPool.load(context, R.raw.piano24c4, 1);
        sound25CS4 = soundPool.load(context, R.raw.piano25cs4, 1);
        sound26D4 = soundPool.load(context, R.raw.piano26d4, 1);
        sound27DS4 = soundPool.load(context, R.raw.piano27ds4, 1);
        sound28E4 = soundPool.load(context, R.raw.piano28e4, 1);
        sound29F4 = soundPool.load(context, R.raw.piano29f4, 1);
        sound30FS4 = soundPool.load(context, R.raw.piano30fs4, 1);
        sound31G4 = soundPool.load(context, R.raw.piano31g4, 1);
        sound32GS4 = soundPool.load(context, R.raw.piano32gs4, 1);
        sound33A4 = soundPool.load(context, R.raw.piano33a4, 1);
        sound34AS4 = soundPool.load(context, R.raw.piano34as4, 1);
        sound35B4 = soundPool.load(context, R.raw.piano35b4, 1);

        sound36C5 = soundPool.load(context, R.raw.piano36c5, 1);
        sound37CS5 = soundPool.load(context, R.raw.piano37cs5, 1);
        sound38D5 = soundPool.load(context, R.raw.piano38d5, 1);
        sound39DS5 = soundPool.load(context, R.raw.piano39ds5, 1);
        sound40E5 = soundPool.load(context, R.raw.piano40e5, 1);
        sound41F5 = soundPool.load(context, R.raw.piano41f5, 1);
        sound42FS5 = soundPool.load(context, R.raw.piano42fs5, 1);
        sound43G5 = soundPool.load(context, R.raw.piano43g5, 1);
        sound44GS5 = soundPool.load(context, R.raw.piano44gs5, 1);
        sound45A5 = soundPool.load(context, R.raw.piano45a5, 1);
        sound46AS5 = soundPool.load(context, R.raw.piano46as5, 1);
        sound47B5 = soundPool.load(context, R.raw.piano47b5, 1);

        sound48C6 = soundPool.load(context, R.raw.piano48c6, 1);
        sound49CS6 = soundPool.load(context, R.raw.piano49cs6, 1);
        sound50D6 = soundPool.load(context, R.raw.piano50d6, 1);
        sound51DS6 = soundPool.load(context, R.raw.piano51ds6, 1);
        sound52E6 = soundPool.load(context, R.raw.piano52e6, 1);


        // Listener for when a sound completes loading
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            int progress = 0;

            /**
             * Updates the progress bar when a sound finishes loading
             * @param soundPool Object containing collection of sounds
             * @param sampleId ID of the completed task
             * @param status Status of the completed task
             */
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                progress += 2;
                progressBar.setProgress(progress);

                if (progress >= 100) {
                    progressBar.setVisibility(View.GONE);
                    soundsLoading = false;
                }
            }
        });

        pianoSounds = new int[]{sound00C2, sound01CS2, sound02D2, sound03DS2, sound04E2, sound05F2,
                sound06FS2, sound07G2, sound08GS2, sound09A2, sound10AS2, sound11B2,
                sound12C3, sound13CS3, sound14D3, sound15DS3, sound16E3, sound17F3,
                sound18FS3, sound19G3, sound20GS3, sound21A3, sound22AS3, sound23B3,
                sound24C4, sound25CS4, sound26D4, sound27DS4, sound28E4, sound29F4,
                sound30FS4, sound31G4, sound32GS4, sound33A4, sound34AS4, sound35B4,
                sound36C5, sound37CS5, sound38D5, sound39DS5, sound40E5, sound41F5,
                sound42FS5, sound43G5, sound44GS5, sound45A5, sound46AS5, sound47B5,
                sound48C6, sound49CS6, sound50D6, sound51DS6, sound52E6};
    }

    boolean isLoading() {return soundsLoading;}

    /**
     * Plays through all the loaded sounds for testing purposes.
     */
    public void playAllNotes() {
        try {
            int duration = 500;

            for (int i = 0; i < pianoSounds.length; i++) {
                int sound = soundPool.play(pianoSounds[i], 0, 0, 0, 0, 1);
                fadeInNote(sound);
                Thread.sleep(duration);
                fadeOutNote(sound);
                Log.i(TAG, "playStartingNote sound number: " + Integer.toString(sound));
            }
        }
        catch (Exception e) {
            Log.w(TAG, "playStartingNote Exception: ", e);
        }
    }

    /**
     * Calls the functions needed to play a note
     *
     * Takes two ints as parameters, one representing the index of the note in the array of sounds,
     * the other is the duration of the notes in milliseconds. The SoundPool object play function is
     * called and given a reference to the sound to be played; the volume is initially set to 0.
     * The fadeInNote method is called taking a reference to the playing sound as a parameter. The
     * Thread then sleeps for the specified duration, after which the fadeOutNote method is called.
     *
     * @param note Index of the note to be played
     * @param duration Number of milliseconds that a sound should play for
     */
    public void playNote(int note, int duration) {
        try {
            int sound = soundPool.play(pianoSounds[note], 0, 0, 0, 0, 1);
            fadeInNote(sound);
            Thread.sleep(duration);
            fadeOutNote(sound);
            Log.i(TAG, "playStartingNote sound number: " + Integer.toString(sound));
        }
        catch (Exception e) {
            Log.w(TAG, "playStartingNote Exception: ", e);
        }
    }

    /**
     * Raises the volume of a sound over a short period of time.
     *
     * This method is used to prevent clicks and pops in the audio when a sound begins to play.
     * It takes an int representing the playing sound which will have its volume adjusted. A
     * TimerTask is used, which is similar to AsyncTask and repeats a block a block of code with
     * a specified interval of time between each iteration. Each iteration increases the volume of
     * the sound until it reaches full volume, at which point the TimerTask is canceled.
     * @param soundID ID number of the sound that is playing
     */
    private void fadeInNote(final int soundID) {
        TimerTask timerTask = new TimerTask() {
            float volume = (float)0.0;
            @Override
            public void run() {
                if (volume < 1.0) {
                    volume += 0.1;
                }
                else {
                    this.cancel();
                }
                soundPool.setVolume(soundID, volume, volume);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1);
        timer.purge();
    }

    /**
     * Lowers the volume of a note over a period of time.
     *
     * The purpose of this method is to simulate the release of a note that has been played without
     * having an abrupt cut-off of the sound. It works in the same fashion as the fadeInNote method,
     * but lowers the volume until it reaches 0.
     * @see #fadeInNote(int)
     * @param soundID ID number of the sound that is playing
     */
    private void fadeOutNote(final int soundID) {
        TimerTask timerTask = new TimerTask() {
            float volume = (float)1.0;
            @Override
            public void run() {
                if (volume > 0.0) {
                    volume -= 0.01;
                }
                else {
                    soundPool.stop(soundID);
                    this.cancel();
                }
                soundPool.setVolume(soundID, volume, volume);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1);
        timer.purge();
    }

    /**
     * Releases the SoundPool object from memory.
     */
    public void releaseResources() {
        soundPool.release();
    }
}
