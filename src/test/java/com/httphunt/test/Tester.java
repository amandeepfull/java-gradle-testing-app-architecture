package com.httphunt.test;

import com.httphunt.utlity.ObjUtil;
import org.junit.Assert;

import java.util.*;


public interface Tester {
    static <U extends CustomBlock> void testCustom(CodeBlock block1, U block2) {
        block2.run(block1.run());
    }

    static <U extends Number> void testSize(CodeBlock block, U expectedSize) {
        Object actualOutput = null;
        try {
            actualOutput = block.run();
            int size = 0;
            if (actualOutput.getClass() == String.class)
                size = ((String) actualOutput).length();
            if (isCollection(actualOutput)) {
                size = ((Collection) actualOutput).size();
            }
            if (isMap(actualOutput))
                size = ((Map) actualOutput).size();
            Assert.assertEquals(ObjUtil.getJson(expectedSize), ObjUtil.getJson(size));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedSize), e.toString());
        }
    }

    static boolean isMap(Object actualOutput) {
        return actualOutput.getClass() == LinkedHashMap.class || actualOutput.getClass() == HashMap.class
                || actualOutput.getClass() == HashMap.class;
    }

    static boolean isCollection(Object actualOutput) {
        return actualOutput.getClass() == ArrayList.class || actualOutput.getClass() == LinkedList.class
                || actualOutput.getClass() == Vector.class || actualOutput.getClass() == Stack.class
                || actualOutput.getClass() == ArrayDeque.class || actualOutput.getClass() == TreeSet.class
                || actualOutput.getClass() == HashSet.class || actualOutput.getClass() == LinkedHashSet.class;
    }

    static <U extends String> void testString(CodeBlock block, U expectedString) {
        Object actualOutput = null;
        try {
            actualOutput = block.run();
            Assert.assertEquals(ObjUtil.getJson(expectedString), ObjUtil.getJson(actualOutput));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedString), e.toString());
        }
    }

    static <U extends Number> void testNumber(CodeBlock block, U expectedNumber) {
        Object actualOutput = null;
        try {
            actualOutput = block.run();
            Assert.assertEquals(ObjUtil.getJson(expectedNumber), ObjUtil.getJson(actualOutput));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedNumber), e.toString());
        }
    }

    static <U extends Collection> void testCollection(CodeBlock block, U expectedOutput) {
        Object actualOutput = null;
        try {
            actualOutput = block.run();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), ObjUtil.getJson(actualOutput));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), e.toString());
        }
    }

    static <U extends Map> void testMap(CodeBlock block, U expectedOutput) {
        Object actualOutput = null;
        try {
            actualOutput = block.run();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), ObjUtil.getJson(actualOutput));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(ObjUtil.getJson(expectedOutput), e.toString());
        }
    }
}
