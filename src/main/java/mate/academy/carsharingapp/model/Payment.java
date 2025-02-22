package mate.academy.carsharingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rentalId;
    private URL sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;

    public enum Status {
        PENDING,
        PAID
    }

    public enum Type {
        PAYMENT,
        FINE
    }
}
