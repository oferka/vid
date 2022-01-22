package org.ok.vid.integration;

public interface Paths {

    String BASE_PATH = "api/rest";

    String COUNT_PATH = "/count";

    String RANDOM_PATH = "/random";

    String RANDOM_ID_PATH = RANDOM_PATH + "/id";

    String ACCOUNT_PATH = BASE_PATH + "/" + ServiceNames.ACCOUNT_SERVICE_NAME;

    String USER_PATH = BASE_PATH + "/" + ServiceNames.USER_SERVICE_NAME;
}
