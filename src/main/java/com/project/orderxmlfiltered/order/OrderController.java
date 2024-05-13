package com.project.orderxmlfiltered.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper mapper;
    private final IOrderService service;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<OrderDTO>> register(
            @RequestBody
            @Valid
            @Size(max = 10, message = "Request list must have top 10 elements")
            List<OrderDTO> order
    ) {
        return ResponseEntity.ok(mapper.toDTOList(service.register(mapper.toEntityList(order))));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll(@RequestParam(name = "date", required = false) LocalDate date) {
        return ResponseEntity.ok(mapper.toDTOList(service.getAll(date)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(service.getById(id)));
    }
}
