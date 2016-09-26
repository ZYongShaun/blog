package com.shaun.blog.frame;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-10
 * Time: 上午1:23
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name="BaseBean")
public class BaseBean {

    private static final long serialVersionUID = -8477857085568630631L;

    private Date createTime;

    private Date updateTime;

    public BaseBean() {
    }

    public boolean check(Map clazzs) {
        try {
            Field newField[] = getClass().getDeclaredFields();
            for (int i = 0; i < newField.length; i++) {
                Field field = newField[i];
                String fieldName = field.getName();
                int mod = field.getModifiers();
                if (!"serialVersionUID".equals(fieldName)
                        && !Modifier.isAbstract(mod) && !Modifier.isFinal(mod)
                        && !Modifier.isStatic(mod)) {
                    fieldName = (new StringBuilder(String.valueOf(Character
                            .toUpperCase(fieldName.charAt(0))))).append(
                            fieldName.substring(1)).toString();
                    Method getMethod = getClass().getMethod(
                            (new StringBuilder("get")).append(fieldName)
                                    .toString(), null);
                    if (getMethod.invoke(this, null) == null) {
                        Class clazz = field.getType();
                        Method setMethod = getClass().getMethod(
                                (new StringBuilder("set")).append(fieldName)
                                        .toString(), new Class[] { clazz });
                        String className = clazz.getName();
                        Object obj = clazzs.get(className);
                        if (obj != null)
                            setMethod.invoke(this, new Object[] { obj });
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean check() {
        try {
            Field newField[] = getClass().getDeclaredFields();
            for (int i = 0; i < newField.length; i++) {
                Field field = newField[i];
                String fieldName = field.getName();
                int mod = field.getModifiers();
                if (!"serialVersionUID".equals(fieldName)
                        && !Modifier.isAbstract(mod) && !Modifier.isFinal(mod)
                        && !Modifier.isStatic(mod)) {
                    fieldName = (new StringBuilder(String.valueOf(Character
                            .toUpperCase(fieldName.charAt(0))))).append(
                            fieldName.substring(1)).toString();
                    Method getMethod = getClass().getMethod(
                            (new StringBuilder("get")).append(fieldName)
                                    .toString(), null);
                    if (getMethod.invoke(this, null) == null) {
                        Class clazz = field.getType();
                        Method setMethod = getClass().getMethod(
                                (new StringBuilder("set")).append(fieldName)
                                        .toString(), new Class[] { clazz });
                        if (clazz.isInstance(new String()))
                            setMethod.invoke(this, new Object[] { "" });
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        try {
            strBuff.append((new StringBuilder("classname (")).append(
                    getClass().getName()).append(")").toString());
            Field newField[] = getClass().getDeclaredFields();
            for (int i = 0; i < newField.length; i++) {
                String fieldName = newField[i].getName();
                int mod = newField[i].getModifiers();
                if (!"serialVersionUID".equals(fieldName)
                        && !Modifier.isAbstract(mod) && !Modifier.isFinal(mod)
                        && !Modifier.isStatic(mod)) {
                    fieldName = (new StringBuilder(String.valueOf(Character
                            .toUpperCase(fieldName.charAt(0))))).append(
                            fieldName.substring(1)).toString();
                    Method method = getClass().getMethod(
                            (new StringBuilder("get")).append(fieldName)
                                    .toString(), null);
                    strBuff
                            .append((new StringBuilder(String
                                    .valueOf(fieldName))).append(" (").append(
                                    method.invoke(this, null)).append(")")
                                    .toString());
                }
            }

        } catch (Exception x) {
            strBuff.append(x.getMessage());
        }
        return strBuff.toString();
    }

    public String toXML() {
        StringBuffer strBuff = new StringBuffer();
        String className = getClass().getName();
        String nameArray[] = className.split("\\.");
        String name = nameArray[nameArray.length - 1];
        try {
            strBuff.append((new StringBuilder("<")).append(name).toString());
            Field newField[] = getClass().getDeclaredFields();
            for (int i = 0; i < newField.length; i++) {
                String fieldName = newField[i].getName();
                int mod = newField[i].getModifiers();
                if (!"serialVersionUID".equals(fieldName)
                        && !Modifier.isAbstract(mod) && !Modifier.isFinal(mod)
                        && !Modifier.isStatic(mod)) {
                    strBuff.append((new StringBuilder(" ")).append(fieldName)
                            .append("=\"").toString());
                    fieldName = (new StringBuilder(String.valueOf(Character
                            .toUpperCase(fieldName.charAt(0))))).append(
                            fieldName.substring(1)).toString();
                    Method method = getClass().getMethod(
                            (new StringBuilder("get")).append(fieldName)
                                    .toString(), null);
                    strBuff.append(method.invoke(this, null));
                    strBuff.append("\"");
                }
            }

            strBuff.append((new StringBuilder("></")).append(name).append(">")
                    .toString());
        } catch (Exception x) {
            strBuff.append(x.getMessage());
        }
        return strBuff.toString();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
