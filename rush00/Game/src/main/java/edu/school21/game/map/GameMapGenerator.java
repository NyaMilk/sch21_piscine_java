package edu.school21.game.map;

import edu.school21.game.app.AppProperties;

import java.util.Arrays;
import java.util.Random;

public class GameMapGenerator {
    private final int enemiesCount;
    private final int wallsCount;
    private final int sizeMap;
    private final char enemyChar;
    private final char playerChar;
    private final char wallChar;
    private final char goalChar;
    private final char emptyChar;
    private final GameMap gameMap;

    public GameMapGenerator(int enemiesCount, int wallsCount, int sizeMap, AppProperties appProperties) {
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.sizeMap = sizeMap;
        this.enemyChar = appProperties.getEnemy();
        this.playerChar = appProperties.getPlayer();
        this.wallChar = appProperties.getWall();
        this.goalChar = appProperties.getGoal();
        this.emptyChar = appProperties.getEmpty();
        this.gameMap = new GameMap(new char[sizeMap][sizeMap]);
    }

    public GameMap generate() {
        char[][] map = gameMap.getMap();

        fillMapByChar(map, wallChar, wallsCount);

        fillMapByChar(map, enemyChar, enemiesCount);

        fillMapByChar(map, playerChar, 1);

        fillMapByChar(map, goalChar, 1);

        fillMapByEmptyChar(map);

        if (isValidMap(map)) {
            return gameMap;
        } else {
            clearMap(map);
            return generate();
        }
    }

    private Position getFreePosition(char[][] map) {
        Random random = new Random();
        Position position = new Position(random.nextInt(sizeMap), random.nextInt(sizeMap));

        while (map[position.getY()][position.getX()] != '\0') {
            position.setX(random.nextInt(sizeMap));
            position.setY(random.nextInt(sizeMap));
        }
        return position;
    }

    private void fillMapByChar(char[][] map, char symbol, int count) {
        for (int i = 0; i < count; i++) {
            Position position = getFreePosition(map);
            map[position.getY()][position.getX()] = symbol;

            if (symbol == enemyChar) {
                gameMap.getEnemies().put(i, position);
            }

            if (symbol == playerChar) {
                gameMap.setPlayer(position);
            }

            if (symbol == goalChar) {
                gameMap.setGoal(position);
            }
        }
    }

    private void fillMapByEmptyChar(char[][] map) {
        for (int y = 0; y < sizeMap; y++) {
            for (int x = 0; x < sizeMap; x++) {
                if (map[y][x] == 0) {
                    map[y][x] = emptyChar;
                }
            }
        }
    }

    private boolean isValidMap(char[][] map) {
        Position player = gameMap.getPlayer();
        int x = player.getX();
        int y = player.getY();

        boolean left = x != 0 && map[y][x - 1] == emptyChar;
        boolean right = (x != sizeMap - 1) && map[y][x + 1] == emptyChar;
        boolean up = y != 0 && map[y - 1][x] == emptyChar;
        boolean down = (y != sizeMap - 1) && map[y + 1][x] == emptyChar;

        return right || left || up || down;
    }

    private void clearMap(char[][] map) {
        for (int y = 0; y < sizeMap; y++) {
            for (int x = 0; x < sizeMap; x++) {
                map[y][x] = emptyChar;
            }
        }
    }
}
