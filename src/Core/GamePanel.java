package Core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Entities.Plant.*;
import Entities.Zombie.*;
import Entities.Sun;
import Core.Game;
import java.awt.Color;

public class GamePanel extends JPanel {
    private int hoverRow = -1;
    private int hoverCol = -1;
    private int OffsetY = 100;
    private boolean shovelMode = false;
    Game game = Game.getInstance();
    private PlantType selectedPlant = null;

    public GamePanel() {
        new GameLoop(() -> {
            game.update();
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int col = x / 100;
                int row = (y - 100) / 100;

                if (row >= 0 && row < game.grid.rows && col < game.grid.cols) {
                    hoverRow = row;
                    hoverCol = col;
                } else {
                    hoverRow = -1;
                    hoverCol = -1;
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //UI selected plant Table
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 900, 100);

// Sunflower card
        g.setColor(Color.YELLOW);
        g.fillRect(50, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("50", 60, 95);

// Peashooter card
        g.setColor(Color.GREEN);
        g.fillRect(150, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("100", 160, 95);

        // WallNut
        g.setColor(Color.ORANGE);
        g.fillRect(750, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("50", 760, 95);

// Cherry
        g.setColor(Color.RED);
        g.fillRect(650, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("150", 660, 95);
        // Potato Mine
        g.setColor(Color.DARK_GRAY);
        g.fillRect(250, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("25", 260, 95);

// Snow Pea
        g.setColor(Color.CYAN);
        g.fillRect(350, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("175", 360, 95);

// Repeater
        g.setColor(Color.GREEN);
        g.fillRect(450, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("200", 460, 95);

// Chomper
        g.setColor(Color.MAGENTA);
        g.fillRect(550, 20, 60, 60);
        g.setColor(Color.BLACK);
        g.drawString("150", 560, 95);
        // highlight selected plant
        g.setColor(Color.BLACK);

        if (selectedPlant == PlantType.SUNFLOWER) {
            g.drawRect(50, 20, 60, 60);
        }

        if (selectedPlant == PlantType.PEASHOOTER) {
            g.drawRect(150, 20, 60, 60);
        }
        if (selectedPlant == PlantType.POTATOMINE)
            g.drawRect(250, 20, 60, 60);

        if (selectedPlant == PlantType.SNOWPEA)
            g.drawRect(350, 20, 60, 60);

        if (selectedPlant == PlantType.REPEATER)
            g.drawRect(450, 20, 60, 60);

        if (selectedPlant == PlantType.CHOMPER)
            g.drawRect(550, 20, 60, 60);

        if (selectedPlant == PlantType.CHERRYBOMB)
            g.drawRect(650, 20, 60, 60);

        if (selectedPlant == PlantType.WALL_NUT)
            g.drawRect(750, 20, 60, 60);

        if(shovelMode) {
            g.drawRect(850, 20, 40, 40);
        }

        //shovel
        g.setColor(Color.GRAY);
        g.fillRect(850, 20, 40, 40);

        g.setColor(Color.BLACK);
        g.drawString("Shovel", 865, 45);
        // Vẽ grid
        game.grid.draw(g);
        //Đạn
        g.setColor(Color.BLACK);
        for (var b : Game.getInstance().bullets) {
            g.fillOval((int)b.x, b.row * 100 + OffsetY +40, 10, 10);
        }
        //Hover
        if (hoverRow != -1) {

            Graphics2D g2 = (Graphics2D) g;

            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            0.5f
                    )
            );

            //  preview plant
            if (selectedPlant != null &&
                    game.grid.cells[hoverRow][hoverCol].plant == null) {

                if (selectedPlant == PlantType.SUNFLOWER) {
                    g2.setColor(Color.YELLOW);
                }
                else if (selectedPlant == PlantType.PEASHOOTER) {
                    g2.setColor(Color.BLUE);
                }
                else if (selectedPlant == PlantType.WALL_NUT) {
                    g2.setColor(Color.ORANGE);
                }
                else if (selectedPlant == PlantType.CHERRYBOMB) {
                    g2.setColor(Color.RED);
                }
                else if (selectedPlant == PlantType.POTATOMINE) {
                    g2.setColor(Color.DARK_GRAY);
                }
                else if (selectedPlant == PlantType.SNOWPEA) {
                    g2.setColor(Color.CYAN);
                }
                else if (selectedPlant == PlantType.REPEATER) {
                    g2.setColor(Color.GREEN);
                }
                else if (selectedPlant == PlantType.CHOMPER) {
                    g2.setColor(Color.MAGENTA);
                }

                g2.fillRect(
                        hoverCol * 100 + 20,
                        hoverRow * 100 + 120,
                        60,
                        60
                );
            }

            // shovel
            if (shovelMode &&
                    game.grid.cells[hoverRow][hoverCol].plant != null) {

                g2.setColor(Color.RED);

                g2.fillRect(
                        hoverCol * 100,
                        hoverRow * 100 + 100,
                        100,
                        100
                );
            }

            g2.setComposite(
                    AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER,
                            1f
                    )
            );
        }
        //mower
        g.setColor(Color.GRAY);
        for(var m : game.mowers) {
            if(!m.used || m.active) {
                g.fillRect(
                        (int)m.x,
                        m.row * 100 + OffsetY + 30,
                        50,
                        40
                );
            }
        }
        // Vẽ zombie
        for (var z : game.Zombies) {

            if (z instanceof BrownSuit) {
                g.setColor(Color.RED);
            }
            else if (z instanceof ConeHead) {
                g.setColor(Color.ORANGE);
            }
            else if (z instanceof BucketHead) {
                g.setColor(Color.GRAY);
            }
            else if (z instanceof NewsPaper) {
                g.setColor(Color.WHITE);
            }
            else if (z instanceof PoleVaulting) {
                g.setColor(Color.BLACK);
            }

            g.fillRect(
                    (int)z.x,
                    z.row * 100 + OffsetY + 20,
                    60,
                    60
            );
        }
        //Sun
        g.setColor(Color.ORANGE);
        for (var s : game.suns) {
            g.fillOval(s.x, s.y, 40, 40);
        }
        // display sun
        g.setColor(Color.BLACK);
        g.drawString("Sun: " + game.sun, 20, 20);
    }
    private void handleClick(int x, int y) {
        //collect sun
        for (int i = 0; i < game.suns.size(); i++) {
            var s = game.suns.get(i);

            if (x >= s.x && x <= s.x + 40 &&
                    y >= s.y && y <= s.y + 40) {

                game.sun += s.value;
                game.suns.remove(i);
                repaint();
                return;
            }
        }
        //select tree
        if (y <= 100) {
            if (x >= 50 && x <= 110) {
                selectedPlant = PlantType.SUNFLOWER;
            }
            else if (x >= 150 && x <= 210) {
                selectedPlant = PlantType.PEASHOOTER;
            }
            else if (x >= 250 && x <= 310) {
                selectedPlant = PlantType.POTATOMINE;
            }
            else if (x >= 350 && x <= 410) {
                selectedPlant = PlantType.SNOWPEA;
            }
            else if (x >= 450 && x <= 510) {
                selectedPlant = PlantType.REPEATER;
            }
            else if (x >= 550 && x <= 610) {
                selectedPlant = PlantType.CHOMPER;
            }
            else if (x >= 650 && x <= 710) {
                selectedPlant = PlantType.CHERRYBOMB;
            }
            else if (x >= 750 && x <= 810) {
                selectedPlant = PlantType.WALL_NUT;
            }
            else if(x >= 850 && x <= 890) {
                shovelMode = !shovelMode;
                selectedPlant = null;
            }
            return;
        }

        // plant tree
        int col = x / 100;
        int row = (y - 100) / 100;
        if (row >= game.grid.rows || col >= game.grid.cols) return;


        if(shovelMode) {
            game.grid.cells[row][col].plant = null;
            repaint();
            return;
        }

        if (game.grid.cells[row][col].plant == null && selectedPlant != null) {

            switch (selectedPlant) {
                case WALL_NUT:
                    if (game.sun >= 50) {
                        game.grid.cells[row][col].plant = new WallNut(row, col);
                        game.sun -= 50;
                    }
                    break;

                case CHERRYBOMB:
                    if (game.sun >= 150) {
                        game.grid.cells[row][col].plant = new CherryBomb(row, col);
                        game.sun -= 150;
                    }
                    break;

                case SUNFLOWER:
                    if (game.sun >= 50) {
                        game.grid.cells[row][col].plant = new SunFlower(row, col);
                        game.sun -= 50;
                    }
                    break;
                case PEASHOOTER:
                    if (game.sun >= 100) {
                        game.grid.cells[row][col].plant = new PeaShooter(row, col);
                        game.sun -= 100;
                    }
                    break;
                case POTATOMINE:
                    if (game.sun >= 25) {
                        game.grid.cells[row][col].plant = new PotatoMine(row, col);
                        game.sun -= 25;
                    }
                    break;

                case SNOWPEA:
                    if (game.sun >= 175) {
                        game.grid.cells[row][col].plant = new SnowPea(row, col);
                        game.sun -= 175;
                    }
                    break;

                case REPEATER:
                    if (game.sun >= 200) {
                        game.grid.cells[row][col].plant = new Repeater(row, col);
                        game.sun -= 200;
                    }
                    break;

                case CHOMPER:
                    if (game.sun >= 150) {
                        game.grid.cells[row][col].plant = new Chomper(row, col);
                        game.sun -= 150;
                    }
                    break;
            }
        }

        repaint();
    }
}
