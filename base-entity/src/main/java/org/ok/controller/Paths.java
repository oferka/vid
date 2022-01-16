package org.ok.controller;

import static org.ok.controller.ServiceNames.ACCOUNT;
import static org.ok.controller.ServiceNames.USER;

public interface Paths {

    String BASE_PATH = "api/rest";

    String COUNT_PATH = "/count";

    String RANDOM_PATH = "/random";

    String RANDOM_ID_PATH = RANDOM_PATH + "/id";

    String ACCOUNT_PATH = BASE_PATH + "/" + ACCOUNT;

    String USER_PATH = BASE_PATH + "/" + USER;
}
