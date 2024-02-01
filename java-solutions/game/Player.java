package game;

import java.io.IOException;

public interface Player {
    Move move(Position position, Cell cell) throws IndexOutOfBoundsException , IllegalArgumentException, NullPointerException, IOException;
}
