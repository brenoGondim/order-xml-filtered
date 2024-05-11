package com.project.orderxmlfiltered;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "cannot be null")
    private Long controlNumber;
    @NotNull(message = "cannot be null")
    private Long clientId;
    @NotBlank(message = "cannot be null or empty")
    private String productName;
    @NotNull(message = "cannot be null")
    private Double unitValue;
    private Double totalValue;
    private Integer quantity = 1;
    private LocalDateTime registerDate = LocalDateTime.now();;
}
