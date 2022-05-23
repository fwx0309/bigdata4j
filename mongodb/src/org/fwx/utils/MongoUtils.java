package org.fwx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MongoDB 工具类
 *
 */
public class MongoUtils {
    private static final Logger log = LoggerFactory.getLogger(MongoUtils.class);

    private static MongoClient mongoClient;

    public static MongoClient getMongoClient(){
        if(mongoClient == null){
            synchronized (MongoUtils.class){
                if(mongoClient == null){
                    // 加载 mongo.properties
                    InputStream is = MongoUtils.class.getClassLoader().getResourceAsStream("mongo.properties");
                    Properties properties = new Properties();
                    try {
                        properties.load(is);
                    } catch (IOException e) {
                        log.warn("mongo.properties load fail !");
                        e.printStackTrace();
                    }

                    // server
                    ServerAddress serverAddress = new ServerAddress(
                            properties.getProperty("mongo.host").split(":")[0],
                            Integer.parseInt(properties.getProperty("mongo.host").split(":")[1]));

                    // 认证信息
                    List<MongoCredential> credentialsList = new ArrayList<>();
                    MongoCredential credential = MongoCredential.createCredential(
                            properties.getProperty("mongo.username"),
                            properties.getProperty("mongo.dataBase"),
                            properties.getProperty("mongo.password").toCharArray());
                    credentialsList.add(credential);

                    // 设置配置信息
                    MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
                    // 设置每个主机的最大连接数。    #每个主机答应的连接数（每个主机的连接池大小），当连接池被用光时，会被阻塞住 ，默以为10
                    builder.connectionsPerHost(Integer.parseInt(properties.getProperty("mongo.connectionsPerHost")));
                    // 设置连接超时时间。连接超时，推荐>3000毫秒
                    builder.connectTimeout(Integer.parseInt(properties.getProperty("mongo.connectTimeout")));
                    // 套接字超时时间，0无限制
                    builder.socketTimeout(Integer.parseInt(properties.getProperty("mongo.socketTimeout")));
                    // 设置一个线程将阻塞等待连接的最大时间
                    builder.maxWaitTime(Integer.parseInt(properties.getProperty("mongo.maxWaitTime")));
                    // 设置池连接的最大空闲时间
                    builder.maxConnectionIdleTime(Integer.parseInt(properties.getProperty("mongo.maxConnectionIdleTime")));
                    // 设置池连接的最大生存时间 设置连接池最长生命时间
                    builder.maxConnectionLifeTime(Integer.parseInt(properties.getProperty("mongo.maxConnectionLifeTime")));
                    // 设置是否启用socket保持连接 //false 短连接，为true是长连接
                    builder.socketKeepAlive(Boolean.parseBoolean(properties.getProperty("mongo.socketKeepAlive")));
                    // 设置允许阻塞等待连接的线程数的乘数
                    builder.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(properties.getProperty("mongo.threadsAllowedToBlockForConnectionMultiplier")));
                    //builder.writeConcern(WriteConcern.W1);
                    MongoClientOptions build = builder.build();

                    return new MongoClient(serverAddress,credentialsList ,build);
                }
            }
        }
        return mongoClient;
    }

    /**
     * 打印 Document 集合中每条数据的信息
     *      每条数据打印格式：第一行 数据的id，第二行：数据全字段信息
     * @param mongoCursor
     */
    public static void printDocument(MongoCursor<Document> mongoCursor){
        while (mongoCursor.hasNext()){
            Document document = mongoCursor.next();
            String json = document.toJson();

            // 文档id
            JSONObject jsonObject = (JSONObject) JSON.parse(json);
            JSONObject idObject =(JSONObject)JSON.parse(jsonObject.get("_id").toString());
            String id = idObject.getString("$oid");
            System.out.println("id:"+id);

            // 文档json
            System.out.println(jsonObject);

        }
    }

    /**
     * 关闭Mongodb
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
