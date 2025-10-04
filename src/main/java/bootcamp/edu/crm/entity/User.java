package bootcamp.edu.crm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * A simple field that will be mapped to a column named "name" in the "user" table.
     */
    private String name;

    /**
     * Defines a one-to-many relationship between User and Orders. One User can have many Orders.
     *
     * mappedBy = "user": Specifies that the 'user' field in the 'Orders' entity is the owner of this
     * relationship. This is the inverse (non-owning) side. The foreign key information is in the Orders class.
     *
     * cascade = {CascadeType.PERSIST, CascadeType.MERGE}: Defines which operations should be cascaded
     * from the parent (User) to the child (Orders).
     * - PERSIST: If you save a new User, any new Orders in its list will also be saved.
     * - MERGE: If you update a User, changes to the Orders in its list will also be updated.
     *
     * orphanRemoval = true: This is a crucial setting. If an Order is removed from this list,
     * the Order entity is considered "orphaned" and will be automatically deleted from the database.
     * This is very useful for update operations.
     */
    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    /**
     * Used to handle circular dependencies during JSON serialization (User -> Orders -> User ...).
     * This is the "forward" part of the reference – it tells the serializer (like Jackson) to
     * process this list of orders normally.
     */
    @JsonManagedReference
    private List<Orders> orders = new ArrayList<>();
}