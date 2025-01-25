package HW03;

import java.util.Scanner;

public class Battleship {
    final static int boardRows = 5;
    final static int boardCols = 5;
    final static int numShips = 5;

    public static void main(String[] args){
        System.out.println("Welcome to Battleship!\n");
        char[][] player1Ships = newBoard();
        char[][] player2Ships = newBoard();
        char[][] player1Hits = newBoard();
        char[][] player2Hits = newBoard();
        Scanner input = new Scanner(System.in);
        placeShips(player1Ships, 1, input);
        printBattleShip(player1Ships);
        for (int i=0; i < 100; i++){
            System.out.println();
        }
        placeShips(player2Ships, 2, input);
        printBattleShip(player2Ships);
        for (int i=0; i < 99; i++){
            System.out.println();
        }
        while (true){
            take_turn(player2Ships, player1Hits, 1, input);
            if (check_winner(player1Hits)){
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
                break;
            }
            take_turn(player1Ships, player2Hits, 2, input);
            if (check_winner(player2Hits)){
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
                break;
            }
        }

        System.out.println("Final boards:\n");
        for (int row=0; row < boardRows; row++) {
            for (int col = 0; col < boardCols; col++) {
                if (player2Ships[row][col] == '@' && player1Hits[row][col] == '-'){
                    player1Hits[row][col] = '@';
                }
                if (player1Ships[row][col] == '@' && player2Hits[row][col] == '-'){
                    player2Hits[row][col] = '@';
                }
            }
        }
        /*
        Final boards:

  0 1 2 3 4
0 @ @ @ @ @
1 - - - - O
2 - - - - O
3 - - - - O
4 - - - - O

  0 1 2 3 4
0 X - - - -
1 X - - - -
2 X - - - -
3 X - - - -
4 X - - - -
=====
         */
        printBattleShip(player2Hits);
        System.out.println();
        printBattleShip(player1Hits);
    }
    private static boolean check_winner(char[][] board){
        int shipsSunk = 0;
        for (int row=0; row < board.length; row++){
            for (int col=0; col < board[row].length; col++){
                if ('X' == board[row][col]){
                    shipsSunk += 1;
                    if (shipsSunk == numShips){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static void take_turn(char[][] player, char[][] targets, int playerNum, Scanner input){
        int[] coords;
        while (true) {
            System.out.printf("\nPlayer %d, enter hit row/column:\n", playerNum);
            coords = processInput(input); // Ensure valid input coordinates
            if (coords[0] != -1) {
                if ('-' == targets[coords[0]][coords[1]]) { // We have not targeted this spot before
                    if ('@' == player[coords[0]][coords[1]]){
                        targets[coords[0]][coords[1]] = 'X'; // No ship there
                        System.out.printf("PLAYER %d HIT PLAYER %d's SHIP!\n", playerNum, playerNum % 2 + 1);
                    } else {
                        targets[coords[0]][coords[1]] = 'O'; // Yes ship there
                        System.out.printf("PLAYER %d MISSED!\n", playerNum);
                    }
                    break;
                } else { // We have already fired there
                    System.out.print("You already fired on this spot. Choose different coordinates.");
                }
            } else {
                System.out.print("Invalid coordinates. Choose different coordinates.");
            }
        }
        printBattleShip(targets);
    }

    private static int[] processInput(Scanner input) {
        // Helper method to make sure coordinates are integer, and on the board
        int[] coords = new int[2];
        int row;
        int col;
        if (input.hasNextInt()) { // Check input exists
            row = input.nextInt();
            if (input.hasNextInt()) { // Check input exists
                col = input.nextInt();
                if (row >= 0 && col >= 0 && row < boardRows && col < boardCols) {
                    coords[0] = row;
                    coords[1] = col;
                    return coords;
                }
            }
        }
        coords[0] = -1; // return an error code
        return coords;
    }

//    private static void printBoard(String[][] board){
//        String[] r;
//        System.out.print("  ");
//        for(int h=0; h< board[0].length;h++){
//            System.out.printf("%d ", h);
//        }
//        System.out.println();
//        for (int row=0; row < board.length; row++){
//            r = board[row];
//            System.out.printf("%d ", row);
//            for (int col=0; col < r.length; col++){
//                System.out.printf("%s ", board[row][col]);
//            }
//            System.out.println();
//        }
//    }

    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    private static void placeShips(char[][] board, int playerNum, Scanner input){
        int shipsPlaced = 0;
        System.out.printf("PLAYER %d, ENTER YOUR SHIPS' COORDINATES.\n", playerNum);
        int[] coords;
        do {
            System.out.printf("Enter ship %d location:\n", shipsPlaced + 1);
            coords = processInput(input);
            if (coords[0] == -1){
                System.out.println("Invalid coordinates. Choose different coordinates."); // Invalid index
            } else if ('-' == board[coords[0]][coords[1]]){
                shipsPlaced += 1;
                board[coords[0]][coords[1]] = '@';
            } else {
                System.out.println("You already have a ship there. Choose different coordinates.");
            }
        } while (shipsPlaced < 5);
    }

    private static char[][] newBoard(){
        char[][] board = new char[boardRows][boardCols];
        for (int row=0; row < board.length; row++){
            for (int col=0; col < board[row].length; col++){
                board[row][col] = '-'; // Initialize board as empty space
            }
        }
        /*
        A '–' character must represent an empty space.
        An '@' character must represent a ship that is not hit. When the game begins, all ships will start fresh with no hits.
        An 'X' character will represent a space with a ship that has been hit.
        An 'O' character will represent a space that was fired upon, but since there is not ship at that location, the shot was a miss.
        Each player's board must have five ships of length one. Five of the 25 grid spaces will start with ships on them.
         */
        return board;
    }
}


