import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MineSweeper {
    int line;
    int column;
    int[][] board;
    int a;
    int b;
    int count;
    String[][] game;
    int mineCount;


    MineSweeper() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Satır sayısını girin: ");
        this.line = scn.nextInt();
        System.out.print("Sütun sayısını girin: ");
        this.column = scn.nextInt();
    }


    void create() {
        this.board = new int[line][column];
        this.game = new String[line][column];
        mineCount = Math.round((line * column) / 4);
        Random rnd = new Random();
        int mines;
        int limit = 1;
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                mines = rnd.nextInt(100);
                if (mines % 2 == 0 && limit <= mineCount) {
                    board[i][j] = -1;
                    limit++;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                game[i][j] = "-";
            }
        }

    }

    int chooseLine() {
        Scanner chs = new Scanner(System.in);
        System.out.print("Satır giriniz: ");
        int sira;
        do {
            sira = chs.nextInt();
            if (sira > this.line) {
                System.out.println("Geçerli bir satır girin:");
            }

        } while (sira > this.line);
        if (sira - 1 > 0) {
            a = sira - 1;
        } else {
            a = 0;
        }
        return a;

    }

    int chooseColumn() {
        Scanner chs = new Scanner(System.in);
        System.out.print("Sütun giriniz: ");
        int sütun;
        do {
            sütun = chs.nextInt();
            if (sütun > this.column) {
                System.out.println("Geçerli bir sütun girin:");
            }

        } while (sütun > this.column);
        if (sütun - 1 > 0) {
            b = sütun - 1;
        } else {
            b = 0;
        }
        return b;

    }

    boolean isValid(int a, int b) {

        return (a >= 0) && (a < this.line) &&
                (b >= 0) && (b < this.column);
    }

    int control(int a, int b, int[][] board) {
        this.count = 0;
        if (isValid(a - 1, b - 1) == true) {
            if (board[a - 1][b - 1] == -1) {
                count++;
            }
        }
        if (isValid(a - 1, b) == true) {
            if (board[a - 1][b] == -1) {
                count++;
            }
        }
        if (isValid(a - 1, b + 1) == true) {
            if (board[a - 1][b + 1] == -1) {
                count++;
            }
        }
        if (isValid(a, b - 1) == true) {
            if (board[a][b - 1] == -1) {
                count++;
            }
        }
        if (isValid(a, b + 1) == true) {
            if (board[a][b + 1] == -1) {
                count++;
            }
        }
        if (isValid(a + 1, b - 1) == true) {
            if (board[a + 1][b - 1] == -1) {
                count++;
            }
        }
        if (isValid(a + 1, b) == true) {
            if (board[a + 1][b] == -1) {
                count++;
            }
        }
        if (isValid(a + 1, b + 1) == true) {
            if (board[a + 1][b + 1] == -1) {
                count++;
            }
        }
        return count;
    }

    boolean isPlay(int a, int b) {
        this.a = a;
        this.b = b;


        if (board[a][b] == -1) {
            game[a][b] = "*";
            for (String[] x : game)
                System.out.println(Arrays.toString(x));

            System.out.println("\nGame Over!!!");
            return true;
        } else {
            game[a][b] = String.valueOf(count);
            for (String[] x : game)
                System.out.println(Arrays.toString(x));
        }
        return false;
    }


    void run() {


        create();

        for (String[] x : game)
            System.out.println(Arrays.toString(x));


        for (int i = 1; i <= ((line * column) - mineCount); i++) {
            chooseLine();
            chooseColumn();
            control(a, b, board);
            if (isPlay(a, b)) {
                break;
            }
            System.out.println("=====================");
            if (i == (line * column) - mineCount) {
                System.out.println("Tebrikler oyunu kazandınız!");
                break;
            }
        }

    }
}
