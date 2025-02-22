package mate.academy.carsharingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Integer inventory;
    private BigDecimal dailyFee;
    private Type type;

    public enum Type {
        SEDAN,
        SUV,
        HATCHBACK,
        UNIVERSAL
    }
}
