package com.admxj.real.mvc.model;

import com.admxj.real.mvc.model.enums.HttpStatus;
import lombok.Data;

/**
 * @author jin.xiang
 * @version Id: ModelAndView, v 0.1 2019-10-02 15:38 jin.xiang Exp $
 */
@Data
public class ModelAndView {

    /** View instance or view name String */
    private String     view;

    /** Model Map */
    private ModelMap   model;

    /** Optional HTTP status for the response */
    private HttpStatus status;

    /** Indicates whether or not this instance has been cleared with a call to {@link #clear()} */
    private boolean    cleared = false;

    /**
     * 清除所有状态
     */
    public void clear() {
        this.view = null;
        this.model = null;
        this.cleared = true;
    }

}
