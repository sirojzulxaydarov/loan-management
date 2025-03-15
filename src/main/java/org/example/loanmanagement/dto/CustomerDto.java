package org.example.loanmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private Integer storeId;

}
