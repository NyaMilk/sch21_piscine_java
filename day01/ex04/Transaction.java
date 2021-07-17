import java.util.UUID;

enum transactionType {
    INCOME,
    OUTCOME
}

public class Transaction {
    private UUID uuid;

    private User recipient;

    private User sender;

    private transactionType transactionType;

    private Integer transactionAmount;

    public Transaction(User recipient, User sender, transactionType transactionType, Integer transactionAmount) {
        this.uuid = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transactionType = transactionType;

        if (!checkTransactionAmount(transactionAmount)) {
            System.err.println("Incorrect transaction amount value!");
            System.exit(-1);
        }
        this.transactionAmount = transactionAmount;
    }

    public Transaction(UUID uuid, User recipient, User sender, transactionType transactionType, Integer transactionAmount) {
        this.uuid = uuid;
        this.recipient = recipient;
        this.sender = sender;
        this.transactionType = transactionType;

        if (!checkTransactionAmount(transactionAmount)) {
            System.err.println("Incorrect transaction amount value!");
            System.exit(-1);
        }
        this.transactionAmount = transactionAmount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public transactionType getTransactionType() {
        return transactionType;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    private boolean checkTransactionAmount(Integer transactionAmount) {
        boolean isChecked = false;

        switch (transactionType) {
            case OUTCOME: {
                isChecked = transactionAmount < 0;
                break;
            }
            case INCOME: {
                isChecked = transactionAmount > 0;
                break;
            }
        }

        return isChecked;
    }
}