import java.util.Stack;

public class Engine {

    Engine(){
    }

    public void visitSquare(Game game, int x, int y){
        if(game.visited[y][x] == 0 && game.flaged[y][x] == 0){
            game.visited[y][x] = 1;
            if(game.squares[y][x] == 0){
                this.revealNearEmptySquares(game, x, y);
            }else if(game.squares[y][x] == '*'){
                this.revealBombSquares(game);
            }
        }
    }

    public void flagSquare(Game game, int x, int y){
        if(game.visited[y][x] == 0)
            game.flaged[y][x] = 1 - game.flaged[y][x];
    }


    private void revealNearEmptySquares(Game game, int x, int y){
        Stack<Integer> sx = new Stack<Integer>();
        Stack<Integer> sy = new Stack<Integer>();

        sx.push(x);
        sy.push(y);

        while(!sx.empty() && !sy.empty()){
            int y0 = sy.pop();
            int x0 = sx.pop();

            for(int y1 = y0 - 1; y1 <= y0 + 1 ; y1++){
                for(int x1 = x0 - 1; x1 <= x0 + 1 ; x1++){
                    if(x1 == x0 && y1 == y0)
                        continue;
                    if(x1 < 0 || x1 >= game.w)
                        continue;
                    if(y1 < 0 || y1 >= game.h)
                        continue;
                    if(game.visited[y1][x1] == 1)
                        continue;
                    game.visited[y1][x1] = 1;
                    if(game.flaged[y1][x1] == 1)
                        game.flaged[y1][x1] = 0;
                    if(game.squares[y1][x1] == 0){
                        sx.push(x1);
                        sy.push(y1);
                    }
                }
            }
        }
    }

    private void revealBombSquares(Game game){
        for(int i = 0; i < game.h; i++){
            for(int j = 0; j < game.w; j++){
                if(game.squares[i][j] == '*')
                    game.visited[i][j] = 1;
            }
        }
    }
}
