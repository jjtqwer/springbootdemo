package com.example.springbootdemo.util;


import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 验空Utils
 */
@Slf4j
public class CommonFunctions {
    /**
     * 判空方法
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null || object.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判空方法
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isExists(String cons, String variy) {
        if (!isEmpty(cons) && !isEmpty(variy)) {
            String[] conStrings = cons.split(",");
            if (!isEmpty(conStrings)) {
                for (int i = 0; i < conStrings.length; i++) {
                    if (variy.equals(conStrings[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判空方法
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        if (object == null || object.toString().trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判空方法
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 以一个""来替换空值
     *
     * @param str
     * @return
     */
    public static String nvl(String str) {
        if (null == str || "".equals(str.trim())) {
            return "";
        } else {
            return str.trim();
        }
    }

    /**
     * BigDecimal转字符
     */
    public static String toBigToSting(BigDecimal big) {
        return isNotEmpty(big) ? big.toString() : "";
    }

    /*** String转BigDecimal */
    public static BigDecimal toStrToBigDecimal(String str) {
        return CommonFunctions.isNotEmpty(str) ? new BigDecimal(str) : null;
    }

    /**
     * 对象克隆方法
     *
     * @param bean
     * @return
     */
    public static Object getCloneObject(Object bean) {
        Object cloneBean = null;
        try {
            ByteArrayOutputStream byout = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(byout);
            obj.writeObject(bean);
            ByteArrayInputStream byin = new ByteArrayInputStream(byout.toByteArray());
            ObjectInputStream ins = new ObjectInputStream(byin);
            cloneBean = (Object) ins.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cloneBean;
    }

    /**
     * 对象转byte
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            log.error("对象转byte" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }
    /**
     * byte转对象
     * @param bytes
     * @param obj
     * @return
     */
    public static Object ByteToObject(Object obj, byte[] bytes) {
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = (Object) oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            log.error("byte转对象" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

}
