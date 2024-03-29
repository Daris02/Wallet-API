package com.wallet.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Currency {
    private Integer id;
    private String name;
    private String code;
    
    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
