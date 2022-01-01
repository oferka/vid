package org.ok.account.data.content.provider;

import org.ok.account.model.Account;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ContentProvider {

    @NotNull Account get();

    @NotNull List<Account> get(int numberOfItems);
}
