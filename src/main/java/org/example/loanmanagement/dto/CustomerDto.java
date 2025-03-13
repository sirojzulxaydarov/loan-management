package org.example.loanmanagement.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private Integer storeId;

}
