package app;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{

    private final int max;
    private int x;
    private int y;
    private Moved moved;

    public BlockPanel(int max) {
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        this.max = max;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        int d = max * SIZE;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= max; i++) {
            int c = i*SIZE;
            g.drawLine(0, c, d, c);
            g.drawLine(c, 0, c, d);
        }
        
        g.setColor(Color.RED);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{

        private boolean pressed = false;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if ((e.getX() < x) || (e.getY() < y) || (e.getX() > x+SIZE) || (e.getY() > y+SIZE)) return;
            pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            moved.to(e.getX() , e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        } 
    }
}