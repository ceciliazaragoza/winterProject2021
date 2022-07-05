public class Piece {
  private int row, column;
  private String symbol;
  
  public Piece(String symbol, int column) {
    this.symbol = symbol;
    this.column = column;
    row = 0;
  }

  public String toString() {
    return symbol;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public String getSymbol() {
    return symbol;
  }
}