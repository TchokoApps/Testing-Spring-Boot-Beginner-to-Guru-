package com.tchokoapps.springboot.tddbyexample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {

    @Test
    public void testMuliplication() {
        // given
        final Dollar dollar = new Dollar(5);
        // when
        final int times = dollar.times(2);
        //then
        assertEquals(10, times);

    }

    @Test
    public void testEquality() {
        assertEquals(new Dollar(3), new Dollar(3));
    }
}
