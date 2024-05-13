package com.project.orderxmlfiltered.order;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderDTO toDTO(Order entity);
    List<OrderDTO> toDTOList(List<Order> entity);
    @InheritInverseConfiguration(name = "toDTO")
    Order toEntity(OrderDTO dto);
    @InheritInverseConfiguration(name = "toDTOList")
    List<Order> toEntityList(List<OrderDTO> model);
}
