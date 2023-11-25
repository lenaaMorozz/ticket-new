package org.psu.java.example.context;

import org.psu.java.example.application.EvenDecorator;
import org.psu.java.example.application.FortunateTicketService;
import org.psu.java.example.application.FortunateTicketStreamImpl;
import org.psu.java.example.application.MultipleOfFiveDecorator;
import org.psu.java.example.domain.Ticket;
import org.psu.java.example.infrastructure.TicketImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.UnaryOperator;

/**
 * Конфигурация
 */
@Configuration
@ComponentScan(basePackageClasses = TicketImpl.class)
public class Config {
    @Bean
    @Scope("prototype")
    FortunateTicketService getFortunateTicketServiceForEven(@Qualifier("evenDecorator") UnaryOperator<Ticket> decorator) {
        return new FortunateTicketStreamImpl(decorator);
    }

    @Bean
    @Scope("prototype")
    FortunateTicketService getFortunateTicketServiceForMultipleOfFive(@Qualifier("MultipleOfFiveDecorator") UnaryOperator<Ticket> decorator) {
        return new FortunateTicketStreamImpl(decorator);
    }

    @Bean("evenDecorator")
    UnaryOperator<Ticket> getEvenDecorator() {
        return EvenDecorator::new;
    }

    @Bean("MultipleOfFiveDecorator")
    UnaryOperator<Ticket> getMultipleOfFiveDecorator() {
        return MultipleOfFiveDecorator::new;
    }

}
