package bootcamp.edu.crm.dto.request;

import bootcamp.edu.crm.dto.OrderDto;
import bootcamp.edu.crm.dto.UserDto;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequest {

    private UserDto user;
    private List<OrderDto> orders;
}