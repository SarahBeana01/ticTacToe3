package com.zybooks.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    private ticTacToeGame mGame;
    private GridLayout mSquareGrid;
    private int mSquareImageX;
    private int mSquareImageO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Drawable myX = getResources().getDrawable(R.drawable.myX);

        mSquareImageX = findViewById(R.);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSquareGrid = findViewById(R.id.square_grid);

        mLightOnColor = ContextCompat.getColor(this, R.color.yellow);
        mLightOffColor = ContextCompat.getColor(this, R.color.black);

        mGame = new ticTacToeGame();
        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setButtonColors();
        }
    }
    public void onNewGameClick(View view) {
        // Creates public function called onNewGameClick
        startGame();
        // Calls function startGame
    }
}