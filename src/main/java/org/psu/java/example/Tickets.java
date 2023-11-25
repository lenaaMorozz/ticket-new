package org.psu.java.example;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.psu.java.example.application.FortunateTicketService;
import org.psu.java.example.domain.Ticket;
import org.psu.java.example.infrastructure.TicketGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


/**
 * Выводит количество шестизначных счастливых билетов
 */
@Slf4j
@SpringBootApplication
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ComponentScan("org.psu.java.example.context")
public class Tickets implements CommandLineRunner {
    FortunateTicketService fortunateTicketServiceForEven;
    FortunateTicketService fortunateTicketServiceForMultipleOfFive;
    ApplicationContext context;

    public Tickets(@Qualifier("getFortunateTicketServiceForEven") FortunateTicketService fortunateTicketServiceForEven,
                   @Qualifier("getFortunateTicketServiceForMultipleOfFive") FortunateTicketService fortunateTicketServiceForMultipleOfFive,
                   ApplicationContext context
    ) {
        this.fortunateTicketServiceForEven = fortunateTicketServiceForEven;
        this.fortunateTicketServiceForMultipleOfFive = fortunateTicketServiceForMultipleOfFive;
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Tickets.class);
    }

    @Override
    public void run(String... args) {
        log.info("Hello world Spring Boot style!");
//        var serviceForRecordTicketGenerator = context.getBean(FortunateTicketService.class);
        var recordTicketGenerator = context.getBean("recordTicketGenerator", TicketGenerator.class);

        var count = fortunateTicketServiceForEven.count(recordTicketGenerator.getTickets());
        log.info(String.valueOf(count));

//        self.calculate("recordTicketGenerator");
//        self.calculate("recordTicketGenerator");
//
//        ticketGenerator
//                .stream()
//                .map(TicketGenerator::getTickets)
//                .mapToInt(fortunateTicketService::count)
//                .mapToObj(String::valueOf)
//                .forEach(log::info);

        var eightDigitsTicketGenerator = context.getBean("eightDigitsTicketGenerator", TicketGenerator.class);
//        var serviceForEightDigitsTicketGenerator = context.getBean(FortunateTicketService.class);

        var eightDigitsTicketCount = fortunateTicketServiceForEven.count(eightDigitsTicketGenerator.getTickets());
        log.info(String.valueOf(eightDigitsTicketCount));

        var fourDigitsTicketGenerator = context.getBean("fourDigitsTicketGenerator", TicketGenerator.class);
        var fourDigitsTicketCount = fortunateTicketServiceForMultipleOfFive.count(fourDigitsTicketGenerator.getTickets());
        log.info(String.valueOf(fourDigitsTicketCount));
    }

//    private void calculate(String generatorName) {
//        var serviceForRecordTicketGenerator = context.getBean(FortunateTicketService.class);
//        var recordTicketGenerator = context.getBean(generatorName, TicketGenerator.class);
//
//        var count = serviceForRecordTicketGenerator.count(recordTicketGenerator.getTickets());
//        log.info(String.valueOf(count));
//    }
}