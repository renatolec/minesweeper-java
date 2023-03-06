import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Index extends JFrame {

    Game field;
    Engine engine;
    MouseAdapter cursor;

    Index(){
        this.setupVariables();
        this.addMouseListener(cursor);

        super.setVisible(true);
        super.setResizable(false);
        super.setTitle("Minesweeper");
        super.pack();
    }

    public void play(int b){
        field = new Game(b);
        engine = new Engine();
        this.add(field);
        this.pack();
    }

    private void setupVariables(){
        cursor = new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int x, y;
                x = (e.getX()-8-field.margin)/field.blocksize;
                y = (e.getY()-31-field.margin)/field.blocksize;

                boolean left = SwingUtilities.isLeftMouseButton(e);
                if(left)
                    engine.visitSquare(field, x, y);
                else
                    engine.flagSquare(field, x, y);
                field.repaint();
            }
        };
    }
}