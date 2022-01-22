package org.ok.vid.user.data.content.provider;

import org.ok.vid.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ContentProvider {

    @NotNull User get();

    @NotNull List<User> get(int numberOfItems);
}
