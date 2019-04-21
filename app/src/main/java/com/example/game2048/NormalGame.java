package com.example.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Random;
import java.util.Vector;

public class NormalGame extends AppCompatActivity {

    Button[][] board;
    int n = 4;
    Random random;
    Vector<String> emptyCell;

    public int getRandom(int a, int b) {
        return random.nextInt(b) - random.nextInt(a) + 1;
    }

    public boolean isFull() {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                if (board[i][j].getText().toString().equals("")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);

        board[0][0] = (Button) findViewById(R.id.cell_1);
        board[0][1] = (Button) findViewById(R.id.cell_2);
        board[0][2] = (Button) findViewById(R.id.cell_3);
        board[0][3] = (Button) findViewById(R.id.cell_4);

        board[1][0] = (Button) findViewById(R.id.cell_5);
        board[1][1] = (Button) findViewById(R.id.cell_6);
        board[1][2] = (Button) findViewById(R.id.cell_7);
        board[1][3] = (Button) findViewById(R.id.cell_8);

        board[2][0] = (Button) findViewById(R.id.cell_9);
        board[2][1] = (Button) findViewById(R.id.cell_10);
        board[2][2] = (Button) findViewById(R.id.cell_11);
        board[2][3] = (Button) findViewById(R.id.cell_12);

        board[3][0] = (Button) findViewById(R.id.cell_13);
        board[3][1] = (Button) findViewById(R.id.cell_14);
        board[3][2] = (Button) findViewById(R.id.cell_15);
        board[3][3] = (Button) findViewById(R.id.cell_16);



    }
}
