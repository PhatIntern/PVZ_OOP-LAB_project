package Entities.Zombie;

import Core.Game;
import Entities.Zombies;

public class PoleVaulting extends Zombies {

    private boolean jumped = false;

    public PoleVaulting(int row) {
        super(row, 175, 2);
    }

    @Override
    public void update() {

        int col = (int)(x / 100);

        // check plant ahead
        if (!jumped &&
                col >= 0 &&
                col < Game.getInstance().grid.cols &&
                row >= 0 &&
                row < Game.getInstance().grid.rows) {

            var cell = Game.getInstance().grid.cells[row][col];

            if (cell.plant != null) {

                //  jump over plant
                x -= 100;

                jumped = true;

                // walk slower after jumping
                speed = 1;
            }
        }

        x -= speed;
    }
}
