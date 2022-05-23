package org.fwx.hbase.utils;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * hbase 数据操作工具类
 * 数据的增、删、改、查操作
 */
public class HBaseDataUtil {

    private static Logger logger = LoggerFactory.getLogger(HBaseDataUtil.class);

    /**
     * 获取 table 对象
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @return
     * @throws IOException
     */
    public static Table getTable(Connection connection, String nameSpace, String tableName) throws IOException {
        TableName tableNameObj = HBaseTableUtil.checkTableName(nameSpace, tableName);
        if (tableNameObj == null) {
            return null;
        }

        // 检查表是否存在,如果不存在直接返回
        if (!HBaseTableUtil.tableExists(connection, nameSpace, tableName)) {
            logger.error("Tabel " + nameSpace + ":" + tableName + " not exist !");
            return null;
        }

        Table table = connection.getTable(tableNameObj);
        return table;
    }

    /**
     * put 数据入 hbase
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @param rowKey     行键
     * @param cf         列族名
     * @param cq         列名
     * @param value      值
     * @throws IOException
     */
    public static void putData(Connection connection, String nameSpace, String tableName, String rowKey, String cf, String cq, String value) throws IOException {
        Table table = getTable(connection, nameSpace, tableName);
        if (table == null) {
            logger.error("get table object error! NameSpace:" + nameSpace + "TableName:" + tableName);
            return;
        }

        // 使用 hbase 提供的 Bytes 工具类，将基本类型数据 和 byte数组相互转换
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cq), Bytes.toBytes(value));

        table.put(put);

        // 关闭 table
        table.close();
        logger.info("Tabel " + nameSpace + ":" + tableName + " put data SUCCESS !");
    }

    /**
     * 根据 rowKey 查询数据
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @param rowKey
     * @return
     * @throws IOException
     */
    public static String getOne(Connection connection, String nameSpace, String tableName, String rowKey) throws IOException {
        Table table = getTable(connection, nameSpace, tableName);
        if (table == null) {
            logger.error("get table object error! NameSpace:" + nameSpace + "TableName:" + tableName);
            return null;
        }

        StringBuilder dataSB = new StringBuilder();

        Get get = new Get(Bytes.toBytes(rowKey));

        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        // 使用 hbase 提供的 CellUtil 工具类，获取 cell 中的列族、列名、值等
        for (Cell cell : cells) {
            dataSB.append(Bytes.toString(CellUtil.cloneFamily(cell)) + ":");
            dataSB.append(Bytes.toString(CellUtil.cloneQualifier(cell)));
            dataSB.append("{" + Bytes.toString(CellUtil.cloneValue(cell)) + "}\r\n");
        }

        table.close();
        return dataSB.toString();
    }

    /**
     * scan 查询数据
     *
     * @param connection
     * @param nameSpace
     * @param tableName
     * @return
     * @throws IOException
     */
    public static List<String> scan(Connection connection, String nameSpace, String tableName) throws IOException {
        Table table = getTable(connection, nameSpace, tableName);
        if (table == null) {
            logger.error("get table object error! NameSpace:" + nameSpace + "TableName:" + tableName);
            return null;
        }

        ArrayList<String> dataList = new ArrayList<>();
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            StringBuilder dataSB = new StringBuilder();
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                dataSB.append(Bytes.toString(CellUtil.cloneFamily(cell)) + ":");
                dataSB.append(Bytes.toString(CellUtil.cloneQualifier(cell)));
                dataSB.append("{" + Bytes.toString(CellUtil.cloneValue(cell)) + "}\r\n");
            }
            dataList.add(dataSB.toString());
        }
        table.close();
        return dataList;
    }

    /**
     * 根据 rowKey 删除一条数据
     * @param connection
     * @param nameSpace
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void deleteOne(Connection connection,String nameSpace,String tableName,String rowKey) throws IOException {
        Table table = getTable(connection, nameSpace, tableName);
        if (table == null) {
            logger.error("get table object error! NameSpace:" + nameSpace + "TableName:" + tableName);
            return;
        }

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 只删除最新的一个版本
        // delete.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("name"));
        // 删除所有版本
        // delete.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("name"));

        table.delete(delete);
        table.close();
        logger.info("Tabel " + nameSpace + ":" + tableName + " delete data SUCCESS !");
    }
}
