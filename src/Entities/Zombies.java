package Entities;

public abstract class Zombies {
    public double x;
    public int row;
    public int hp;
    public double speed;
    public double originalSpeed;
    public int slowTimer = 0;

    public Zombies(int r, int hp, double speed) {
        row = r;
        this.hp = hp;
        this.speed = speed;
        x = 900;
        this.originalSpeed = speed;
    }

    public void update() {


            if (slowTimer > 0) {
                slowTimer--;
            } else {
                speed = originalSpeed;
            }

            x -= speed;

    };
}
