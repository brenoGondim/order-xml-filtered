package com.project.orderxmlfiltered;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long controlNumber;
    private Long clientId;
    private String productName;
    private Double unitValue;
    private Double totalValue;
    private Integer quantity;
    private LocalDateTime registerDate;

}
