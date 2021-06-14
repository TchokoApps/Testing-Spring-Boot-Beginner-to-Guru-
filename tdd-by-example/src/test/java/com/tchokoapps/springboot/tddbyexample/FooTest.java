package com.tchokoapps.springboot.tddbyexample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooTest {

    @Test
    public void getBar() {
        Foo foo = new Foo();
        final String bar = foo.getBar();

        assertThat("Bar").isEqualTo(bar);
    }

}