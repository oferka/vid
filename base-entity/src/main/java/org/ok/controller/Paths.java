package org.ok.controller;

import static org.ok.controller.ServiceNames.ACCOUNT_SERVICE_NAME;
import static org.ok.controller.ServiceNames.USER_SERVICE_NAME;

public interface Paths {

    String BASE_PATH = "api/rest";

    String COUNT_PATH = "/count";

    String RANDOM_PATH = "/random";

    String RANDOM_ID_PATH = RANDOM_PATH + "/id";

    String ACCOUNT_PATH = BASE_PATH + "/" + ACCOUNT_SERVICE_NAME;

    String USER_PATH = BASE_PATH + "/" + USER_SERVICE_NAME;
}
