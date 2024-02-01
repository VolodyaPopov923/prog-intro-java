package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final int m;
    private final int n;
    private final int k;
    private final Cell[][] cells;
    private Cell turn;
    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        
        int empty = 0;
        for (int u = 0; u < m; u++) {
            for (int v = 0; v < n; v++) {
                int inRow = 0;
                int inColumn = 0;
                int inMainDiagonal = 0;
                int inSecondaryDiagonal = 0;
                for (int w = 0; w < k; w++) {
                    if (v + w < n && cells[u][v + w] == turn) inRow++;
                    
                    if (u + w < m && cells[u + w][v] == turn) inColumn++;
                    
                    if (u + w < m && v + w < n && cells[u + w][v + w] == turn) inMainDiagonal++;
                    
                    if (u + w < m && v - w > 0 && cells[u + w][v - w] == turn) inSecondaryDiagonal++;
                    
                }
                if (inRow == k || inColumn == k || inMainDiagonal == k || inSecondaryDiagonal == k) return Result.WIN;
                
                if (cells[u][v] == Cell.E) empty++;
                
            }
        }
        if (empty == 0) return Result.DRAW;
        

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int cntm = Integer.toString(m).length();
        final StringBuilder sb = new StringBuilder();
		sb.append(" ".repeat(Math.max(0, Integer.toString(m).length() + 1)));
        for (int i = 0; i < n; i++) {sb.append(i); sb.append(" ");}
        for (int r = 0; r < m; r++) {
            sb.append("\n");
			sb.append(" ".repeat(Math.max(0, cntm - Integer.toString(r).length())));
			;
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(" ");
                sb.append(SYMBOLS.get(cells[r][c]));
				sb.append(" ".repeat(Math.max(0, Integer.toString(c).length() - 1)));
            }
        }
        return sb.toString();
    }
}
