package com.zybooks.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

public class ticTacToeGame extends AppCompatActivity {
    public static final int GRID_SIZE = 3;
    private final char[][] mGameGrid;
    public String whoWon;




    //This will be used to create an instance of this class over in other places.
    public ticTacToeGame() {
        mGameGrid = new char[GRID_SIZE][GRID_SIZE];
        newGame();
    }

    /*
    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++){
                mImageButton = mGridView.getChildAt()
            }
        }
    }
    */

    //testing out an alternate newGame method
    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++){
                mGameGrid[row][col] = 'W'; //using 'W' to resemble white, or an empty ImageButton
            }
        }
    }

    public Character getCurrentSymbol(int row, int col) {
        return mGameGrid[row][col];
    }

    public void selectImageButton(int row, int col, char currentTurn) {
        mGameGrid[row][col] = currentTurn;
    }

    /*  Not in use yet, possibly not needed
    public String getGameState() {
        //This gets the current state of all the ImageButtons

        StringBuilder boardString = new StringBuilder();
        //Here we are creating a new instance of the StringBuilder class named boardString. This will hold the state of the lights.

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {

                char value = mGameGrid[row][col];

                boardString.append(value);
                //Here it just appends the character to the end of boardString
            }
        }

        return boardString.toString();
        //This is just returning the string we just built, it will be used to recover after the device is rotated primarily
    }
*/
    public boolean isGameOver() {

        for(int index = 0; index < GRID_SIZE; index++) {
            //These first two if statements should iterate through every row and column respectively looking for any non diagonal matches.
            //Making sure to include the "&& mGameGrid[][] != 'W'" at the end of each if statement to make sure it doesn't declare white as the winner.
            if(mGameGrid[index][0] == mGameGrid[index][1] && mGameGrid[index][1] == mGameGrid[index][2] && mGameGrid[index][0] != 'W') {
                whoWon = mGameGrid[index][0] + " won the game";
                return true;
            }
            if(mGameGrid[0][index] == mGameGrid[1][index] && mGameGrid[1][index] == mGameGrid[2][index] && mGameGrid[0][index] != 'W') {
                whoWon = mGameGrid[0][index] + " won the game";
                return true;
            }
        }
        //these next two should be checking the diagonals for matches.
        if(mGameGrid[0][0] == mGameGrid[1][1] && mGameGrid[1][1] == mGameGrid[2][2] && mGameGrid[0][0] != 'W') {
            whoWon = mGameGrid[0][0] + " won the game";
            return true;
        }
        if(mGameGrid[0][2] == mGameGrid[1][1] && mGameGrid[1][1] == mGameGrid[2][0] && mGameGrid[0][2] != 'W') {
            whoWon = (mGameGrid[0][2]) + " won the game";
            return true;
        }
        if (allFilled()) {
            whoWon = "CAT";
            return true;
        }
        return false;
    }

    public boolean allFilled() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {

                if(mGameGrid[row][col] == 'W') {
                    return false;
                }
            }
        }
        return true;
    }

}
