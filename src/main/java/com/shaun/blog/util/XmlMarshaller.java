package com.shaun.blog.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by shaun on 2014/4/24.
 */
public class XmlMarshaller {

    /**
     * 序列化xml到本地
     * @param obj
     * @param filePath
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public  synchronized static void marshalXml(Object obj, String filePath)
            throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        Marshaller m = jc.createMarshaller();
        FileOutputStream bos = new FileOutputStream(filePath);
        m.marshal(obj, bos);
    }

    /**
     * 将对象转换为字符串
     * @param obj
     * @return   XML的字符串
     */
    public synchronized static String marshalXml(Object obj) throws JAXBException {
        JAXBContext jc=JAXBContext.newInstance(obj.getClass());
        Marshaller m = jc.createMarshaller();
        Writer writer=new StringWriter();
        m.marshal(obj,writer);
        return writer.toString();
    }


    /**
     * 反序列化xml
     * @param obj
     * @param path
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public synchronized  static Object ummarshalXml(Class<?> obj, String path)throws JAXBException, FileNotFoundException {
        JAXBContext jc = JAXBContext.newInstance(obj);
        Unmarshaller um = jc.createUnmarshaller();
        um.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        InputStream is = new FileInputStream(new File(path));
        return um.unmarshal(is);
    }

    /**
     * 将XML字符串转换为对象
     * @param xml
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    public synchronized  static Object ummarshalFromXml(Class<?> obj,String xml) throws JAXBException, UnsupportedEncodingException {
        JAXBContext jc = JAXBContext.newInstance(obj);
        Unmarshaller um = jc.createUnmarshaller();
        InputStream is=new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return  um.unmarshal(is);
    }

}
