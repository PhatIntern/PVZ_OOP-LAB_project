package Entities;

public abstract class Plants {
    public int row, col;

    public Plants(int r, int c) {
        row = r;
        col = c;
    }

    public abstract void update();
}
