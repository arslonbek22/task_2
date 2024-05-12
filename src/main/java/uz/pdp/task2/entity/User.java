package uz.pdp.task2.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.task2.entity.template.ParentEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;

    private String role;
}
