package org.fwx.hbase.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;

/**
 * 操作 HBase NameSpace 的工具类
 * NameSpace 的增、删、改、查操作
 */
public class HBaseNameSpaceUtil {
    private static Logger logger = LoggerFactory.getLogger(HBaseNameSpaceUtil.class);

    /**
     * 获取所有的 namespace
     *
     * @param connection hbase.client
     * @return LinkedList<String>
     * @throws IOException
     */
    public static LinkedList<String> getNameSpaces(Connection connection) throws IOException {
        LinkedList<String> list = new LinkedList<>();

        Admin admin = connection.getAdmin();

        NamespaceDescriptor[] descriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor descriptor : descriptors) {
            list.add(descriptor.getName());
        }

        // 释放 Admin
        admin.close();
        return list;
    }

    /**
     * 判断 NameSpace 是否存在
     *
     * @param connection hbase.client
     * @param nameSpace  命名空间
     * @return boolean
     * @throws IOException
     */
    public static boolean nameSpaceExist(Connection connection, String nameSpace) throws IOException {
        // nameSpace 合规性校验
        if (StringUtils.isBlank(nameSpace)) {
            logger.error("NameSpace " + nameSpace + " error!");
            return false;
        }

        Admin admin = connection.getAdmin();

        try {
            admin.getNamespaceDescriptor(nameSpace);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 释放 Admin
            admin.close();
        }
    }

    /**
     * 创建 nameSpace
     *
     * @param connection
     * @param nameSpace
     * @return
     * @throws IOException
     */
    public static boolean createNameSpace(Connection connection, String nameSpace) throws IOException {
        // nameSpace 合规性校验
        if (StringUtils.isBlank(nameSpace)) {
            logger.error("NameSpace " + nameSpace + " error!");
            return false;
        }

        Admin admin = connection.getAdmin();

        try {
            NamespaceDescriptor.Builder ns1 = NamespaceDescriptor.create(nameSpace);
            NamespaceDescriptor descriptor = ns1.build();
            admin.createNamespace(descriptor);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            admin.close();
        }
    }

    /**
     * 删除 NameSpace
     *
     * @param connection
     * @param nameSpace
     * @return
     * @throws IOException
     */
    public static boolean deleteNameSpace(Connection connection, String nameSpace) throws IOException {
        // nameSpace 合规性校验
        if (StringUtils.isBlank(nameSpace)) {
            logger.error("NameSpace " + nameSpace + " error!");
            return false;
        }

        // 判断表是否存在
        if (!nameSpaceExist(connection, nameSpace)) {
            return false;
        }

        Admin admin = connection.getAdmin();
        HTableDescriptor[] hTableDescriptors = admin.listTableDescriptorsByNamespace(nameSpace);

        // 如果表不为空才能删除 NameSpace
        if (hTableDescriptors.length == 0) {
            admin.deleteNamespace(nameSpace);
            admin.close();
            return true;
        } else {
            logger.warn("NameSpace " + nameSpace + " not null!");
            for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
                logger.info(hTableDescriptor.toString());
            }
            admin.close();
            return false;
        }
    }
}
