package org.ok.controller;

import static org.ok.controller.ServiceNames.ACCOUNT_SERVICE_NAME;
import static org.ok.controller.ServiceNames.USER_SERVICE_NAME;

public interface ServiceAddresses {

    String PREFIX = "http://";

    String ACCOUNT_SERVICE_ADDRESS = PREFIX + ACCOUNT_SERVICE_NAME + "/";

    String USER_SERVICE_ADDRESS = PREFIX + USER_SERVICE_NAME + "/";
}
