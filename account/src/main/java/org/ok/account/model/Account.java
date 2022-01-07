package org.ok.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ok.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Account extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 64)
    @NotBlank
    @Getter
    private String symbol;

    @NotNull
    @Size(min = 1, max = 64)
    @NotBlank
    @Getter
    private String name;

    @NotNull
    @Size(min = 1, max = 64)
    @NotBlank
    @Getter
    private String sector;

    public Account(Long id, String symbol, String name, String sector) {
        super(id);
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
    }

    public Account(String symbol, String name, String sector) {
        this(null, symbol,name, sector);
    }
}
