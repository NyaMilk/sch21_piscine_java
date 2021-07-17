public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        User buyer = new User("Buyer", 1000);
        User seller = new User("Seller", 2000);
        UsersList usersList = new UsersArrayList();

        System.out.println("Users count: " + usersList.getUsersCount());

        usersList.addUser(buyer);
        usersList.addUser(seller);

        System.out.println("Users count: " + usersList.getUsersCount());

        System.out.println("First user: " + usersList.getUserById(1).getId() + " " + usersList.getUserById(1).getName() + ", balance: " + usersList.getUserById(1).getBalance());
        System.out.println("Second user: " + usersList.getUserById(2).getId() + " " + usersList.getUserById(2).getName() + ", balance: " + usersList.getUserById(2).getBalance());

        for (int i = 0; i < 15; i++) {
            usersList.addUser(buyer);
        }

        System.out.println("Users count: " + usersList.getUsersCount());

        System.out.println("Check exception:");
        System.out.println(usersList.getUserById(3).getId());
    }
}