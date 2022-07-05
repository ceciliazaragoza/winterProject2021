
import java.util.Scanner;

public class Connect4 {
  private Piece[] game = new Piece[42];
  private String symbol1, symbol2;
  private boolean remove1;
  private boolean remove2;
  
  public Connect4 (String symbol1, String symbol2) {
    this.symbol1 = symbol1;
    this.symbol2 = symbol2;
    remove1 = true;
    remove2 = true;
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public void addPiece(Piece piece) {
    int column = piece.getColumn();
    int row = 1;
    boolean placed = false;
    for (int spaceNum = (column - 1) + 35; spaceNum >= column - 1 && !placed; spaceNum -= 7) {
      if (spaceNum == column - 1 && game[column - 1] != null) {
        System.out.println("Sorry! That column is full. Turn skipped.");
      }
      if (game[spaceNum] == null) {
        game[spaceNum] = piece;
        piece.setRow(row);
        placed = true;
      }
      row++;
    }
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public void removePiece(int column) {
    boolean removed = false;
    //start from top of column, if the bottom row in the column is empty, sends out error message
    for (int spaceNum = column - 1; spaceNum <= (column - 1) + 35 && !removed; spaceNum += 7) {
      if (spaceNum == (column - 1) + 35 && game[(column - 1) + 35] == null) {
        System.out.println("There aren't any pieces in that column! Remove skipped.");
      }
      if (game[spaceNum] != null) {
        game[spaceNum] = null;
        removed = true;
      }
    }
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public void printGame() { 
    System.out.println("-----------------------------");
    for (int idx = 0; idx < game.length; idx++) {
      if (game[idx] != null) {
        System.out.print("| " + game[idx] + " ");
      }
      else {
        System.out.print("|   ");
      }
      //if the next number is a multiple of 7, (meaning this is the 7th space in the row) will close off the row and move on
      if ((idx + 1) % 7 == 0 && idx != 0) {
        System.out.println("|");
      }
    }
    System.out.println("-----------------------------");
    System.out.println("  1   2   3   4   5   6   7");
    System.out.println("-----------------------------");
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public boolean winVert() {
    for (int idx = 0; idx <= 20; idx++) {
      if (game[idx] != null) {
        String symbol = game[idx].getSymbol();
        if (game[idx + 7] != null && game[idx + 14] != null && game[idx + 21] != null && symbol.equals(game[idx + 7].getSymbol()) && symbol.equals(game[idx + 14].getSymbol()) && symbol.equals(game[idx + 21].getSymbol())) {
            return true;
        }
      }
    }
    return false;
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public boolean winRtDiag() {
    for (int idx = 3; idx <= 20; idx++) {
      if (game[idx] != null && game[idx].getColumn() > 2 && game[idx].getRow() > 3) {
        String symbol = game[idx].getSymbol();
        if (game[idx + 6] != null && game[idx + 12] != null && game[idx + 18] != null && symbol.equals(game[idx + 6].getSymbol()) && symbol.equals(game[idx + 12].getSymbol()) && symbol.equals(game[idx + 18].getSymbol())) {
            return true;
        }
      }
    }
    return false;
  }

  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public boolean winLtDiag() {
    for (int idx = 0; idx <= 17; idx++) {
      if (game[idx] != null && game[idx].getColumn() < 5 && game[idx].getRow() > 3) {
        String symbol = game[idx].getSymbol();
        if (game[idx + 8] != null && game[idx + 16] != null && game[idx + 24] != null && symbol.equals(game[idx + 8].getSymbol()) && symbol.equals(game[idx + 16].getSymbol()) && symbol.equals(game[idx + 24].getSymbol())) {
            return true;
        }
      }
    }
    return false;
  }
  
  /*
    0    1   2   3   4   5   6
    7    8   9  10  11  12  13
    14  15  16  17  18  19  20
    21  22  23  24  25  26  27
    28  29  30  31  32  33  34
    35  36  37  38  39  40  41 
  */
  public boolean winHorizontal() {
    for (int idx = 0; idx <= 38; idx++) {
      if (game[idx] != null && game[idx].getColumn() < 5) {
        String symbol = game[idx].getSymbol();
        if (game[idx + 1] != null && game[idx + 2] != null && game[idx + 3] != null && symbol.equals(game[idx + 1].getSymbol()) && symbol.equals(game[idx + 2].getSymbol()) && symbol.equals(game[idx + 3].getSymbol())) {
          if (game[idx].getRow() == game[idx + 3].getRow()) {
            return true;
          } 
        }
      }
    }
    return false;  
  }

  public boolean tie() {
    for (int idx = 0; idx < game.length; idx++) {
      if (game[idx] == null) {
        return false;
      }
    }
    return true;
  }


  public void play(String symbol, int column) {
    Piece player = new Piece(symbol, column);
    addPiece(player);
  }

  public String getSymbol1() {
    return symbol1;
  }

  public String getSymbol2() {
    return symbol2;
  }

  public boolean getRemove1() {
    return remove1;
  }

  public boolean getRemove2() {
    return remove2;
  }

  public void setRemove1(boolean remove1) {
    this.remove1 = remove1;
  }

  public void setRemove2(boolean remove2) {
    this.remove2 = remove2;
  }
}