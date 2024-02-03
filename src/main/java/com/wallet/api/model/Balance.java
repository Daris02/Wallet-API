package com.wallet.api.model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Balance {
    private String id;
    private Double value;
    private Timestamp updateDateTime;
    private String accountId;
}
