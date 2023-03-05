import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;

public class GUI extends JFrame{
    
    JComboBox<String> difficulty;
    JComboBox<String> dimension;

    GUI(){
        super.setTitle("Minesweeper");
        super.setVisible(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(400, 480));


        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setComponents(this.getContentPane());
        super.pack();
    }

    private void setComponents(Container pane){
        String[] difficulties = {"Easy", "Medium", "Hard"};
        String[] dimensions = {"400x300", "640x640", "1024x768"};

        difficulty = new JComboBox<String>(difficulties);
        dimension = new JComboBox<String>(dimensions);

        difficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        dimension.setAlignmentX(Component.CENTER_ALIGNMENT);

        pane.add(dimension);
        pane.add(difficulty);
    }
}
