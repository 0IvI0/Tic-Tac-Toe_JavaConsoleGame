package game_modules;

import java.util.Scanner;

public class GamePlay {

    private char player;
    private boolean gameOver;
    private int round;
    private Board gameBoard;
    private Scanner scanner;
    private char[] lastCoordinate;


    public GamePlay() {
        this.player = GameConstants.PLAYER_X;
        this.gameOver = false;
        this.round = 0;
        this.gameBoard = new Board();
        this.scanner = new Scanner(System.in);
        this.lastCoordinate = new char[2];
    }

    public void run() {
        System.out.println(GameConstants.GAME_TITLE + "\n");
        gameBoard.printBoard();
        while (!gameOver) {
            nextTurn();
        }
    }

    private void nextTurn() {
        this.lastCoordinate[0] = ' ';
        this.lastCoordinate[1] = ' ';
        this.round++;
        if (this.round > 1) {
            switchPlayer();
        }
        while(!checkIfInputIsValid()) {
            System.out.print(GameConstants.INPUT_COORDINATE + this.player + ": ");
            this.lastCoordinate = scanner.nextLine().toLowerCase().toCharArray();
        }
        this.gameBoard.getGameFields()[gameBoard.getFieldIndices().get(lastCoordinate[1])]
                                      [gameBoard.getFieldIndices().get(lastCoordinate[0])] = this.player;
        this.gameBoard.printBoard();
        if (this.round >= 5) {
            checkIfGameOver();
        }
    }

    private boolean checkIfInputIsValid() {
        if (this.lastCoordinate.length != 2 || this.lastCoordinate[0] == ' ') {
            return false;
        }
        if (!(this.lastCoordinate[0] == 'a' || this.lastCoordinate[0] == 'b' || this.lastCoordinate[0] == 'c')) {
            System.out.println("\n" + GameConstants.INPUT_ERROR + " Possible input for a row: a b c");
            return false;
        }
        if (!(this.lastCoordinate[1] == '1' || this.lastCoordinate[1] == '2' || this.lastCoordinate[1] == '3')) {
            System.out.println("\n" + GameConstants.INPUT_ERROR + " Possible input for a column: 1 2 3");
            return false;
        }
        if (this.gameBoard.getGameFields()[gameBoard.getFieldIndices().get(lastCoordinate[1])]
                                          [gameBoard.getFieldIndices().get(lastCoordinate[0])] != ' ') {
            System.out.println("\n" + GameConstants.INPUT_ERROR + " Field already taken.");
            return false;
        }
        return true;
    }

    private void checkIfGameOver() {
        if (this.round == 9) {
            System.out.println(GameConstants.GAME_DRAW);
            this.gameOver = true;
            return;
        }
        // Check rows for equality
        for (int row = 0; row < this.gameBoard.getGameFields().length; row++) {
                if (this.gameBoard.getGameFields()[row][0] == this.player
                        && this.gameBoard.getGameFields()[row][1] == this.player
                        && this.gameBoard.getGameFields()[row][2] == this.player) {
                    System.out.println(GameConstants.GAME_WINNER + this.player);
                    this.gameOver = true;
                    return;
                }
        }
        // Check columns for equality
        for (int col = 0; col < this.gameBoard.getGameFields()[0].length; col++) {
            if (this.gameBoard.getGameFields()[0][col] == this.player
                    && this.gameBoard.getGameFields()[1][col] == this.player
                    && this.gameBoard.getGameFields()[2][col] == this.player) {
                System.out.println(GameConstants.GAME_WINNER + this.player);
                this.gameOver = true;
                return;
            }
        }
        // Check diagonals for equality
        if (this.gameBoard.getGameFields()[0][0] == this.player
                && this.gameBoard.getGameFields()[1][1] == this.player
                && this.gameBoard.getGameFields()[2][2] == this.player) {
            System.out.println(GameConstants.GAME_WINNER + this.player);
            this.gameOver = true;
            return;
        }
        if (this.gameBoard.getGameFields()[0][2] == this.player
                && this.gameBoard.getGameFields()[1][1] == this.player
                && this.gameBoard.getGameFields()[2][0] == this.player) {
            System.out.println(GameConstants.GAME_WINNER + this.player);
            this.gameOver = true;
            return;
        }
        this.gameOver = false;
    }

    private void switchPlayer() {
        if (this.player == GameConstants.PLAYER_X) {
            this.player = GameConstants.PLAYER_O;
        } else {
            this.player = GameConstants.PLAYER_X;
        }
    }

}