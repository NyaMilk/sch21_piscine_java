import java.util.UUID;

public class Program {
    public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {
        TransactionsService service = new TransactionsService();

        String[] name = {"Npetrell", "Rubi", "Kina", "Ning", "Melissa"};

        for (int i = 0; i < 5; i++) {
            service.addUser(name[i], 1225);
        }

        for (int i = 1; i < 5; i++) {
            service.makeTransfer(i, i + 1, 15 * i);
        }

        for (int i = 1; i < 6; i++) {
            Transaction[] transactions = service.getUserTransactions(i);

            for (int j = 0; j < transactions.length; j++) {
                System.out.printf("%s  %s  %s  %s  %d \n", transactions[j].getUuid(), transactions[j].getRecipient().getName(),
                        transactions[j].getSender().getName(), transactions[j].getTransactionType(), transactions[j].getTransactionAmount());
            }
        }
        UUID id = service.getUserTransactions(1)[0].getUuid();
        service.removeTransaction(1, id);

        System.out.println("\nDelete first transaction.\n");

        for (int i = 1; i < 6; i++) {
            Transaction[] transactions = service.getUserTransactions(i);

            for (int j = 0; j < transactions.length; j++) {
                System.out.printf("%s  %s  %s  %s  %d \n", transactions[j].getUuid(), transactions[j].getRecipient().getName(),
                        transactions[j].getSender().getName(), transactions[j].getTransactionType(), transactions[j].getTransactionAmount());
            }
        }

        System.out.println("\nUnpaired transaction:");

        Transaction[] unpairedTransactions = service.getUnpairedTransactions();

        for (int i = 0; i < unpairedTransactions.length; i++) {
            System.out.printf("%s  %s  %s  %s  %d \n\n", unpairedTransactions[i].getUuid(), unpairedTransactions[i].getRecipient().getName(),
                    unpairedTransactions[i].getSender().getName(), unpairedTransactions[i].getTransactionType(), unpairedTransactions[i].getTransactionAmount());
        }

        System.out.println("Check exception:");
        service.makeTransfer(1, 2, -15);

    }
}