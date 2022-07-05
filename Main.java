
import java.util.Scanner;

class Main {
  public static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    System.out.println("\nWelcome to Connect4.\nTo win, you must get 4 of your pieces in a row horizontally, vertically, or diagonally.\nIn this version of Connect4, each player can use 1 turn to remove the highest piece from a column.\n\tTo remove a piece, choose 0 as your column number.");
    System.out.println("\nPlayer 1, what would you like your symbol to be?");
    String symbol1 = scan.nextLine();
    System.out.println("Player 2, what would you like your symbol to be?");
    String symbol2 = scan.nextLine();
    while (symbol2.equals(symbol1)) {
      System.out.println("The 2 symbols used to play must be different. Player 2, choose another symbol.");
      symbol2 = scan.nextLine();
    }
    Connect4 connect4 = new Connect4(symbol1, symbol2);
    
    while (true) {
      connect4.printGame();
      System.out.println("Player 1 choose a column.");
      int column = scan.nextInt();
      //removed1 acts as a temporary marker to indicate that a remove has happened that turn, so then a new piece can't be added. The getRemove1() acts as a permanent indicator of whether a player has used up their one opportunity to remove a piece.
      boolean removed1 = false;
      if (column == 0 && connect4.getRemove1()) {
        System.out.println("Which column do you want to remove the top piece from?");
        int removeColumn1 = scan.nextInt();
        if (removeColumn1 > 0 && removeColumn1 < 8) {
          connect4.removePiece(removeColumn1);
        }
        else {
          System.out.println("Not a valid column number! Try again.");
          removeColumn1 = scan.nextInt();
          if (removeColumn1 > 0 && removeColumn1 < 8) {
            connect4.removePiece(removeColumn1);
          }
          else {
            System.out.println("Not a valid column number! Remove skipped.");
          }
        }
        connect4.setRemove1(false);
        removed1 = true;
      }
      if (!removed1) {
        if (column > 0 && column < 8) {
          connect4.play(connect4.getSymbol1(), column);
        }
        else {
          System.out.println("Not a valid column number! Try again.");
          column = scan.nextInt();
          if (column > 0 && column < 8) {
            connect4.play(connect4.getSymbol1(), column);
          }
          else {
            System.out.println("Not a valid column number! Turn skipped.");
          }
        }
      }
      if (connect4.winVert()) {
        connect4.printGame();
        System.out.println("Player 1 wins! (vertically)");
        break;
      }
      if (connect4.winHorizontal()) {
        connect4.printGame();
        System.out.println("Player 1 wins! (horizontally)");
        break;
      }
      if (connect4.winRtDiag()) {
        connect4.printGame();
        System.out.println("Player 1 wins! (diagonally to the right)");
        break;
      }
      if(connect4.winLtDiag()) {
        connect4.printGame();
        System.out.println("Player 1 wins! (diagonally to the left)");
        break;
      }
      if (connect4.tie()) {
        connect4.printGame();
        System.out.println("Tie! No one wins.");
        break;
      }
      
      connect4.printGame();
      System.out.println("Player 2 choose a column.");
      column = scan.nextInt();
      boolean removed2 = false;
      if (column == 0 && connect4.getRemove2()) {
        System.out.println("Which column do you want to remove the top piece from?");
        int removeColumn2 = scan.nextInt();
        if (removeColumn2 > 0 && removeColumn2 < 8) {
          connect4.removePiece(removeColumn2);
        }
        else {
          System.out.println("Not a valid column number! Try again.");
          removeColumn2 = scan.nextInt();
          if (removeColumn2 > 0 && removeColumn2 < 8) {
            connect4.removePiece(removeColumn2);
          }
          else {
            System.out.println("Not a valid column number! Remove skipped.");
          }
        }
        connect4.setRemove2(false);
        removed2 = true;
      }
      if (!removed2) {
        if (column > 0 && column < 8) {
          connect4.play(connect4.getSymbol2(), column);
        }
        else {
          System.out.println("Not a valid column number! Try again.");
          column = scan.nextInt();
          if (column > 0 && column < 8) {
            connect4.play(connect4.getSymbol2(), column);
          }
          else {
            System.out.println("Not a valid column number! Turn skipped.");
          }
        }
      }
      if (connect4.winVert()) {
        connect4.printGame();
        System.out.println("Player 2 wins! (vertically)");
        break;
      }
      if (connect4.winHorizontal()) {
        connect4.printGame();
        System.out.println("Player 2 wins! (horizontally)");
        break;
      }
      if (connect4.winRtDiag()) {
        connect4.printGame();
        System.out.println("Player 2 wins! (diagonally to the right)");
        break;
      }
      if(connect4.winLtDiag()) {
        connect4.printGame();
        System.out.println("Player 2 wins! (diagonally to the left)");
        break;
      }
      if (connect4.tie()) {
        connect4.printGame();
        System.out.println("Tie! No one wins.");
        break;
      }
    }
  }
}