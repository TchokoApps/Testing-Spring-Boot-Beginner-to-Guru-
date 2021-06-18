package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.exceptions.ValueNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    public void index() {
        Assertions.assertEquals("index", controller.index());
    }

    @Test
    public void oopsHandler_shouldThrowValueNotFoundException() {
        Assertions.assertThrows(ValueNotFoundException.class, () -> controller.oopsHandler());
    }

    @Test
    public void testTimeOut() {
        Assertions.assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(50));
        System.out.println("I got here");
    }

    @Test
    public void testTimeOutPrempt() {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(2000));
        System.out.println("I got here also");
    }

}