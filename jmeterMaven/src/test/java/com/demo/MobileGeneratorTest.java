package com.demo;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.FunctionTestHelper;
import org.apache.jmeter.functions.MobileGenerator;
import org.junit.Test;

import java.util.Collection;

/**
 * @创建人 anjie
 * @创建时间 2019/1/24
 * @描述
 */
public class MobileGeneratorTest {
    @Test
    public void test() throws Exception {
        MobileGenerator mobileGenerator = new MobileGenerator();
        Collection<CompoundVariable> params = FunctionTestHelper.makeParams("mobile","mobile",null);
        mobileGenerator.setParameters(params);
        String string = mobileGenerator.execute();
        System.out.println(string);
    }
}
