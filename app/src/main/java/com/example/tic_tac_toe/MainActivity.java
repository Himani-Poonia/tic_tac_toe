package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 is yellow and 1 is red
    int activePlayer = 1;

    //array will tell the state of each block
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4 ,7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //state of game whether game is won by someone or not
    boolean gameActive = true;

    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            counter.setTranslationY(-1500);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 1) {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            } else {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            }


            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String winner = "Player ";

                    if (activePlayer == 0) {

                        winner += "Red ";
                    } else {

                        winner+= "Yellow ";
                    }

                    gameActive = false;

                    Button playAgain = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    playAgain.setVisibility(View.VISIBLE);

                    winnerText.setVisibility(View.VISIBLE);

                    winnerText.setText(winner + "has Won");
                }
            }
        }

    }

    //when PlayAgainButton is clicked
    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerText.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView dots = (ImageView) gridLayout.getChildAt(i);

            //make every dot invisible
            dots.setImageDrawable(null);
        }

        //0 is yellow and 1 is red
        activePlayer = 1;

        //array will tell the state of each block
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        //state of game whether game is won by someone or not
        boolean gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}