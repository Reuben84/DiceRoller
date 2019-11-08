package com.example.diceroller;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score = 0; // Used to store the score when the user guesses correctly
    EditText guessInput; //Used to store the guess value inputted by the user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void on_button_click(View view) { //When the Roll the Dice button is clicked...
        /*Summary
        The user enters a number between 1-6 and clicks the Roll Dice button
        A number 1-6 will then be displayed
        If the number the user entered matches the displayed number a congratulations message appears
        The user's score will increase by 1.
        If no number is entered by the user the value is changed to 1
        If a number greater than 6 is used the value is changed to 6
         */

        TextView scoreCount = findViewById(R.id.ScoreTextView);
        TextView tv = this.findViewById(R.id.numberTextView);
        guessInput = (EditText) findViewById(R.id.numberInputNumber);
        TextView congrats = findViewById(R.id.congratsTextView);
        //Creates instances and matches them with the corresponding IDs

        congrats.setVisibility(view.INVISIBLE); //Sets the congratulations message to invisible

        Random r = new Random();
        int number = r.nextInt( 6 ) + 1;
        tv.setText(Integer.toString(number));

        if(guessInput.getText().toString().length() == 0) { //Checks if the input field is empty
            guessInput.setText("1"); //if it is, change the value to 1 for ease of use - see below
        }

       int guess = Integer.parseInt(guessInput.getText().toString());
        //stores the value inputted by the user

        if (guess > 6) { //If the user's guess is above 6 then...
            guessInput.setText("6"); //Set the guess to 6

            /*Here I decided that I would automatically change any guess higher than the upper bound
           to 6 so that the application would proceed rather than displaying an error message and
           asking the user to try again.
           I did this because I believe it allows the application to run smoother and keeps the
           application simple and easy to use.
            */
        }
        if (guess == number) { //If the user's guess is equal to the random number then...
            score++; //Increments the score
            congrats.setVisibility(view.VISIBLE); //Sets the congratulations message to visible
           }
        scoreCount.setText("Score: " + Integer.toString(score)); //Displays the score
    }

}
