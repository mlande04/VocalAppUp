package com.example.vocalapp;

import android.os.AsyncTask;
import android.util.Log;
import java.util.List;

public class Exercise {

    /** Activity Specific Tag for Logging and Debugging */
    private static final String TAG = "ExerciseClass";

    /**Object that holds all of the piano sound sample files to be played */
    private transient SoundBank soundBank;

    /**
     * The starting note of the exercise;
     * to be calculated based off the vocal range and exercises selected.
      */
    private int startingNote;

    /** A list of the starting values for each vocal range for the given exercise */
    private List<Integer> startingPositions;

    /** The vocal range selected in the Vocal Range Activity */
    private String vocalRange;

    /** The name of the exercise. Loaded through JSON. */
    private String name;

    /** The description of the exercise. Loaded through JSON. */
    private String description;

    /** A list of integers representing the intervals between notes played. Loaded through JSON. */
    private List<Integer> notes;

    /**
     *  A list of integers representing the length of time in milliseconds
     *  that notes should be played for. Loaded through JSON.
     *  */
    private List<Double> durations;

    /** The interval by which notes in the warm-up should be adjusted each cycle. Loaded through JSON. */
    private int transpositionInterval;

    /** The number of times that a warm-up can be cycled through without walking off the edge of piano */
    private int maxTranspositions;

    /** A boolean keeping track of whether there is an instance of the warm-up already playing. */
    private boolean isRunning = false;

    /** A boolean keeping track of whether the warm-up has been cancelled. */
    private boolean isCanceled;

    /** An AsyncTask dedicated to playing the warm-up */
    private ExerciseTask exerciseTask;

    /** The tempo of an exercise. Loaded through JSON. */
    private double tempo;

    /** The amount by which an exercise accelerates after each iteration */
    private float accelerando;

    /** The name of the image for the exercise. Loaded through JSON. */
    private String imageName;

    /** String for name of activity group, loaded from JSON. */
    public String activity;

    public String getName() {
        return name;
    }

    public List<Integer> getNotes() {
        return notes;
    }

    public List<Double> getDurations() {
        return durations;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {return imageName;}

    public void setVocalRange (String vocalRange) {this.vocalRange = vocalRange;}

    public String getVocalRange() { return vocalRange; }

    public String getActivityGroup() { return activity; }

    /**
     * Constructor for exercise class --
     * Note: Only the default constructor is called when an object
     * is created through GSON.
     */
    public Exercise() {
        soundBank = MainActivity.soundBank;
    }

    /**
     * Calculates the starting note based off the vocal range selected and the
     * accompanying value in the exercise JSON
     */
    private void findStartingNote() {


        switch (vocalRange) {
            case "Soprano":
                startingNote = startingPositions.get(3);
                break;
            case "Alto":
                startingNote = startingPositions.get(2);
                break;
            case "Tenor":
                startingNote = startingPositions.get(1);
                break;
            case "Bass":
                startingNote = startingPositions.get(0);
                break;
            default:
                Log.e(TAG, "Exercise() - Error: Unexpected Vocal Range");
                //For choose a note functionality.
                //startingNote = Integer.parseInt(vocalRange);
        }

    }

    /**
     * Calculates the number of times that the exercise can be played without walking off the edge
     * of the piano given the current transposition value.
     */
    private void calcMaxTransposition() {
        //If warm-up is ascending.
        if (transpositionInterval > 0) {
            int highestNote = 0;
            for (Integer note : notes) {
                if (highestNote < note)
                    highestNote = note;
            }

            maxTranspositions = (52 - (highestNote + startingNote)) / transpositionInterval;
        }
        //If the exercise remains on the same note without transposing.
        else if (transpositionInterval == 0) {
            //TODO: Do something here. How do we want to calculate iterations here?
            maxTranspositions = 15;
        }
        //If warm-up is descending
        else {
            int lowestNote = 52;
            for (Integer note : notes) {
                if (lowestNote > note)
                    lowestNote = note;
            }

            maxTranspositions = (lowestNote + startingNote) / -(transpositionInterval);
        }
    }

    /**
     * Returns bool for if the soundBank is loading
     * @return Where the SoundBank is loading
     */
    boolean isLoading() {return soundBank.isLoading();}

    /**
     * Begins playing the warm-up
     */
    void runExercise() {

        if (!isRunning) {
            findStartingNote();
            calcMaxTransposition();

            isCanceled = false;
            exerciseTask = new ExerciseTask();
            exerciseTask.execute();
        }
    }

    /**
     * Stops the exercise if isCanceled is true
     */
    void cancelExercise() {

        isCanceled = true;
    }

    /**
     * AsyncTask to play the exercise audio
     */
    private class ExerciseTask extends AsyncTask<Void, Void, Void> {

        /**
         * Sets isRunning flag to true
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isRunning = true;
        }

        /**
         * Plays exercise on a new thread
         *
         * Contains nested for loops for iterating through the notes of the exercise and then
         * transposing the exercise to another musical key. The outer loop counts the transpositions
         * and multiplies it by the transposition interval, which is then added to the starting
         * note's index to find the first note of the current run through the exercise. The inner loop
         * iterates through the exercise notes array. Each value in the array is the number of
         * half-steps a note is away from the starting note. These values are added to the index
         * of the first note of the exercise for the current transposition iteration to find the
         * index of the note to be played. <br><br>
         *
         * The duration of a quarter note is found by dividing 60000 by the tempo, which is then
         * multiplied by values in the durations array, which are factors to calculate the duration
         * of a note in relation to a quarter note. The calculated value is the number of millisecond
         * a note should play for. <br><br>
         *
         * The index of the note to be played and the note duration are parameters to the playNote
         * function of the soundBank object.
         *
         * @param voids nothing
         * @return null
         */
        @Override
        protected Void doInBackground(Void... voids) {

            //Set Initial Note Duration and Tempo
            double quartNoteDuration = 60000.0 / tempo;
            double currentTempo = tempo;

            //Begin loop for exercise
            for (int i = 0; i < maxTranspositions; i++ ) {

                //Select first note to be played
                int firstNote = startingNote + i * transpositionInterval;

                //Play through each note in the exercise
                for (int j = 0; j < notes.size(); j++) {
                    Double noteDuration = quartNoteDuration * durations.get(j);
                    soundBank.playNote(notes.get(j) + firstNote, noteDuration.intValue());
                    if (isCanceled) {
                        break;
                    }
                }
                if (isCanceled) {
                    break;
                }
                //
                if(accelerando > 1) {
                    //Adjust and update tempo
                    currentTempo = currentTempo * accelerando;
                    quartNoteDuration = 60000 / currentTempo;
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * Sets the isRunning flag to false when the exercise stops playing.
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            isRunning = false;
        }
    }
}
