package com.leo.everyday.networklibrary.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 作者：Leo on 2018/3/15 12:28
 * <p>
 * 描述：对象序列化和反序列化的工具类
 */
public class ObjectUtil {
    /**
     * 序列化对象
     *
     * @param obj
     * @throws IOException
     */
    public static byte[] serializeObj(Object obj) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(baos);
        oo.writeObject(obj);
        byte[] b = baos.toByteArray();
        oo.flush();
        oo.close();
        baos.close();
        // return new String(b, "UTF-8");
        return b;

    }

    /**
     * 反序列化对象
     *
     * @return
     */
    public static Object deserializeObj(byte[] data) throws Exception {
        ByteArrayInputStream baos = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(baos);
        Object obj = ois.readObject();
        ois.close();
        baos.close();
        return obj;
    }

}
