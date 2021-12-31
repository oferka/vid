package org.ok.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    @Getter
    private String symbol;

    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    @Getter
    private String name;

    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    @Getter
    private String sector;

    public Account(String symbol, String name, String sector) {
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
    }
}
