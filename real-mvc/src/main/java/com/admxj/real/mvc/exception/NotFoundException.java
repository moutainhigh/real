package com.admxj.real.mvc.exception;

/**
 * @author jin.xiang
 * @version Id: NotFoundException, v 0.1 2019-10-03 02:18 jin.xiang Exp $
 */
public class NotFoundException extends Exception {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
