package uz.pdp.task2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.task2.entity.template.ParentEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product extends ParentEntity {

    private String name;
    private int price;
}
