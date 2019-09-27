package com.admxj.real.base.impl;

import com.admxj.real.base.StartMap;
import com.admxj.real.base.StartParam;
import com.admxj.real.mvc.load.LoadController;

/**
 * @author admxj
 * @version Id: StartController, v 0.1 2019-09-27 02:23 admxj Exp $
 */
public class StartController implements StartMap {
    @Override
    public void load(StartParam startParam) throws Exception {
        LoadController.loadController();
    }
}
