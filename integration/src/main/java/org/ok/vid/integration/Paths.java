package org.ok.vid.integration;

import static org.ok.vid.integration.ServiceNames.*;

public interface Paths {

    String BASE_PATH = "api/rest/";

    String COUNT_PATH = "/count";

    String RANDOM_PATH = "/random";

    String RANDOM_ID_PATH = RANDOM_PATH + "/id";

    String ACCOUNT_PATH = BASE_PATH + ACCOUNT_SERVICE_NAME;

    String USER_PATH = BASE_PATH + USER_SERVICE_NAME;

    String SERVICE_REGISTRY_MAPPER_PATH = BASE_PATH + SERVICE_REGISTRY_MAPPER_SERVICE_NAME;
}
