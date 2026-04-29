package Map;
import Entities.Plant.*;

import java.awt.*;


public class Grid {
    public int rows, cols;
    public Cell[][] cells;

    public Grid(int r, int c) {
        rows = r;
        cols = c;

        cells = new Cell[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void update() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].update();
            }
        }
    }

    public void draw(Graphics g) {
        int OffsetY = 100;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.drawRect(j * 100, i * 100 + OffsetY, 100, 100);

            }
        }
        // vẽ plant

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].plant != null) {

                    if (cells[i][j].plant instanceof SunFlower) {
                        g.setColor(Color.YELLOW);
                    }
                    else if (cells[i][j].plant instanceof PeaShooter) {
                        g.setColor(Color.GREEN); //
                    }
                    else if (cells[i][j].plant instanceof WallNut){
                        g.setColor(Color.ORANGE);
                    }
                    else if (cells[i][j].plant instanceof CherryBomb) g.setColor(Color.RED);
                    else if (cells[i][j].plant instanceof PotatoMine) g.setColor(Color.DARK_GRAY);
                    else if (cells[i][j].plant instanceof SnowPea) g.setColor(Color.CYAN);
                    else if (cells[i][j].plant instanceof Repeater) g.setColor(Color.GREEN);
                    else if (cells[i][j].plant instanceof Chomper) g.setColor(Color.MAGENTA);

                    g.fillRect(j * 100 + 20, i * 100 + OffsetY + 20, 60, 60);
                }
            }
        }
    }
}
