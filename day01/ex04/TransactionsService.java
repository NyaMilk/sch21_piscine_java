import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(String name, Integer balance) {
        User user = new User(name, balance);
        usersList.addUser(user);
    }

    public String getUserName(Integer id) throws UserNotFoundException {
        return usersList.getUserById(id).getName();
    }

    public Integer getUserBalance(Integer id) throws UserNotFoundException {
        return usersList.getUserById(id).getBalance();
    }

    public void makeTransfer(Integer idSender, Integer idRecipient, Integer amount) throws UserNotFoundException, IllegalTransactionException {
        User sender = usersList.getUserById(idSender);
        User recipient = usersList.getUserById(idRecipient);

        if (amount <= 0) {
            throw new IllegalTransactionException("Transaction is not possible, amount not correct!");
        }

        if (sender.getBalance() - amount < 0) {
            throw new IllegalTransactionException("Transaction is not possible, insufficient funds available!");
        }

        Transaction transactionIn = new Transaction(recipient, sender, transactionType.INCOME, amount);
        recipient.addTransaction(transactionIn);
        recipient.setBalance(recipient.getBalance() + amount);

        Transaction transactionOut = new Transaction(transactionIn.getUuid(), recipient, sender, transactionType.OUTCOME, -amount);
        sender.addTransaction(transactionOut);
        sender.setBalance(sender.getBalance() - amount);
    }

    public void removeTransaction(Integer id, UUID idTransaction) throws UserNotFoundException, TransactionNotFoundException {
        usersList.getUserById(id).removeTransaction(idTransaction);
    }

    public Transaction[] getUserTransactions(Integer id) throws UserNotFoundException {
        return usersList.getUserById(id).getTransactionsArray();
    }

    public Transaction[] getUnpairedTransactions() throws UserNotFoundException, TransactionNotFoundException {
        boolean flag;

        TransactionsList unpairedList = new TransactionsLinkedList();
        TransactionsList transactionsListIn = new TransactionsLinkedList();
        TransactionsList transactionsListOut = new TransactionsLinkedList();

        int countIn = 0;
        int countOut = 0;

        for (int i = 0; i < usersList.getUsersCount(); i++) {
            User user = usersList.getUserByIndex(i);
            Transaction[] transaction = user.getTransactionsArray();

            for (int j = 0; j < transaction.length; j++) {
                switch (transaction[j].getTransactionType()) {
                    case OUTCOME: {
                        transactionsListIn.addTransaction(transaction[j]);
                        countIn++;
                        break;
                    }
                    case INCOME: {
                        transactionsListOut.addTransaction(transaction[j]);
                        countOut++;
                        break;
                    }
                }
            }
        }

        Transaction[] transactionsArrayIn = transactionsListIn.transactionToArray();
        Transaction[] transactionsArrayOut = transactionsListOut.transactionToArray();

        for (int i = 0; i < countIn; i++) {
            flag = false;

            for (int j = 0; j < countOut; j++) {
                if (transactionsArrayIn[i].getUuid().equals(transactionsArrayOut[j].getUuid())) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                unpairedList.addTransaction(transactionsArrayIn[i]);
            }
        }

        for (int i = 0; i < countOut; i++) {
            flag = false;

            for (int j = 0; j < countIn; j++) {
                if (transactionsArrayOut[i].getUuid().equals(transactionsArrayIn[j].getUuid())) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                unpairedList.addTransaction(transactionsArrayOut[i]);
            }
        }

        return unpairedList.transactionToArray();
    }
}