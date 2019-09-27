package com.admxj.real.core.load;

import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author jin.xiang
 * @version Id: ScanClassTest, v 0.1 2019-09-27 14:01 jin.xiang Exp $
 */

public class ScanClassTest {

    @Test
    public void loadClassList() throws IOException {
        Set<String> strings = ScanClass.loadClassList("com.admxj.real");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
