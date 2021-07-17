public class UserIdsGenerator {
    private static UserIdsGenerator instance;

    private Integer id;

    private UserIdsGenerator() {
        this.id = 1;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }

        return instance;
    }

    public Integer generateId() {
        return this.id++;
    }
}