package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button[][]= new Button[3][3];
    private TextView p1;
    private TextView p2;
    private Button reset;
    private int round = 0;
    private Boolean player1Turn = true;
    private int p1score=0,p2score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1=(TextView)findViewById(R.id.p1TextView);
        p2=(TextView)findViewById(R.id.p2TextView);

        reset=(Button)findViewById(R.id.resetButton);

        for (int i = 0; i<3 ; i++){
            for (int j=0;j<3;j++){
                String buttonID = "b"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                button[i][j]=findViewById(resID);
                final int finalI = i;
                final int finalJ = j;

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p1.setText("Player 1 : 0");
                        p2.setText("Player 2 : 0");
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setText("");
                            }
                        }
                    }
                });

                button[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!(button[finalI][finalJ].getText().toString() =="")){
                            return;
                        }
                        if(player1Turn){
                            button[finalI][finalJ].setText("X");
                            round++;
                        }
                        else{
                            button[finalI][finalJ].setText("O");
                            round++;
                        }
                        if(Win()){
                            if (player1Turn) {
                                player1Wins();
                            } else {
                                player2Wins();
                            }
                        }
                        else if (round == 9) {
                            draw();
                        } else {
                            player1Turn = !player1Turn;
                        }
                        }

                    private void draw() {
                        Toast.makeText(MainActivity.this, "Match Draw", Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setText("");
                            }
                        }
                    }
                    private void player1Wins(){
                        p1score++;
                        p1.setText("Player 1 : "+p1score);
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setText("");
                            }
                        }
                        Toast.makeText(MainActivity.this, "Player 1 plays first", Toast.LENGTH_SHORT).show();

                    }
                    private void player2Wins(){
                        p2score++;
                        p2.setText("Player 2 : "+p2score);
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setText("");
                            }
                        }
                        Toast.makeText(MainActivity.this, "Player 2 plays first", Toast.LENGTH_SHORT).show();

                    }

                    private boolean Win() {
                        String[][] field = new String[3][3];
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                field[i][j] = button[i][j].getText().toString();
                            }
                        }
                        for (int i = 0; i < 3; i++) {
                            if (field[i][0].equals(field[i][1])
                                    && field[i][0].equals(field[i][2])
                                    && !field[i][0].equals("")) {
                                return true;
                            }
                        }
                        for (int i = 0; i < 3; i++) {
                            if (field[0][i].equals(field[1][i])
                                    && field[0][i].equals(field[2][i])
                                    && !field[0][i].equals("")) {
                                return true;
                            }
                        }
                        if (field[0][0].equals(field[1][1])
                                && field[0][0].equals(field[2][2])
                                && !field[0][0].equals("")) {
                            return true;
                        }
                        if (field[0][2].equals(field[1][1])
                                && field[0][2].equals(field[2][0])
                                && !field[0][2].equals("")) {
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
    }
}