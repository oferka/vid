package org.ok.account.model;

import lombok.*;

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
    private String id;

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
}
