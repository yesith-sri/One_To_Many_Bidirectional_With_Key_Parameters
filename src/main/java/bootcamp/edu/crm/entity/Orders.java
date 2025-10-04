package bootcamp.edu.crm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String product;

    /**
     * Defines a many-to-one relationship. Many Orders can be associated with one User.
     * This is the "owning" side of the relationship, as it will contain the foreign key.
     */
    @ManyToOne
    /**
     * This is the "back" part of the reference to solve the circular dependency issue.
     * It tells the serializer to NOT serialize the 'user' field from here, which prevents
     * an infinite loop (Orders -> User -> Orders...).
     */
    @JsonBackReference
    /**
     * Specifies the foreign key column. This annotation is placed on the field that
     * represents the parent entity.
     * The "orders" table will have a column named "user_id" that links to the "user" table's primary key.
     */
    @JoinColumn(name = "user_id")
    private User user;
}