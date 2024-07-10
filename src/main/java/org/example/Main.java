package org.example;

public class main {
    private char[][] board; // Das Spielfeld
    private char currentPlayerSymbol; // Symbol des aktuellen Spielers ('X' oder 'O')

    public main() {
        board = new char[3][3]; // 3x3 Spielfeld initialisieren
        currentPlayerSymbol = 'X'; // Startspieler ist 'X'
        initializeBoard();
    }

    // Methode zum Initialisieren des Spielfelds
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Methode zum Setzen eines Zugs
    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayerSymbol;
            if (checkWin()) {
                System.out.println("Spieler " + currentPlayerSymbol + " gewinnt!");
                displayBoard();
            } else if (isBoardFull()) {
                System.out.println("Das Spiel endet unentschieden!");
                displayBoard();
            } else {
                switchPlayer();
            }
        } else {
            System.out.println("Ungültiger Zug. Bitte wählen Sie ein leeres Feld.");
        }
    }

    // Methode zur Überprüfung, ob ein Zug gültig ist
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    // Methode zum Wechseln des Spielers
    private void switchPlayer() {
        currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }

    // Methode zum Anzeigen des aktuellen Spielfelds
    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Methode zur Anzeige des aktuellen Spielzustands
    public void displayGameState() {
        System.out.println("Aktueller Spieler: " + currentPlayerSymbol);
        displayBoard();
    }

    // Methode zur Überprüfung, ob das Spielfeld voll ist
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Methode zur Überprüfung, ob ein Spieler gewonnen hat
    private boolean checkWin() {
        // Überprüfen der Zeilen
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayerSymbol && board[i][1] == currentPlayerSymbol && board[i][2] == currentPlayerSymbol) {
                return true;
            }
        }

        // Überprüfen der Spalten
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayerSymbol && board[1][i] == currentPlayerSymbol && board[2][i] == currentPlayerSymbol) {
                return true;
            }
        }

        // Überprüfen der Diagonalen
        if (board[0][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol && board[2][2] == currentPlayerSymbol) {
            return true;
        }

        if (board[0][2] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol && board[2][0] == currentPlayerSymbol) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        main game = new main();
        game.displayGameState(); // Anfangszustand des Spielfelds und des aktuellen Spielers anzeigen

        // Beispielzüge für Spieler X und O
        game.makeMove(0, 0); // Spieler X setzt auf Feld (0,0)
        game.displayGameState(); // Aktualisierter Zustand des Spiels anzeigen

        game.makeMove(1, 1); // Spieler O setzt auf Feld (1,1)
        game.displayGameState(); // Aktualisierter Zustand des Spiels anzeigen

        game.makeMove(0, 1); // Spieler X setzt auf Feld (0,1)
        game.displayGameState(); // Aktualisierter Zustand des Spiels anzeigen

        game.makeMove(1, 0); // Spieler O setzt auf Feld (1,0)
        game.displayGameState(); // Aktualisierter Zustand des Spiels anzeigen

        game.makeMove(0, 2); // Spieler X setzt auf Feld (0,2) und gewinnt
        game.displayGameState(); // Endzustand des Spiels anzeigen
    }
}
