package com.admxj.real.mvc.model.enums;

/**
 * @author jin.xiang
 * @version Id: HttpStatus, v 0.1 2019-10-02 16:55 jin.xiang Exp $
 * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">Wikipedia 对每个状态码的介绍</a>
 */
public enum HttpStatus {

                        /**
                         * {@code 200 OK}.
                         */
                        OK(200, "OK"),

                        BAD_REQUEST(400, "Bad Request"),

                        UNAUTHORIZED(401, "Unauthorized"),

                        FORBIDDEN(403, "Forbidden"),

                        NOT_FOUND(404, "Not Found"),

                        METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

                        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

                        SERVICE_UNAVAILABLE(503, "Service Unavailable"),

                        BAD_GATEWAY(502, "Bad Gateway");

    private final int    value;

    private final String reasonPhrase;

    HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
