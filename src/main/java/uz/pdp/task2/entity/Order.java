package uz.pdp.task2.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.task2.entity.template.ParentEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
@Builder

public class Order extends ParentEntity {
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    private int orderNumber;

    private String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.REMOVE)
    List<OrderProduct> orderProducts;
}
