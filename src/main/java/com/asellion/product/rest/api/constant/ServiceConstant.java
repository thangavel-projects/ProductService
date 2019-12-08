package com.asellion.product.rest.api.constant;

public enum ServiceConstant {

    CONSOLE_URL_PATTERN("/console/**"),
    ALL_REQUEST_URL_PATTERN("/*"),
    INSTANCE_URL_PATTERN("/instances/**"),
    ACTUATOR_URL_PATTERN("/actuator/**"),
    AUTHENTICATE_URL_PATTERN("/authenticate"),
    H2_URL_PATTERN("/h2/**"),
    VIEW_URL_PATTERN("/view/**"),
    HEALTH_CHECK_DISABLED("disable");

    private String value;

    ServiceConstant(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
