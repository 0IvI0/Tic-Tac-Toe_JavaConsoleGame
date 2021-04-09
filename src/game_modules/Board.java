package game_modules;

import java.util.HashMap;
import java.util.Map;

class Board {

    private char[][] gameFields = {
                                    {' ', ' ', ' '},
                                    {' ', ' ', ' '},
                                    {' ', ' ', ' '}
                                  };

    private final Map<Character, Integer> fieldIndices = new HashMap<>(Map.of(
                                                                               'a', 0,
                                                                               'b', 1,
                                                                               'c', 2,
                                                                               '1', 2,
                                                                               '2', 1,
                                                                               '3', 0
                                                                              )
                                                                        );

    Board() {
    }

    void printBoard() {
        for (int row = 0; row < gameFields.length; row++) {
            for (int col = 0; col < gameFields[row].length; col++) {
                System.out.print(gameFields[row][col]);
                if (col < 2) {
                    System.out.print('|');
                }
            }
            if (row < 2) {
                System.out.println("\n" + "-+-+-");
            }
        }
        System.out.println("\n");
    }

    char[][] getGameFields() {
        return gameFields;
    }

    Map<Character, Integer> getFieldIndices() {
        return fieldIndices;
    }
}