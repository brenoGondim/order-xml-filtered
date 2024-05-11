package com.project.orderxmlfiltered;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByControlNumber(Long controlNumber);

    @Query("SELECT o FROM Order o WHERE DATE(o.registerDate) = :date")
    List<Order> findByRegisterDate(LocalDate date);

}
