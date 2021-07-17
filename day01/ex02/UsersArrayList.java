public class UsersArrayList implements UsersList {
    private static final Integer DEFAULT_SIZE = 10;

    private static final double INCREASE_SIZE = 1.5;

    private User[] users;

    private Integer usersCount;

    public UsersArrayList() {
        this.users = new User[DEFAULT_SIZE];
        this.usersCount = 0;
    }

    private void resizeArray() {
        User[] tmp = new User[usersCount];

        for (int i = 0; i < usersCount; i++) {
            tmp[i] = users[i];
        }

        users = new User[(int) (users.length * INCREASE_SIZE)];

        for (int i = 0; i < usersCount; i++) {
            users[i] = tmp[i];
        }
    }

    public void addUser(User user) {
        users[usersCount++] = user;

        if (usersCount == users.length) {
            resizeArray();
        }
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < usersCount; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }

        throw new UserNotFoundException("User not found!");
    }

    public User getUserByIndex(Integer index) throws UserNotFoundException {
        if (this.users[index] != null) {
            return users[index];
        }
        throw new UserNotFoundException("User not found!");
    }

    public Integer getUsersCount() {
        return this.usersCount;
    }
}