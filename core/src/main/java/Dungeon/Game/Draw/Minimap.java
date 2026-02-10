package Dungeon.Game.Draw;

public class Minimap {
    private int[][] map;
    private int[][] status;

    public void setStatus(int x, int y, int statusNumb) {
        status[x][y] = statusNumb;

        int width = map.length;
        int height = map[0].length;

        if (x > 0 && map[x-1][y] != 0 && status[x-1][y] == 0) {
            status[x-1][y] = 1;
        }

        if (x < width - 1 && map[x+1][y] != 0 && status[x+1][y] == 0) {
            status[x+1][y] = 1;
        }

        if (y > 0 && map[x][y-1] != 0 && status[x][y-1] == 0) {
            status[x][y-1] = 1;
        }

        if (y < height - 1 && map[x][y+1] != 0 && status[x][y+1] == 0) {
            status[x][y+1] = 1;
        }
    }


    public void reset(){
        status = new int[11][11];
        for (int[] row : status) {
            java.util.Arrays.fill(row, 0);
        }
    }
    public void setMap(int[][] map){
        this.map = map;
    }
    public int[][] getStatus() {
        return status;
    }
}
