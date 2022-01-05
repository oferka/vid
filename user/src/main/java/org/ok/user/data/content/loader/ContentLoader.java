package org.ok.user.data.content.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.ok.user.data.content.provider.ContentProvider;
import org.ok.user.data.content.verifier.ContentVerifier;
import org.ok.user.model.User;
import org.ok.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class ContentLoader {

    private final ContentProvider contentProvider;
    private final ContentVerifier contentVerifier;
    private final UserService userService;

    public ContentLoader(ContentProvider contentProvider, ContentVerifier contentVerifier, UserService userService) {
        this.contentProvider = contentProvider;
        this.contentVerifier = contentVerifier;
        this.userService = userService;
    }

    public @NotNull Iterable<User> ensureContentLoaded() {
        List<User> content = contentProvider.get(500);
        Iterable<User> unloadedContent = contentVerifier.findNotLoaded(content);
        if(!IterableUtils.isEmpty(unloadedContent)) {
            Iterable<User> saved = userService.saveAll(unloadedContent);
            log.info("{} users saved", IterableUtils.size(saved));
        }
        log.info("{} users ensured loaded", content.size());
        return content;
    }
}
