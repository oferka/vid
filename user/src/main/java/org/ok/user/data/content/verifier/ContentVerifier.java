package org.ok.user.data.content.verifier;

import lombok.extern.slf4j.Slf4j;
import org.ok.user.model.User;
import org.ok.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContentVerifier {

    private final UserService userService;

    public ContentVerifier(UserService userService) {
        this.userService = userService;
    }

    public Iterable<User> findLoaded(Iterable<User> users) {
        List<User> result = new ArrayList<>();
        for(User user : users) {
            if(userService.exists(user)) {
                result.add(user);
            }
        }
        log.info("{} users found as loaded", result.size());
        return result;
    }

    public Iterable<User> findNotLoaded(Iterable<User> users) {
        List<User> result = new ArrayList<>();
        for(User user : users) {
            if(!userService.exists(user)) {
                result.add(user);
            }
        }
        log.info("{} users found as unloaded", result.size());
        return result;
    }
}
