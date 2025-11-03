package bankapp.domain;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @SerializedName("id")
    private final String id;

    @SerializedName("type")
    private final TransactionType type;

    @SerializedName("amount")
    private final Money amount;

    @SerializedName("timestamp")
    private final LocalDateTime timestamp;

    @SerializedName("description")
    private final String description;

    @SerializedName("sourceAccountId")
    private final String sourceAccountId;

    @SerializedName("destinationAccountId")
    private final String destinationAccountId;

    @SerializedName("exchangeRate")
    private final Double exchangeRate;

    @SerializedName("fee")
    private final Money fee;

    public Transaction(TransactionType type, Money amount, String accountId, String description) {
        this(type, amount, accountId, null, description, null, null);
    }

    public Transaction(TransactionType type, Money amount, String sourceAccountId,
            String destinationAccountId, String description, Double exchangeRate, Money fee) {
        if (type == null) {
            throw new IllegalArgumentException("Transaction type cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (sourceAccountId == null || sourceAccountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Source account ID cannot be null or empty");
        }

        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description != null ? description : "";
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.exchangeRate = exchangeRate;
        this.fee = fee;
    }

    public Transaction(String id, TransactionType type, Money amount, LocalDateTime timestamp,
            String description, String sourceAccountId, String destinationAccountId,
            Double exchangeRate, Money fee) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.exchangeRate = exchangeRate;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Money getFee() {
        return fee;
    }

    public String getFormattedTimestamp() {
        return timestamp.format(FORMATTER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormattedTimestamp()).append(" | ");
        sb.append(type.getDisplayName()).append(" | ");
        sb.append(amount.toString());

        if (destinationAccountId != null) {
            sb.append(" -> ").append(destinationAccountId);
        }

        if (!description.isEmpty()) {
            sb.append(" (").append(description).append(")");
        }

        if (exchangeRate != null) {
            sb.append(" [Rate: ").append(String.format("%.4f", exchangeRate)).append("]");
        }

        if (fee != null) {
            sb.append(" [Fee: ").append(fee.toString()).append("]");
        }

        return sb.toString();
    }
}