package com.cranberries.user.utils;

import com.alibaba.fastjson.JSON;
import com.cranberries.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.annotation.Aspect;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.util.*;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/11/11 15:24
 * @description ：对象比较工具类
 */

@Slf4j
@Aspect
public class BeanCompareUtils {

    private static final String objectName = "用户信息：";

    public static boolean compareBeans(Object oldObject, Object newObject) {
        log.info("比较对象接口请求参数，旧对象：{},新对象：{}", JSON.toJSONString(oldObject), JSON.toJSONString(newObject));

        Map<String, Map<String, Object>> resultMap = compareFields(oldObject, newObject);

        StringBuilder sb = new StringBuilder();
        sb.append(objectName);
        if (resultMap.size() > 0) {
            Iterator<String> it = resultMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                sb.append(key + "由" + resultMap.get(key).get("oldValue") + "被改为" + resultMap.get(key).get("oldValue") + ",");
            }
            System.out.println(sb.toString());
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Map<String, Object>> compareFields(Object oldObject, Object newObject) {
        Map<String, Map<String, Object>> map = null;

        try {
            /**
             * 只有两个对象都是同一类型的才有可比性
             */
            if (oldObject.getClass() == newObject.getClass()) {
                map = new HashMap<String, Map<String, Object>>();

                Class clazz = oldObject.getClass();
                //获取object的所有属性
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {
                    //遍历获取属性名
                    String name = pd.getName();

                    //获取属性的get方法
                    Method readMethod = pd.getReadMethod();

                    // 在oldObject上调用get方法等同于获得oldObject的属性值
                    Object oldValue = readMethod.invoke(oldObject);
                    // 在newObject上调用get方法等同于获得newObject的属性值
                    Object newValue = readMethod.invoke(newObject);

                    if (oldValue instanceof List) {
                        continue;
                    }

                    if (newValue instanceof List) {
                        continue;
                    }

                    if (oldValue instanceof Timestamp) {
                        oldValue = new Date(String.valueOf(((Timestamp) oldValue).getTimestamp()));
                    }

                    if (newValue instanceof Timestamp) {
                        newValue = new Date(String.valueOf(((Timestamp) newValue).getTimestamp()));
                    }

                    if (oldValue == null && newValue == null) {
                        continue;
                    } else if (oldValue == null && newValue != null) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

                        map.put(name, valueMap);

                        continue;
                    }

                    if (!oldValue.equals(newValue)) {// 比较这两个值是否相等,不等就可以放入map了
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

                        map.put(name, valueMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }
}
