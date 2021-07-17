package edu.school21.game.engine;

public enum Action {
    LEFT("A"),
    UP("W"),
    RIGHT("D"),
    DOWN("S"),
    GIVE_UP("9");

    private final String keyCode;

    Action(String code) {
        this.keyCode = code;
    }

    private static final Action[] ACTIONS = values();

    public static Action getNameByKeyCode(String code) {
        for (Action action : ACTIONS) {
            if (action.keyCode.equals(code)) {
                return action;
            }
        }
        return null;
    }
}
