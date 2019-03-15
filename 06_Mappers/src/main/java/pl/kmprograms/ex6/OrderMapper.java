package pl.kmprograms.ex6;

import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public abstract class OrderMapper {

    public OrderDto orderToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .month(order.getDate().getMonth().toString())
                .totalPrice(order.getPrice().multiply(new BigDecimal(order.getQuantity())))
                .build();
    }

    public abstract List<OrderDto> ordersToOrdersDto(List<Order> orders);
}
