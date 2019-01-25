package com.demo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @创建人 anjie
 * @创建时间 2019/1/23
 * @描述
 */
public class Two implements JavaSamplerClient {
    private static final String URL_NAME="url:";
    private static final String DEFAULT_URL="http://www.baidu.com";
    private String inputUrl;
    private String resultData="自定义采样器结果";
    public Arguments getDefaultParameters() {
        System.out.println("hello getDefaultParameters!!!");
        Arguments arguments=new Arguments();
        arguments.addArgument(URL_NAME,DEFAULT_URL);
        return arguments;
    }
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("hello setupTest!!!");
        inputUrl = javaSamplerContext.getParameter(URL_NAME, DEFAULT_URL);
        System.out.println("用户输入的Url："+inputUrl);
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        System.out.println("hello runTest!!!");
        SampleResult result=new SampleResult();
        StringBuffer stringBuffer=new StringBuffer();
        try {
            URL url=new URL(inputUrl);
            URLConnection connection=url.openConnection();
            byte[] buffer=new byte[1024];
            int len;
            //标记开始事务
            result.sampleStart();
            InputStream in =connection.getInputStream();
            while ((len=in.read(buffer))!=-1){
                resultData=new String(buffer,"UTF-8");
                stringBuffer.append(resultData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setResponseData(String.valueOf(stringBuffer),null);
        result.setDataType(SampleResult.TEXT);
        if (DEFAULT_URL.equals(inputUrl)){
            result.setSuccessful(false);
            System.out.println("========no");
        }else {
            System.out.println("========yes");
            result.setSuccessful(true);
        }
        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("hello teardownTest!!!");

    }


}
