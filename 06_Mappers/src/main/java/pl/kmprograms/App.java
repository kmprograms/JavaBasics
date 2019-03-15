package pl.kmprograms;

import org.mapstruct.factory.Mappers;
import pl.kmprograms.ex1.Device;
import pl.kmprograms.ex1.DeviceDto;
import pl.kmprograms.ex1.DeviceMapper;
import pl.kmprograms.ex1.DeviceType;
import pl.kmprograms.ex2.Student;
import pl.kmprograms.ex2.StudentDto;
import pl.kmprograms.ex2.StudentMapper;
import pl.kmprograms.ex3.*;
import pl.kmprograms.ex4.Producer;
import pl.kmprograms.ex4.ProducerDto;
import pl.kmprograms.ex4.ProducerMapper;
import pl.kmprograms.ex5.Company;
import pl.kmprograms.ex5.Worker;
import pl.kmprograms.ex5.WorkerDto;
import pl.kmprograms.ex5.WorkerMapper;
import pl.kmprograms.ex6.Order;
import pl.kmprograms.ex6.OrderDto;
import pl.kmprograms.ex6.OrderMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\n\n\n\n---------------------------- 1 -----------------------------");
        var deviceMapper = Mappers.getMapper(DeviceMapper.class);
        Device device = Device
                .builder()
                .id(1L)
                .name("DEV")
                .power(127.0)
                .price(new BigDecimal("100"))
                .deviceType(DeviceType.A)
                .build();
        DeviceDto deviceDto = deviceMapper.deviceToDeviceDto(device);
        Device device2 = deviceMapper.deviceDtoToDevice(deviceDto);
        System.out.println(device);
        System.out.println(deviceDto);
        System.out.println(device2);

        System.out.println("\n\n\n\n---------------------------- 2 -----------------------------");
        var studentMapper = Mappers.getMapper(StudentMapper.class);
        Student student = Student.builder().id(1L).name("ADAM").age(20).build();
        StudentDto studentDto = studentMapper.studentToStudentDto(student);
        Student student2 = studentMapper.studentDtoToStudent(studentDto);
        System.out.println(student);
        System.out.println(studentDto);
        System.out.println(student2);

        System.out.println("\n\n\n\n---------------------------- 3 -----------------------------");
        var playerMapper = Mappers.getMapper(PlayerMapper.class);
        Stadium stadium1 = Stadium.builder().id(1L).name("S1").build();
        Stadium stadium2 = Stadium.builder().id(2L).name("S2").build();
        Team team = Team.builder().id(10L).name("T1").points(10).build();
        Player player = Player.builder()
                .id(20L)
                .name("P1")
                .goals(100)
                .team(team)
                .stadiums(List.of(stadium1, stadium2))
                .build();
        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);
        Player player2 = playerMapper.playerDtoToPlayer(playerDto);
        System.out.println(player);
        System.out.println(playerDto);
        System.out.println(player2);

        System.out.println("\n\n\n\n---------------------------- 4 -----------------------------");
        var producerMapper = Mappers.getMapper(ProducerMapper.class);
        Producer producer = Producer.builder().id(1L).name("PROD").city("TOKYO").build();
        ProducerDto producerDto = producerMapper.producerToProducerDto(producer);
        Producer producer2 = producerMapper.producerDtoToProducer(producerDto);
        System.out.println(producer);
        System.out.println(producerDto);
        System.out.println(producer2);

        System.out.println("\n\n\n\n---------------------------- 5 -----------------------------");
        var workerMapper = Mappers.getMapper(WorkerMapper.class);
        Worker worker = Worker.builder()
                .id(1L)
                .name("JOHN")
                .hireDate(LocalDate.now())
                .company(Company.builder().id(10L).name("CMP").build())
                .build();
        WorkerDto workerDto = workerMapper.workerToWorkerDto(worker);
        Worker worker2 = workerMapper.workerDtoToWorker(workerDto);
        System.out.println(worker);
        System.out.println(workerDto);
        System.out.println(worker2);

        System.out.println("\n\n\n\n---------------------------- 6 -----------------------------");
        var orderMapper = Mappers.getMapper(OrderMapper.class);
        Order order1 = Order.builder()
                .id(10L)
                .date(LocalDate.now())
                .price(new BigDecimal("100"))
                .quantity(2)
                .build();
        Order order2 = Order.builder()
                .id(11L)
                .date(LocalDate.now().plusDays(1))
                .price(new BigDecimal("200"))
                .quantity(3)
                .build();
        OrderDto orderDto = orderMapper.orderToOrderDto(order1);
        List<Order> orders = List.of(order1, order2);
        List<OrderDto> orders2 = orderMapper.ordersToOrdersDto(orders);
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(orderDto);
        System.out.println(orders);
        System.out.println(orders2);
    }
}
