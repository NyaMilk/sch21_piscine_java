package edu.school21.chaseLogic;

import java.util.ArrayList;
import java.util.Arrays;

public class BotEngine {
    private final char emptyChar;
    private char[][] map;
    private Position player;
    private Position bot;

    public BotEngine(char[][] map, Integer playerX, Integer playerY, char emptyChar) {
        this.map = map;
        this.player = new Position(playerX, playerY);
        this.emptyChar = emptyChar;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void setPlayer(Integer x, Integer y) {
        this.player = new Position(x, y);
    }

    public void setCurrentBot(Integer x, Integer y) {
        this.bot = new Position(x, y);
    }

    public Integer[] getMove() {
        ArrayList<Position> priorityMoves = getMovePriority();

        Position bestMove = new Position(bot.getX(), bot.getY());

        for (Position movePosition : priorityMoves) {
            if (isMovableToDirection(movePosition.getX(), movePosition.getY())) {
                bestMove = movePosition;
                break;
            }
        }

        Integer[] position = new Integer[2];

        position[0] = bestMove.getX();
        position[1] = bestMove.getY();

        return position;
    }

    private ArrayList<Position> getMovePriority() {
        ArrayList<Position> priorityMoves = new ArrayList<>();

        Position up = new Position(bot.getX(), bot.getY() - 1);
        Position down = new Position(bot.getX(), bot.getY() + 1);
        Position right = new Position(bot.getX() + 1, bot.getY());
        Position left = new Position(bot.getX() - 1, bot.getY());

        int deltaY = bot.getY() - player.getY();
        int deltaX = bot.getX() - player.getX();

        if (deltaX == 0 || (Math.abs(deltaY) < Math.abs(deltaX) && deltaY != 0)) {
            if (deltaY < 0 && deltaX <= 0) {
                priorityMoves.add(down);
                priorityMoves.add(right);
                priorityMoves.add(up);
                priorityMoves.add(left);
            } else if (deltaY < 0) {
                priorityMoves.add(down);
                priorityMoves.add(left);
                priorityMoves.add(up);
                priorityMoves.add(right);
            } else if (deltaY > 0 && deltaX < 0) {
                priorityMoves.add(up);
                priorityMoves.add(right);
                priorityMoves.add(down);
                priorityMoves.add(left);
            } else if (deltaY > 0) {
                priorityMoves.add(up);
                priorityMoves.add(left);
                priorityMoves.add(down);
                priorityMoves.add(right);
            }
        } else {
            if (deltaY <= 0 && deltaX < 0) {
                priorityMoves.add(right);
                priorityMoves.add(down);
                priorityMoves.add(left);
                priorityMoves.add(up);
            } else if (deltaY <= 0) {
                priorityMoves.add(left);
                priorityMoves.add(down);
                priorityMoves.add(right);
                priorityMoves.add(up);
            } else if (deltaX < 0) {
                priorityMoves.add(right);
                priorityMoves.add(up);
                priorityMoves.add(left);
                priorityMoves.add(down);
            } else {
                priorityMoves.add(left);
                priorityMoves.add(up);
                priorityMoves.add(right);
                priorityMoves.add(down);
            }
        }

        return priorityMoves;
    }

    private boolean isMovableToDirection(int x, int y) {
        boolean isEdgeOfMap = x >= 0 && x < map.length && y >= 0 && y < map.length;

        return isEdgeOfMap && (map[y][x] == emptyChar || (x == player.getX() && y == player.getY()));
    }
}
