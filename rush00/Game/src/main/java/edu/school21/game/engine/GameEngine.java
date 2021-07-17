package edu.school21.game.engine;

import edu.school21.game.app.AppProperties;
import edu.school21.game.app.ArgsProperties;
import edu.school21.game.map.GameMap;
import edu.school21.game.map.GameMapGenerator;
import edu.school21.game.map.Position;
import edu.school21.game.printer.Printer;
import edu.school21.chaseLogic.BotEngine;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameEngine {
    private final AppProperties appProperties;
    private final String profile;
    private GameMap gameMap;
    private volatile boolean isGameOver;

    public GameEngine(ArgsProperties argsProperties, AppProperties appProperties) {
        initGame(argsProperties, appProperties);
        this.profile = argsProperties.getProfile();
        this.appProperties = appProperties;
        this.isGameOver = false;
    }

    private void initGame(ArgsProperties argsProperties, AppProperties appProperties) {
        int enemiesCount = argsProperties.getEnemiesCount();
        int wallsCount = argsProperties.getWallsCount();
        int sizeMap = argsProperties.getSizeMap();
        GameMapGenerator gameMapGenerator = new GameMapGenerator(enemiesCount, wallsCount, sizeMap, appProperties);
        gameMap = gameMapGenerator.generate();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        Movement movement = new Movement(gameMap, appProperties);
        Printer printer = new Printer(appProperties, gameMap.getMap());
        Map<Integer, Position> bots = gameMap.getEnemies();
        Position player = gameMap.getPlayer();
        BotEngine botEngine = new BotEngine(gameMap.getMap(), player.getX(), player.getY(), appProperties.getEmpty());

        while (!isGameOver) {
            printer.printMap(profile);

            if (!movement.isMovable(player.getX(), player.getY())) {
                gameOverAlert("You lose by surround enemies!");
            }

            if (handleAction(scanner, movement)) {
                isGameOver = gameMap.isFinish();

                if (isGameOver) {
                    renderMap(printer);
                    gameOverAlert("You win!");
                }

                renderMap(printer);
                botEngine.setPlayer(player.getX(), player.getY());

                for (int i = 0; i < bots.size(); i++) {
                    Position currentBot = bots.get(i);
                    botEngine.setMap(gameMap.getMap());
                    botEngine.setCurrentBot(currentBot.getX(), currentBot.getY());
                    Integer[] smartMove = botEngine.getMove();

                    if (profile.equals("dev")) {
                        System.out.printf("Enemy want go to (%d, %d). Approve it by press 8.%n", smartMove[1], smartMove[0]);
                    }

                    movement.goToDirection(currentBot, smartMove[0], smartMove[1], appProperties.getEnemy());

                    if (profile.equals("dev")) {
                        if (handleDevAction(scanner)) {
                            if (i != bots.size() - 1) {
                                renderMap(printer);
                            }
                        } else {
                            gameOverAlert("You lose by not approving bot's smart move!");
                        }
                    }

                    currentBot.setX(smartMove[0]);
                    currentBot.setY(smartMove[1]);

                    if (gameMap.isCatchByEnemy(currentBot)) {
                        isGameOver = true;
                        renderMap(printer);
                        gameOverAlert("You lose!");
                    }
                }

                printer.setMap(gameMap.getMap());

                isGameOver = gameMap.isFinish();
            }
        }
    }

    private void renderMap(Printer printer) {
        printer.setMap(gameMap.getMap());
        printer.printMap(profile);
    }

    private void gameOverAlert(String message) {
        System.out.println(message);
        System.exit(0);
    }

    private boolean handleDevAction(Scanner scanner) {
        String line = scanner.nextLine();

        try {
            int symbol = Integer.parseInt(line);
            return symbol == 8;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean handleAction(Scanner scanner, Movement movement) {
        String input = scanner.nextLine();
        Action action = Action.getNameByKeyCode(input.toUpperCase());

        if (action == null) {
            return false;
        } else {
            return movement.handleAction(action);
        }
    }
}
