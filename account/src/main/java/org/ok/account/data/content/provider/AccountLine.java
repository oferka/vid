package org.ok.account.data.content.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountLine {

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
}
