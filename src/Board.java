/**
 * Created by Kyle on 11/6/2014.
 */

public class Board {
    private int size;
    Spot spaces[][];
    public Board(int _size, int cellSize){
        size = _size/cellSize;
        spaces = new Spot[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                spaces[i][j] = new Spot();
            }
        }
    }
    public void update(){
        int numNeighbors = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (occupied(i-1,j-1)) numNeighbors++;
                if (occupied(i,j-1)) numNeighbors++;
                if (occupied(i+1,j-1)) numNeighbors++;
                if (occupied(i-1,j)) numNeighbors++;
                if (occupied(i+1,j)) numNeighbors++;
                if (occupied(i-1,j+1)) numNeighbors++;
                if (occupied(i,j+1)) numNeighbors++;
                if (occupied(i+1,j+1)) numNeighbors++;
                if (spaces[i][j].isOccupied){
                    if ( numNeighbors < 2 || numNeighbors > 3) spaces[i][j].change = true;
                } else if (numNeighbors == 3) spaces[i][j].change = true;
                numNeighbors = 0;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (spaces[i][j].change){
                    spaces[i][j].change = false;
                    spaces[i][j].isOccupied = !spaces[i][j].isOccupied;
                }

            }
        }
    }
    public boolean occupied(int x, int y){
        if ( x == size) x = 0;
        else if (x == -1) x += size;
        if ( y == size) y = 0;
        else if (y == -1) y += size;
        return ( x >= 0 && x < size && y >= 0 && y < size && spaces[x][y].isOccupied);
    }
}


class Spot{
    public boolean isOccupied = false;
    public boolean change = false;
}
