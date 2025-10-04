package bootcamp.edu.crm.repositary;

import bootcamp.edu.crm.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositary extends CrudRepository <Orders, Long>{
}
