public class User {
    private Integer id;

    private String name;

    private Integer balance;

    public User(String name) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = 0;
    }

    public User(String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;

        if (balance < 0) {
            System.err.println("Start balance can't be negative!");
            System.exit(-1);
        }

        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}