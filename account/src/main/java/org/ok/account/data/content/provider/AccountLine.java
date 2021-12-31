package org.ok.account.data.content.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountLine {

    @Getter
    private String symbol;

    @Getter
    private String name;

    @Getter
    private String sector;
}
