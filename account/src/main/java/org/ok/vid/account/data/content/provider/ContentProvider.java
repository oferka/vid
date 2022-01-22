package org.ok.vid.account.data.content.provider;

import org.ok.vid.account.model.Account;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ContentProvider {

    @NotNull Account get();

    @NotNull List<Account> get(int numberOfItems);
}
