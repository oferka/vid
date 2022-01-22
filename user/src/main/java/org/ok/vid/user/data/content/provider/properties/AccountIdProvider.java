package org.ok.vid.user.data.content.provider.properties;

import javax.validation.constraints.NotNull;

public interface AccountIdProvider {

    @NotNull Long get();
}
