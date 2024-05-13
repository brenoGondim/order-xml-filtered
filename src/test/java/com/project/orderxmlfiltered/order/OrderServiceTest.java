package com.project.orderxmlfiltered.order;

import com.project.orderxmlfiltered.exception.AlreadyExistsException;
import com.project.orderxmlfiltered.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private IOrderRepository orderRepository;
    private OrderService underTest;
    Order orderMock;

    @BeforeEach
    void setUp() {
        underTest = new OrderService(orderRepository);
    }

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
    void itShouldGetAll() {
        //when
        underTest.getAll(null);
        //then
        verify(orderRepository).findAll();
    }

    @Test
    void itShouldGetAllByDate() {
        //given
        LocalDate date = LocalDate.now();
        //when
        underTest.getAll(date);
        //then
        verify(orderRepository).findByRegisterDate(date);
    }

    @Test
    void itShouldGetById() {
        //given
        given(orderRepository.findById(orderMock.getId()))
                .willReturn(Optional.ofNullable(orderMock));
        //when
        underTest.getById(orderMock.getId());
        //then
        verify(orderRepository).findById(orderMock.getId());
    }

    @Test
    void willThrownWhenIdDontExists() {
        //given
        //when

        //then
        assertThatThrownBy(() -> underTest.getById(orderMock.getId()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(MessageFormat.format("{0} {1} not found", "order.id", orderMock.getId()));
    }

    @Test
    void canRegister() {
        //given
        List<Order> ordersMock = Collections.singletonList(orderMock);
        //when
        underTest.register(ordersMock);

        //then
        ArgumentCaptor<List<Order>> userArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(orderRepository).saveAll(userArgumentCaptor.capture());

        List<Order> capturedOrder = userArgumentCaptor.getValue();
        assertThat(capturedOrder).isEqualTo(ordersMock);
    }

    @Test
    void willThrownWhenControlNumberIsTakenOnRegister() {
        //given
        given(orderRepository.findByControlNumber(orderMock.getControlNumber()))
                .willReturn(Optional.ofNullable(orderMock));

        //when
        //then
        assertThatThrownBy(() -> underTest.register(Collections.singletonList(orderMock)))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage(MessageFormat.format(
                        "{0} {1} already exists",
                        "order.controlNumber",
                        String.valueOf(orderMock.getControlNumber())
                ));

        verify(orderRepository, never()).saveAll(any());
    }
}