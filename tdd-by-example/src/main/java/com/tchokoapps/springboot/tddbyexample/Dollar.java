package com.tchokoapps.springboot.tddbyexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dollar {

    private int amount;

    public int times(int multiplier) {
        return amount * multiplier;
    }
}
