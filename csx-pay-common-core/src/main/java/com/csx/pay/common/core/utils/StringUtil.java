package com.csx.pay.common.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author csx
 * @Package com.csx.pay.common.core.utils
 * @Description: 字符串工具类
 * @date 2018/5/30 0030
 */
public class StringUtil {
    private static final Logger LOGGER=LoggerFactory.getLogger(StringUtil.class);

    /**
     * 私有化构造方法，将该类设置成单例模式
     */
    private StringUtil(){
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return null==str||"".equals(str);
    }

    /**
     * 判断对象数组是否为空
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object[] objects){
        return null==objects||0==objects.length;
    }

    /**
     * 判断对象是否为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object){
        if(null==object){
            return true;
        }

        if(object instanceof String){
            return ((String)object).trim().isEmpty();
        }

        return !(object instanceof Number)?false:false;
    }

    /**
     * 判断集合是否为空
     * @param objects
     * @return
     */
    public static boolean isEmpty(List<?> objects){
        return null==objects||objects.isEmpty();
    }

    /**
     * map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        return null==map||map.isEmpty();
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public static String getExt(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    /**
     * 获取去掉横线的32位的UUID
     * @return
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获取不去掉横线的36位UUID
     * @return
     */
    public static String get36UUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(StringUtil.isEmpty(str)){
            return false;
        }else{
            return str.matches("\\d*");
        }
    }

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     * @param content
     * @return
     */
    public static int getByteSize(String content){
        int size=0;
        if(null!=content){
            try {
                size=content.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e.getMessage(),e);
            }
        }
        return size;
    }

    /**
     * 截取字符串拼接in查询参数
     * @param param
     * @return
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }

}
