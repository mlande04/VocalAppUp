package com.example.vocalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Activity for selection of Vocal Range. Displays four options for user to choose from: Soprano,
 * Alto, Tenor, and Bass. Creates an ExerciseActivity and includes user selection in Intent.
 *
 * @author John Batty
 * @version 0.0.1
 * @since 0.0.0
 */
public class VocalRangeActivity extends AppCompatActivity {

    public static final String VOCAL_RANGE = "vocalRange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocal_range);

        // added for menu bar
        Toolbar appToolbar = findViewById(R.id.menu_bar);
        setSupportActionBar(appToolbar);
    }

    /**
     * Retrieves text from button pressed by user. Passes text into intent and starts an
     * ExerciseActivity.
     * @param view The View of the calling button
     */
    public void onSelection(View view) {

        //Cast View as button and retrieve text
        Button b = (Button) view;
        String buttonText = b.getText().toString();

        //Create Intent and include button text
        Intent intentIn = getIntent();
        Intent intentOut = new Intent(getApplicationContext(),ExerciseActivity.class);
        intentOut.putExtra(ExerciseActivity.FILE_NAME, intentIn.getStringExtra(ExerciseActivity.FILE_NAME));
        intentOut.putExtra(VOCAL_RANGE,buttonText);

        //Launch new activity
        startActivity(intentOut);
    }
}
