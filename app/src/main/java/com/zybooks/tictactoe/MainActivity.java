package com.zybooks.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ticTacToeGame mGame;
    private GridLayout mGridView;
    public Character currentTurn = 'X';
    Drawable xIcon;
    Drawable oIcon;
    Drawable whiteForeground;
    TextView whosTurnText;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xIcon = getDrawable(R.drawable.x_icon);
        oIcon = getDrawable(R.drawable.o_icon);
        whiteForeground = getDrawable(R.drawable.ticatac_white_box_foreground);
        whosTurnText = findViewById(R.id.current_turn);
        mGridView = findViewById(R.id.game_grid);
        mGame = new ticTacToeGame();

        for (int buttonIndex = 0; buttonIndex < mGridView.getChildCount(); buttonIndex++) {
            //This is a for loop that iterates through each cell in the mLightGrid getting the count with getChildCount().

            ImageButton gridButton = (ImageButton) mGridView.getChildAt(buttonIndex);
            //This making it so the button at the current index is gridButton, this will be used on the next line

            gridButton.setOnClickListener(this::onImageButtonClick);
            //This is assigning an onClickListener to gridButton, its basically saying that when you click the gridButton I want onLightButtonClick to be called
        }

        //Can probably omit this part, we don't need to do any restoration.
        /*
        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setGameState(gameState);
            setButtonColors();
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_new_game) {
            startGame();
        }
        if(item.getItemId() == R.id.action_quit) {
            quitGame();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onImageButtonClick(View view) {
        //This is called whenever you click a light-button

        // Find the button's row and col
        int buttonIndex = mGridView.indexOfChild(view);
        //This gets the index of the child using the indexOfChild method

        int row = buttonIndex / ticTacToeGame.GRID_SIZE;
        //This finds the row by dividing the index by the grid size, I would have never thought of it but good on them

        int col = buttonIndex % ticTacToeGame.GRID_SIZE;
        //This finds the column using the remainder operator, I'm not going to pretend like I understand it

        //This prevents players from overwriting others spots.
        if(mGame.getCurrentSymbol(row, col) != 'W') {
            return;
        }

        mGame.selectImageButton(row, col, currentTurn);
        //This calls the selectImageButton method from ticTacToeGame on the current child

        setButtonImages();
        //without this the buttons would be invisible

        //This swaps the turn to the next person
        if(currentTurn == 'X') {
            currentTurn = 'O';
            whosTurnText.setText(R.string.o_turn);
        } else {
            currentTurn = 'X';
            whosTurnText.setText(R.string.x_turn);
        }

        // Congratulate the user if the game is over
        if (mGame.isGameOver()) {
            //If isGameOver returns true you'll get a congrats toast


            Toast.makeText(this, mGame.whoWon, Toast.LENGTH_SHORT).show();
            //Here's the toast code, it uses the congrats string, with a short length
        }
    }

    //This is our method that will update the buttons backgrounds to the appropriate image/color.
    private void setButtonImages() {
        for (int buttonIndex = 0; buttonIndex < mGridView.getChildCount(); buttonIndex++) {
            //This iterates through each child in the gridview, it got the count with getChildCount

            ImageButton gridButton = (ImageButton) mGridView.getChildAt(buttonIndex);
            //as its iterating through each child it is making it gridButton

            int row = buttonIndex / ticTacToeGame.GRID_SIZE;
            int col = buttonIndex % ticTacToeGame.GRID_SIZE;

            if (mGame.getCurrentSymbol(row, col) == 'X') {
                gridButton.setImageDrawable(xIcon);
                gridButton.setContentDescription(Integer.toString(R.string.letter_x));
            }
            else if (mGame.getCurrentSymbol(row, col) == 'O') {
                gridButton.setImageDrawable(oIcon);
                gridButton.setContentDescription(Integer.toString(R.string.letter_o));
            } else {
                gridButton.setImageDrawable(whiteForeground);
            }
        }
    }
     private void startGame() {
        mGame.newGame();
        setButtonImages();
     }


     public void quitGame(){
        FragmentManager manager = getSupportFragmentManager();
        QuitDialogFragment dialogFragment = new QuitDialogFragment();
        dialogFragment.show(manager, "quitDialog");
    }
}
