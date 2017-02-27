package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Владислав on 26.02.2017.
 */
public class AnotherClass {
    int[] canvas = {0, 0, 0,
            0, 0, 0,
            0, 0, 0}; // combination for win 012 345 678 036 147 258 048 246

    /**
     * This method write the console and inspect input data
     *
     * @return number for position of game fields
     */
    public int getNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                int number = Integer.parseInt(reader.readLine());
                if (number >= 0 && number < canvas.length && canvas[number] == 0) {
                    return number;
                }
                System.out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter number");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for drawing field of game
     */
    public void drawCanvas() {
        System.out.println("     |     |     ");
        for (int i = 0; i < canvas.length; i++) {
            if (i != 0) {
                if (i % 3 == 0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                } else
                    System.out.print("|");
            }

            if (canvas[i] == 0) System.out.print("  " + i + "  ");
            if (canvas[i] == 1) System.out.print("  X  ");
            if (canvas[i] == 2) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");

    }

    /**
     * Method for checking array of nums
     *
     * @return true if place is empty
     */
    public boolean isDraw() {
        for (int number : canvas) if (number == 0) return false;
        return true;
    }

    public boolean isGameOver(int n) {
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n - n % 3; //номер строки - проверяем только её
        if (canvas[row] == canvas[row + 1] &&
                canvas[row] == canvas[row + 2]) return true;
        //поиск совпадений по вертикали
        int column = n % 3; //номер столбца - проверяем только его
        if (canvas[column] == canvas[column + 3])
            if (canvas[column] == canvas[column + 6]) return true;
        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n % 2 != 0) return false;
        //проверяем принадлежит ли к левой диагонали значение
        if (n % 4 == 0) {
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] &&
                    canvas[0] == canvas[8]) return true;
            if (n != 4) return false;
        }
        return canvas[2] == canvas[4] &&
                canvas[2] == canvas[6];
    }

    /**
     * Main method in this class/ Created for calling in main class;
     */

    public void cofnormAllParts() {
        Random random = new Random();
        boolean ifWinX;
        boolean ifWinY;

        do {

            drawCanvas();
            System.out.println("mark " + "X");
            int n = getNumber();
            int otherN = random.nextInt(8);
            canvas[n] = 1;

            if (n != otherN) canvas[otherN] = 2;
            else {
                otherN = random.nextInt(8);
                canvas[otherN] = 2;
            }

            ifWinX = !isGameOver(n);
            ifWinY = !isGameOver(otherN);
            if (isDraw()) {
                System.out.println("End");
                return;
            }
        } while (ifWinX & ifWinY);
        drawCanvas();
        System.out.println();
        String result = !ifWinX ? "X" : "O";
        System.out.println("The winner is " + result + "!");


    }
}


