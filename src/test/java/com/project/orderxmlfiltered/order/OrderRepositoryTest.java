package com.project.orderxmlfiltered.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private IOrderRepository underTest;
    private Order orderMock;

    @AfterEach
    void tearDown() { underTest.deleteAll(); }

    @BeforeEach
    public void setOrderMock() {
        orderMock = new Order(
                1L,
                2121L,
                2L,
                "Pão",
                2.9,
                5.8,
                2,
                LocalDateTime.now()
        );
    }

    @Test
    void itShouldFindByControlNumber() {
        //given
        underTest.save(orderMock);

        //when
        Optional<Order> expected = underTest.findByControlNumber(orderMock.getControlNumber());

        //then
        assertThat(expected.isPresent()).isTrue();
        assertThat(expected.get()).isEqualTo(orderMock);
    }

    @Test
    void itShouldNotFindByControlNumber() {
        //given
        Long controlNumber = 2121L;

        //when
        Optional<Order> expected = underTest.findByControlNumber(controlNumber);

        //then
        assertThat(expected.isPresent()).isFalse();
    }

    @Test
    void itShouldFindByRegisterDate() {
        //given
        underTest.save(orderMock);

        //when
        List<Order> expected = underTest.findByRegisterDate(orderMock.getRegisterDate().toLocalDate());

        //then
        assertThat(!expected.isEmpty()).isTrue();
        assertThat(expected.get(0)).isEqualTo(orderMock);
    }

    @Test
    void itShouldNotFindByRegisterDate() {
        //given
        LocalDate date = LocalDate.now();

        //when
        List<Order> expected = underTest.findByRegisterDate(date);

        //then
        assertThat(!expected.isEmpty()).isFalse();
    }
}