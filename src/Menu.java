import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

public class Menu extends JFrame{
    
    final int EASY = 0, NORMAL = 1, HARD = 2;
    JButton[] gamemode = new JButton[3];
    MouseAdapter cursor;
    Index game;

    Menu(){
        super.setTitle("Minesweeper");
        super.setVisible(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pane);
        this.setPreferredSize(new Dimension(300, 400));
        this.setLayout(new GridLayout(3, 1, 10, 10));
        this.setComponents();
        super.pack();
    }

    private void setComponents(){
        
        cursor = new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

                if(e.getComponent() == gamemode[0]){
                    if(game != null)
                        game.dispose();
                    game = new Index();
                    game.play(60);
                }
                if(e.getComponent() == gamemode[1]){
                    if(game != null)
                        game.dispose();
                    game = new Index();
                    game.play(80);
                }
                if(e.getComponent() == gamemode[2]){
                    if(game != null)
                        game.dispose();
                    game = new Index();
                    game.play(100);
                }
            }
        };
        this.addMouseListener(cursor);
        setButton(EASY);
        setButton(NORMAL);
        setButton(HARD);
    }

    private void setButton(int i){
        String[] s = {"EASY", "NORMAL", "HARD"};
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        Font font = new Font("Verdana", Font.BOLD, 18);
        gamemode[i] = new JButton(s[i]);
        gamemode[i].setBorder(compound);
        // gamemode[i].setBorderPainted(false);
        // gamemode[i].setContentAreaFilled(false);
        // gamemode[i].setFocusPainted(false);
        // gamemode[i].setOpaque(false);
        // gamemode[i].setBackground(Color.WHITE);
        gamemode[i].setFont(font);
        gamemode[i].setPreferredSize(new Dimension(50, 25));
        gamemode[i].setMaximumSize(new Dimension(100, 50));
        gamemode[i].setAlignmentX(CENTER_ALIGNMENT);
        gamemode[i].addMouseListener(cursor);
        this.getContentPane().add(gamemode[i]);
    }
}
