package com.httphunt.test;

import com.httphunt.utlity.ObjUtil;
import org.junit.Assert;

public class Tester {
    public static Object test(CodeBlock block, Object expectedOutput){
        Object actualOutput = null;
        try{
            actualOutput = block.run();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), ObjUtil.getJson(actualOutput));
        }catch (Exception e){
            actualOutput = e;
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), e.toString());
        }
        return actualOutput;
    }
}
