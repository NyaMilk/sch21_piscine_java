public class Program {
    public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {
        User buyer = new User("Buyer", 1000);
        User seller = new User("Seller", 2000);
        UsersList usersList = new UsersArrayList();

        Transaction transactionFirst = new Transaction(seller, buyer, transactionType.INCOME, 750);
        Transaction transactionSecond = new Transaction(seller, buyer, transactionType.OUTCOME, -350);
        seller.getTransactions().addTransaction(transactionFirst);
        seller.getTransactions().addTransaction(transactionSecond);

        Transaction transactionReturn = new Transaction(seller, buyer, transactionType.OUTCOME, -250);
        buyer.getTransactions().addTransaction(transactionReturn);

        seller.getTransactions().removeTransactionById(transactionSecond.getUuid());

        usersList.addUser(buyer);
        usersList.addUser(seller);

        System.out.println("Buyer's balance: " + buyer.getBalance());
        System.out.println("Seller's balance: " + seller.getBalance());

        for (int i = 1; i <= usersList.getUsersCount(); i++) {
            User users = usersList.getUserById(i);

            for (int j = 0; j < users.getTransactions().transactionToArray().length; j++) {
                System.out.printf("%s's transactions: ", users.getName());
                Transaction tmp = users.getTransactions().transactionToArray()[j];
                System.out.println(tmp.getUuid() + " " + tmp.getRecipient().getName() + " " + tmp.getSender().getName() + " " + tmp.getTransactionAmount());
            }
        }

        System.out.println("Check exception:");
        seller.getTransactions().removeTransactionById(transactionSecond.getUuid());
    }
}