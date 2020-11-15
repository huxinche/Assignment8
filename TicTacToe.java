package TicTacToe;

import java.util.*;


/**
 * I put some codes I wrote in TicTacToe.TicTacToe.java file which can give some tips I think.
 * You can just delete codes or functions I wrote if you come up with some other methods to finish the requirements.
 */

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    static Random r = new Random();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        printGameBoard(gameBoard);


        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();
            // write your code here
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position invalid! Please input other position! ");
                System.out.println("Enter your placement (1-9): ");
                playerPos = scan.nextInt();
            }
            while (true) {
                if (placePiece(gameBoard, playerPos, "player")) {
                    break;
                } else {
                    System.out.println("Enter your placement (1-9): ");
                    playerPos = scan.nextInt();
                }
            }

            String checkResult = checkWinner();
            if (checkResult.length() > 0) {
                System.out.println(checkResult);
                printGameBoard(gameBoard);
                break;
            }
            r = new Random();
            int cpuPos = r.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = r.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);
            checkResult = checkWinner();
            if (checkResult.length() > 0) {
                System.out.println(checkResult);
                printGameBoard(gameBoard);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        // write your code here
        for (char[] chars : gameBoard) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static boolean placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        if (pos < 1 || pos > 9) {
            System.out.println("Position invalid! Please input other position! ");
            return false;
        }
        int row = (pos - 1) / 3 * 2;
        int col = (pos - 1) % 3 * 2;
        gameBoard[row][col] = symbol;

        // write your code here
        return true;
    }

    public static String checkWinner() {
        // write your code here
        List<Integer> firstRow = Arrays.asList(1, 2, 3);
        List<Integer> secondRow = Arrays.asList(4, 5, 6);
        List<Integer> thirdRow = Arrays.asList(7, 8, 9);
        List<Integer> firstCol = Arrays.asList(1, 4, 7);
        List<Integer> secondCol = Arrays.asList(2, 5, 8);
        List<Integer> thirdCol = Arrays.asList(3, 6, 9);
        List<Integer> crossLineXL = Arrays.asList(1, 5, 9);
        List<Integer> crossLineXR = Arrays.asList(7, 5, 3);
        List<List<Integer>> checkWiner = new ArrayList<>();
        checkWiner.add(firstRow);
        checkWiner.add(secondRow);
        checkWiner.add(thirdRow);
        checkWiner.add(firstCol);
        checkWiner.add(secondCol);
        checkWiner.add(thirdCol);
        checkWiner.add(crossLineXL);
        checkWiner.add(crossLineXR);
        for (List<Integer> posValue : checkWiner) {
            if (playerPositions.containsAll(posValue)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(posValue)) {
                return "CPU wins! Sorry:(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }
        return "";
    }

}










