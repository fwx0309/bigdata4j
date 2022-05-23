package org.fwx.hbase.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.RegionSplitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HBase Table 工具类
 * Table 的增、删、改、查操作
 */
public class HBaseTableUtil {

    private static Logger logger = LoggerFactory.getLogger(HBaseTableUtil.class);

    /**
     * tableName 合规性校验
     *
     * @param nameSpace
     * @param tableName
     * @return
     */
    public static TableName checkTableName(String nameSpace, String tableName) {
        if (StringUtils.isBlank(tableName)) {
            if (StringUtils.isBlank(tableName)) {
                logger.error("TableName " + tableName + " error!");
            } else {
                logger.error("TableName " + nameSpace + ":" + tableName + " error!");
            }
            return null;
        }

        TableName tableNameObj = TableName.valueOf(nameSpace, tableName);
        return tableNameObj;
    }

    /**
     * 判断 HBase Table 是否存在
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean tableExists(Connection connection, String nameSpace, String tableName) throws IOException {
        TableName tableNameObj = checkTableName(nameSpace, tableName);
        if (null == tableNameObj) {
            return false;
        }

        Admin admin = connection.getAdmin();
        boolean exists = admin.tableExists(tableNameObj);

        admin.close();
        return exists;
    }

    /**
     * 创建 table
     *
     * @param connection
     * @param nameSpace    命名空间
     * @param tableName    表名
     * @param columnFamily 可变 String 列族
     * @return
     * @throws IOException
     */
    public static boolean createTable(Connection connection, String nameSpace, String tableName, String... columnFamily) throws IOException {
        TableName tableNameObj = checkTableName(nameSpace, tableName);
        if (null == tableNameObj) {
            return false;
        }

        Admin admin = connection.getAdmin();
        // 创建表描述
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableNameObj);

        // 添加列族描述至表描述中
        for (String cf : columnFamily) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        // 创建表
        admin.createTable(hTableDescriptor);

        admin.close();
        logger.info("Create table '"+nameSpace+":"+tableName+"' SUCCESS!");
        return true;
    }

    /**
     * 用 hbase server 中自带的两个 rowKey 算法预分区创建表
     *
     * @param connection
     * @param splitNumber  分区数
     * @param algo         分区算法类:RegionSplitter.HexStringSplit or RegionSplitter.UniformSplit
     * @param nameSpace    命名空间
     * @param tableName    表名
     * @param columnFamily 可变 String 列族
     * @return
     * @throws IOException
     */
    public static boolean createTableBySplitAlgo(Connection connection, int splitNumber, RegionSplitter.SplitAlgorithm algo, String nameSpace, String tableName, String... columnFamily) throws IOException {
        TableName tableNameObj = checkTableName(nameSpace, tableName);
        if (null == tableNameObj) {
            return false;
        }

        Admin admin = connection.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableNameObj);

        for (String cf : columnFamily) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        admin.createTable(hTableDescriptor, algo.split(splitNumber));

        admin.close();
        logger.info("Create table '"+nameSpace+":"+tableName+"' SUCCESS!");
        return true;
    }

    /**
     * 删除 table
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean deleteTable(Connection connection, String nameSpace, String tableName) throws IOException {
        // 判断表是否存在
        if (!tableExists(connection, nameSpace, tableName)) {
            logger.error("Tabel " + nameSpace + ":" + tableName + " not exist !");
            return false;
        }

        Admin admin = connection.getAdmin();
        TableName tableNameObj = TableName.valueOf(nameSpace, tableName);

        // 禁用表
        admin.disableTable(tableNameObj);
        // 删除表
        admin.deleteTable(tableNameObj);

        admin.close();
        logger.info("Delete table '"+nameSpace+":"+tableName+"' SUCCESS!");
        return true;
    }
}
