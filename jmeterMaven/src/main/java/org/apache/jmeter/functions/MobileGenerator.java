package org.apache.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterVariables;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @创建人 anjie
 * @创建时间 2019/1/24
 * @描述  传入俩个变量，生成俩个手机号
 */
public class MobileGenerator extends AbstractFunction {

    private static final List<String> desc = new LinkedList<String>();

    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
            .split(",");

    private static final String KEY="__MobileGenerator";

    static {
        desc.add("Name of variable in which to store the result1 (optional1)");
        desc.add("Name of variable in which to store the result2 (optional2)");

    }

    private CompoundVariable[] varNames=new CompoundVariable[2];

    //生成手机号方法
    public String mobileGenerator(){
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        String mobile = first + second + thrid;
        return mobile;
    }
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("=====execute======");
        JMeterVariables vars = getVariables();
        //将手机号复制给传入参数
        if (varNames != null) {
            for (CompoundVariable compoundVariable:
            varNames) {
                final String varTrim=compoundVariable.execute().trim();
                if (vars != null&&varTrim.length() > 0) {// vars will be null
                    vars.put(varTrim, mobileGenerator());
                }
            }

        }
        return mobileGenerator();
    }

    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        System.out.println("=====setParameters======");
        //校验参数个数  checkParameterCount(Collection<CompoundVariable> parameters, int min, int  max)
        checkParameterCount(collection,0,2);
        //将值存入变量中
        Object[] values = collection.toArray();
        if (values.length > 0) {
            varNames[0] = (CompoundVariable) values[0];
            varNames[1] = (CompoundVariable) values[1];

        } else {
            varNames = null;
        }

    }

    public String getReferenceKey() {
        System.out.println("=====getReferenceKey======");
        return KEY;
    }

    public List<String> getArgumentDesc() {
        System.out.println("=====getArgumentDesc======");
        return desc;
    }
    private static int getNum(int start, int end) {
        return (int) (Math.random() * (end - 1));
    }
}
