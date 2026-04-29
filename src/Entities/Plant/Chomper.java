package Entities.Plant;
import Entities.Plants;
import Core.Game;

public class Chomper extends Plants {

    private int cooldown = 0;

    public Chomper(int r, int c) {
        super(r, c);
    }

    @Override
    public void update() {
        cooldown--;

        if (cooldown <= 0) {
            eatZombie();
        }
    }

    private void eatZombie() {
        var zombies = Game.getInstance().Zombies;

        for (int i = 0; i < zombies.size(); i++) {
            var z = zombies.get(i);

            if (z.row == row && Math.abs(z.x - col * 100) < 60) {
                zombies.remove(i); // eat
                cooldown = 150; // cooldown for next zombie
                break;
            }
        }
    }
}
