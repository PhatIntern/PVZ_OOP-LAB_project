package Entities.Plant;
import Entities.Plants;
import Core.Game;


public class PotatoMine extends Plants {

    private int timer = 100; //
    private boolean armed = false;

    public PotatoMine(int r, int c) {
        super(r, c);
    }

    @Override
    public void update() {
        if (!armed) {
            timer--;
            if (timer <= 0) {
                armed = true;
            }
        } else {
            explodeIfZombie();
        }
    }

    private void explodeIfZombie() {
        var zombies = Game.getInstance().Zombies;

        for (int i = 0; i < zombies.size(); i++) {
            var z = zombies.get(i);

            if (z.row == row && Math.abs(z.x - col * 100) < 40) {
                z.hp = 0;
                // self destroy
                Game.getInstance().grid.cells[row][col].plant = null;
                break;
            }
        }
    }
}
