package org.apache.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterVariables;

import java.util.*;

/**
 * @创建人 anjie
 * @创建时间 2019/1/24
 * @描述  不传入变量，生成32位随机数
 */
public class uuid32 extends AbstractFunction {

    public static void main(String[] args) {
        System.out.println(uuidfunction());
    }

    private static final String KEY="__uuid32";

    public static String uuidfunction(){
        Random rand = new Random();
        StringBuffer sb=new StringBuffer();
        sb.append("C85577852B300001162F");
        for (int i=1;i<=12;i++){
            int randNum = rand.nextInt(9)+1;
            String num=randNum+"";
            sb=sb.append(num);
        }
        String random=String.valueOf(sb);

        return random;
    }
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("=====execute======");
        return uuidfunction();
    }

    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        System.out.println("=====setParameters======");

    }

    public String getReferenceKey() {
        System.out.println("=====getReferenceKey======");
        return KEY;
    }

    public List<String> getArgumentDesc() {
        System.out.println("=====getArgumentDesc======");
        return null;
    }
}
