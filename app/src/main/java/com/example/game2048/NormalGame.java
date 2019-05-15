package com.example.game2048;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Random;

public class NormalGame extends AppCompatActivity {

    TextView[][] board;
    int[][] arr;
    int n = 4;
    Random random;
    TextView txtScore;
    TextView x;
    long score;

    private float x1, x2, y1, y2;
    static final int MIN_DISTANCE = 150;

    HashMap<String, Integer> color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);

        board = new TextView[n][n];
        random = new Random();
        txtScore = findViewById(R.id.score);
        txtScore.setText("0");
        score = 0;

        color = new HashMap<>();
        color.put("0", ContextCompat.getColor(getApplicationContext(), R.color._0point));
        color.put("2", ContextCompat.getColor(getApplicationContext(), R.color._2point));
        color.put("4", ContextCompat.getColor(getApplicationContext(), R.color._4point));
        color.put("8", ContextCompat.getColor(getApplicationContext(), R.color._8point));
        color.put("16", ContextCompat.getColor(getApplicationContext(), R.color._16point));
        color.put("32", ContextCompat.getColor(getApplicationContext(), R.color._32point));
        color.put("64", ContextCompat.getColor(getApplicationContext(), R.color._64point));
        color.put("128", ContextCompat.getColor(getApplicationContext(), R.color._128point));
        color.put("256", ContextCompat.getColor(getApplicationContext(), R.color._256point));
        color.put("512", ContextCompat.getColor(getApplicationContext(), R.color._512point));
        color.put("1024", ContextCompat.getColor(getApplicationContext(), R.color._1024point));
        color.put("2048", ContextCompat.getColor(getApplicationContext(), R.color._2048point));
        color.put("4096", ContextCompat.getColor(getApplicationContext(), R.color._4096point));


        board[0][0] = (TextView) findViewById(R.id.cell_1);
        board[0][1] = (TextView) findViewById(R.id.cell_2);
        board[0][2] = (TextView) findViewById(R.id.cell_3);
        board[0][3] = (TextView) findViewById(R.id.cell_4);

        board[1][0] = (TextView) findViewById(R.id.cell_5);
        board[1][1] = (TextView) findViewById(R.id.cell_6);
        board[1][2] = (TextView) findViewById(R.id.cell_7);
        board[1][3] = (TextView) findViewById(R.id.cell_8);

        board[2][0] = (TextView) findViewById(R.id.cell_9);
        board[2][1] = (TextView) findViewById(R.id.cell_10);
        board[2][2] = (TextView) findViewById(R.id.cell_11);
        board[2][3] = (TextView) findViewById(R.id.cell_12);

        board[3][0] = (TextView) findViewById(R.id.cell_13);
        board[3][1] = (TextView) findViewById(R.id.cell_14);
        board[3][2] = (TextView) findViewById(R.id.cell_15);
        board[3][3] = (TextView) findViewById(R.id.cell_16);

        arr = new int[n][n];

        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                board[i][j].setText("");
                board[i][j].setBackgroundColor(color.get("0"));
                arr[i][j] = 0;
            }
        }

        for(int i = 0;i < 2;) {
            int r = this.getRandom(0, n - 1);
            int c = this.getRandom(0, n - 1);

            if (board[r][c].getText().equals("")) {
                i++;
                board[r][c].setText("2");
                board[r][c].setBackgroundColor(color.get("2"));
                arr[r][c] = 2;
            }
        }
    }


    //Decect swipe
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                y1 = event.getY();
                break;
            }
            case MotionEvent.ACTION_UP:  {
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (x1 > x2) {
                        moveLeft();
                        if (isLose()) {
                            System.out.println("Thua");
                            Toast.makeText(this, "You losed!", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("Chưa thua");
                        }
                    } else if (x1 < x2) {
                        moveRight();
                        if (isLose()) {
                            System.out.println("Thua");
                            Toast.makeText(this, "You losed!", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("Chưa thua");
                        }
                    }
                } else  if (Math.abs(deltaY) > MIN_DISTANCE){
                    if (y1 > y2) {
                        moveUp();
                        if (isLose()) {
                            System.out.println("Thua");
                            Toast.makeText(this, "You losed!", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("Chưa thua");
                        }
                    } else if (y1 < y2) {
                        moveDown();
                        if (isLose()) {
                            System.out.println("Thua");
                            Toast.makeText(this, "You losed!", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("Chưa thua");
                        }
                    }
                }
                break;
            }

        }
        return super.onTouchEvent(event);
    }

    private void moveCellsUp() {
        for(int j = 0;j < n;j++) {
            stack(j);
            merge(j);
            stack(j);
        }
    }

    public void moveRight() {
        rotate270();
        moveCellsUp();
        rotate90();
        refresh();
        generate();
        //View for debug
        //view();
    }

    public void moveLeft() {
        rotate90();
        moveCellsUp();
        rotate270();
        refresh();
        generate();
        //View for debug
        //view();
    }

    public void moveDown() {
        rotate180();
        moveCellsUp();
        rotate180();
        refresh();
        generate();
        //View for debug
        //view();
    }


    public void moveUp() {
        //Move Cell Ups
        moveCellsUp();
        refresh();
        generate();
        //View for debug
        //view();
    }

    private void view() {
        String s = "";
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                if (arr[i][j] != 0) {
                    s += arr[i][j];
                } else {
                    s += '0';
                }
            }
            s += '|';
        }
        System.out.println(s);
    }

    public void refresh() {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                board[i][j].setBackgroundColor(color.get(String.valueOf(arr[i][j])));
                if (arr[i][j] == 0) {
                    board[i][j].setText("");
                } else {
                    board[i][j].setText(arr[i][j] + "");

                }
            }
        }
    }

    public int getRandom(int min, int max) {
        return random.nextInt((max - min + 1)) + min;
    }

    boolean isFull() {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                if (board[i][j].getText().toString().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLose() {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                if (board[i][j].getText().toString().equals("")) {
                    return false;
                } else {
                    //Check around the cell
                    if (i - 1 >= 0) {
                        if (arr[i-1][j] == arr[i][j]) {
                            return false;
                        }
                    }
                    if (i + 1 < n) {
                        if (arr[i+1][j] == arr[i][j]) {
                            return false;
                        }
                    }
                    if (j - 1 >= 0) {
                        if (arr[i][j-1] == arr[i][j]) {
                            return false;
                        }
                    }
                    if (j + 1 < n) {
                        if (arr[i][j+1] == arr[i][j]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void generate() {
        while(!isFull()) {
            int r = this.getRandom(0, n - 1);
            int c = this.getRandom(0, n - 1);
            if (board[r][c].getText().equals("")) {
                board[r][c].setText("2");
                board[r][c].setBackgroundColor(color.get("2"));
                arr[r][c] = 2;
                return ;
            }
        }
    }

    public int findNotZero(int i, int j) {
        for(int k = i - 1;k >= 0;k--) {
            if (arr[k][j] > 0) {
                return k;
            }
        }
        return -1;
    }

    public void stack(int j) {
        for(int i = 1;i < n;i++) {
            if (arr[i][j] > 0) {
                int k = this.findNotZero(i, j);
                if (k < (i - 1)) {
                    arr[k+1][j] = arr[i][j];
                    arr[i][j] = 0;
                }
            }
        }
    }

    public void merge(int j) {
        for(int i = 0;i < n - 1;i++) {
            if (arr[i][j] == arr[i+1][j]) {
                arr[i][j] *= 2;
                score += arr[i][j];
                arr[i+1][j] = 0;
            }
        }
        txtScore.setText(String.valueOf(score));
    }

    public void rotate90() {
        int[][] tempArr = new int[n][n];
        for(int i = 0;i < n;++i) {
            for(int j = 0;j < n;++j) {
                tempArr[i][j] = arr[n - j - 1][i];
            }
        }
        arr = tempArr;
    }

    public void rotate180() {
        int[][] tempArr = new int[n][n];
        for(int i = 0;i < n;++i) {
            for(int j = 0;j < n;++j) {
                tempArr[i][j] = arr[n - i - 1][n - j - 1];
            }
        }
        arr = tempArr;
    }

    public void rotate270() {
        int[][] tempArr = new int[n][n];
        for(int i = 0;i < n;++i) {
            for(int j = 0;j < n;++j) {
                tempArr[i][j] = arr[j][n - i - 1];
            }
        }
        arr = tempArr;
    }

}
