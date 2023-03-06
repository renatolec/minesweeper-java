// JAVA UTILS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// JAVA AWT
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
// JAVA IO
import java.io.File;
import java.io.IOException;
// JAVAX
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel{
    
    // Minesweeper sprites
    Image SquareClosed, SquareWrong, SquareBomb, SquareFlag;
    Image[] SquareNumbers = new Image[10];

    // Minesweeper graphic
    Dimension dimension = new Dimension(640,640);
    int margin = 0;
    int blocksize = 0;
    
    // Minesweeper constraints
    int w = 20, h = 20, b = 80;
    int[][] squares = new int[h][w];
    int[][] visited = new int[h][w];
    int[][] flaged = new int[h][w];
    int[][] hovered = new int[h][w];

    Game(int b){
        this.setPreferredSize(dimension);
        this.setupVariables(b);
        this.loadImages();
        this.setupField();
    }

    private void setupVariables(int b){
        blocksize = (int)dimension.getWidth()/w;
        this.b = b;
    }

    private void setupField(){
        Arrays.stream(squares).forEach(row->Arrays.fill(row,0));
        Arrays.stream(visited).forEach(row->Arrays.fill(row,0));
        Arrays.stream(flaged).forEach(row->Arrays.fill(row,0));
        ArrayList<Integer> mines = this.setupMines();
        this.setupSquares(mines);
    }

    private ArrayList<Integer> setupMines(){
        ArrayList<Integer> mines = new ArrayList<Integer>();
        for(int i = 0; i < h*w; i++)
            mines.add(i);
        Collections.shuffle(mines, new Random());
        for(int i = 0; i < b ; i++)
            squares[mines.get(i)/h][mines.get(i)%h] = '*';
        return mines;
    }

    private void setupSquares(ArrayList<Integer> mines){
        int x, y;
        for(int i = 0; i < b ; i++){
            x = mines.get(i)%h;
            y = mines.get(i)/h;
            this.setupAdjacentSquares(x, y);
        }
    }

    private void setupAdjacentSquares(int x, int y){
        for(int i = y - 1; i <= y + 1 ; i++){
            for(int j = x - 1; j <= x + 1 ; j++){
                if(j == x && i == y)
                    continue;
                if(i < 0 || i >= h)
                    continue;
                if(j < 0 || j >= w)
                    continue;
                if(squares[i][j] == '*')
                    continue;
                squares[i][j]++;
            }
        }
    }

    private void loadImages(){
        try {
            SquareClosed = ImageIO.read(new File("../img/Closed.png"));
            SquareWrong = ImageIO.read(new File("../img/Wrong.png"));
            SquareBomb = ImageIO.read(new File("../img/Bomb.png"));
            SquareFlag = ImageIO.read(new File("../img/Flag.png"));
            SquareNumbers[0] = ImageIO.read(new File("../img/Zero.png"));
            SquareNumbers[1] = ImageIO.read(new File("../img/One.png"));
            SquareNumbers[2] = ImageIO.read(new File("../img/Two.png"));
            SquareNumbers[3] = ImageIO.read(new File("../img/Three.png"));
            SquareNumbers[4] = ImageIO.read(new File("../img/Four.png"));
            SquareNumbers[5] = ImageIO.read(new File("../img/Five.png"));
            SquareNumbers[6] = ImageIO.read(new File("../img/Six.png"));
            SquareNumbers[7] = ImageIO.read(new File("../img/Seven.png"));
            SquareNumbers[8] = ImageIO.read(new File("../img/Eight.png"));
        } catch (IOException e) {
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.BOLD, 18));

        boolean change = false;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w ; j++){
                int x, y;
                x = j * blocksize + margin;
                y = i * blocksize + margin;

                if(flaged[i][j] == 1)
                    g.drawImage(SquareFlag, x, y, blocksize, blocksize, this);
                else if(visited[i][j] == 1){
                    int index = squares[i][j];
                    if(index == '*')
                        g.drawImage(SquareBomb, x, y, blocksize, blocksize, this);
                    else
                        g.drawImage(SquareNumbers[index], x, y, blocksize, blocksize, this);
                }else
                    g.drawImage(SquareClosed, x, y, blocksize, blocksize, this);

                change = !change;
            }
            change = !change;
        }

        Toolkit.getDefaultToolkit().sync();
    }



}