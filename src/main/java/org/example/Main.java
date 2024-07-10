package org.example;

import java.util.Scanner;

public class main {
    private char[][] board;
    private char currentPlayerSymbol;

    public main() {
        board = new char[3][3];
        currentPlayerSymbol = 'X';
        initializeBoard();
    }

    // Initialisiert das Spielfeld mit leeren Feldern
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Zeigt das aktuelle Spielfeld an
    public void displayBoard() {
        System.out.println("Aktuelles Spielfeld:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Überprüft, ob der Zug gültig ist
    private boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col] == '-';
        }
        return false;
    }

    // Führt den Zug aus und überprüft das Spielende
    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayerSymbol;
            if (checkWin()) {
                System.out.println("Spieler " + currentPlayerSymbol + " gewinnt!");
                displayBoard();
                promptNewGame();
            } else if (isBoardFull()) {
                System.out.println("Das Spiel endet unentschieden!");
                displayBoard();
                promptNewGame();
            } else {
                switchPlayer();
            }
        } else {
            System.out.println("Ungültiger Zug. Bitte wählen Sie ein leeres Feld.");
        }
    }

    // Wechselt den Spieler
    private void switchPlayer() {
        currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }

    // Überprüft, ob das Spielfeld voll ist
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

    // Überprüft, ob ein Spieler gewonnen hat
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

    // Fragt den Spieler, ob ein neues Spiel gestartet werden soll
    private void promptNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Möchten Sie ein neues Spiel starten? (ja/nein)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("ja")) {
            startNewGame();
        } else {
            System.out.println("Danke fürs Spielen!");
        }
    }

    // Startet ein neues Spiel
    public void startNewGame() {
        initializeBoard();
        currentPlayerSymbol = 'X';
        System.out.println("Neues Spiel gestartet!");
        displayBoard();
    }

    public static void main(String[] args) {
        main game = new main();
        Scanner scanner = new Scanner(System.in);
        game.displayBoard();
        while (true) {
            System.out.println("Spieler " + game.currentPlayerSymbol + ", geben Sie Ihre Bewegung ein (Reihe und Spalte): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            game.makeMove(row, col);
        }
    }
}

