public class Program {
    public static void main(String[] args) {
        User buyer = new User(1, "Buyer", 1000);
        User seller = new User(2, "Seller", 2000);

        System.out.println("First user: " + buyer.getName() + ", balance: " + buyer.getBalance());
        System.out.println("Second user: " + seller.getName() + ", balance: " + seller.getBalance());

        Transaction transaction = new Transaction(seller, buyer, transactionType.INCOME, 750);
        System.out.println("Transfer amount: " + transaction.getTransactionAmount());

        Transaction transactionReturn = new Transaction(seller, buyer, transactionType.OUTCOME, -250);
        System.out.println("Transfer return: " + transactionReturn.getTransactionAmount());
    }
}