package Dungeon.Game.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class FloorGenerator {

    private int[][] currentFloor;

    public void generateFloor(){
        int size = 11;
        int[][] floor = new int[size][size];

        int minRooms = 25;
        int roomsCount = 1;

        double baseChance = 60;

        LinkedList<int[]> queue = new LinkedList<>();
        ArrayList<int[]> visited = new ArrayList<>();

        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

        queue.add(new int[]{5,5});
        visited.add(new int[]{5,5});
        floor[5][5] = 1;

        Random rand = new Random();

        while (roomsCount < minRooms) {

            if (queue.isEmpty()) {
                int[] restart = visited.get(rand.nextInt(visited.size()));
                queue.add(restart);
            }

            int[] cur = queue.removeFirst();
            int curX = cur[0];
            int curY = cur[1];

            for (int[] dir : directions) {
                int x = curX + dir[0];
                int y = curY + dir[1];

                if (x < 0 || y < 0 || x >= size || y >= size) continue;
                if (floor[x][y] == 1) continue;

                int neighbors = 0;
                for (int[] d : directions) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if (nx >= 0 && ny >= 0 && nx < size && ny < size && floor[nx][ny] == 1) {
                        neighbors++;
                    }
                }

                double chance = baseChance - (neighbors * 15);

                if (rand.nextDouble() * 100 < chance) {
                    floor[x][y] = 1;
                    roomsCount++;
                    queue.add(new int[]{x, y});
                    visited.add(new int[]{x, y});
                }
            }
        }

        currentFloor = floor;
    }

    public int[][] getFloor(){
        return currentFloor;
    }
}
