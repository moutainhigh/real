package com.admxj.real.start;

import com.admxj.real.base.StartLoadListImpl;
import com.admxj.real.start.base.BaseStartReal;

/**
 * @author admxj
 * @version Id: RearStart, v 0.1 2019-09-27 02:28 admxj Exp $
 */
public class RearStart {

    public static void start(Class<?> clazz, String[] args) {

        BaseStartReal.setStartList(StartLoadListImpl.initStartList());

        BaseStartReal.start(clazz);
    }
}
