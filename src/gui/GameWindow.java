package gui;

import game.Game;
import res.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JPanel {
    private Game game;

    public GameWindow(Game game) {
        this.game = game;
        addMouseListener(new Input());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));

        for (int x = Game.FIELD_WIDTH; x <= Game.FIELD_WIDTH * 2; x += Game.FIELD_WIDTH) {
            graphics2D.drawLine(x, 0, x, Game.HEIGHT);
        }
        for (int y = Game.FIELD_HEIGHT; y <= Game.FIELD_WIDTH * 2; y += Game.FIELD_HEIGHT) {
            graphics2D.drawLine(0, y, Game.WIDTH, y);
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++){
                int field = game.getFields()[x][y];
                if (field != 0) {
                    graphics2D.drawImage(Resources.letters[field - 1], x * Game.FIELD_WIDTH, y * Game.FIELD_HEIGHT, Game.FIELD_WIDTH, Game.FIELD_HEIGHT, null);
                }
            }
        }
    }
    class Input extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (e.getButton() == MouseEvent.BUTTON1){
                game.inputReceived(e.getX()/Game.FIELD_WIDTH, e.getY()/Game.FIELD_HEIGHT);
            }
        }
    }
}
