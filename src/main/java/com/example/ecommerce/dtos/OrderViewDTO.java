package com.example.ecommerce.dtos;

import com.example.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
public interface OrderViewDTO {

    Integer getId();
    String getStatus();
    String getCreatedAt();  // Assuming you convert Date to String format
//    String getDestination();

=======
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDTO {
    private Integer id;
    private Status status ;
    private Date createdAt;
    private String  destination;
>>>>>>> 6c390d291bb1ad329302c76764060f5e72760a81
}