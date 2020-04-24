package queens;

public interface QueensChessBoardState {
    public int boardSize();

    public boolean squareOccupied(int row, int column);

    public boolean existsSharedRow();

    public boolean existsSharedColumn();

    public boolean existsSharedDiagonal();
    //NOTE: could coalesce previous three methods into one: noAttacks()
    //leave as three separate methods for illustrative purposes
}
