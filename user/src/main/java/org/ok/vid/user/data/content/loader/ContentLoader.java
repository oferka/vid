package org.ok.vid.user.data.content.loader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.ok.vid.user.data.content.provider.ContentProvider;
import org.ok.vid.user.data.content.provider.ContentProviderConfiguration;
import org.ok.vid.user.data.content.verifier.ContentVerifier;
import org.ok.vid.user.model.User;
import org.ok.vid.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentLoader {

    private final ContentProvider contentProvider;
    private final ContentVerifier contentVerifier;
    private final UserService userService;
    private final ContentProviderConfiguration contentProviderConfiguration;

    public @NotNull Iterable<User> ensureContentLoaded() {
        List<User> content = contentProvider.get(contentProviderConfiguration.getNumberOfItems());
        Iterable<User> unloadedContent = contentVerifier.findNotLoaded(content);
        if(!IterableUtils.isEmpty(unloadedContent)) {
            Iterable<User> saved = userService.saveAll(unloadedContent);
            log.info("{} users saved", IterableUtils.size(saved));
        }
        log.info("{} users ensured loaded", content.size());
        return content;
    }
}
