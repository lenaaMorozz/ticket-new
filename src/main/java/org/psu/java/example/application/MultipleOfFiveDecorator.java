package org.psu.java.example.application;

import lombok.RequiredArgsConstructor;
import org.psu.java.example.domain.Ticket;

@RequiredArgsConstructor
public class MultipleOfFiveDecorator implements Ticket {
    private final Ticket ticket;

    @Override
    public boolean isFortunate() {
        return ticket.getNumber() % 5 == 0 && ticket.isFortunate();
    }

    @Override
    public int getLength() {
        return ticket.getLength();
    }

    @Override
    public long getNumber() {
        return ticket.getNumber();
    }

}
