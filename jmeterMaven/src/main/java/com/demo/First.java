package com.demo;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @创建人 anjie
 * @创建时间 2019/1/23
 * @描述
 */
public class First extends AbstractJavaSamplerClient {
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("hello Jmeter!!!");
        return null;
    }
}
