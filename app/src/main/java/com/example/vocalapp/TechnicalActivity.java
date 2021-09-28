package com.example.vocalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.R.layout.simple_list_item_1;

/**
 * Activity for displaying list of Technical Exercises
 *
 * This class creates, fills, and displays a list of the Technical exercises available and initializes
 * an event listener to create new ExerciseActivity from menu item selected by user.
 * @author John Batty
 * @version 0.0.1
 * @since 0.0.0
 */
public class TechnicalActivity extends AppCompatActivity {

    /** The ListView to be displayed as a menu. */
    ListView list;

    /** The Menu Items to be displayed in list */
    String displayNames[] = {"Agility Run", "Alphabet", "Me-Roh"};

    /** The name of each file associated with each menu item. */
    String fileNames[] = {"agility_run", "alphabet", "me_roh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical);

        // added for menu bar
        Toolbar appToolbar = findViewById(R.id.menu_bar);
        setSupportActionBar(appToolbar);

        //Fill the list view with data
        list = findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                simple_list_item_1, displayNames);
        list.setAdapter(arrayAdapter);

        //Listen for selection of item on ListView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Create an Intent
                Intent intent = new Intent(getApplicationContext(),VocalRangeActivity.class);

                //Put in the fileName
                intent.putExtra(ExerciseActivity.FILE_NAME, fileNames[position]);

                //Create the activity from the new intent
                startActivity(intent);
            }
        });
    }
}
