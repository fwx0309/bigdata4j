package org.fwx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * [ Comments ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/1 15:33 ]
 */
public class Text {

    @Test
    public void test() {

        /*JSONObject requestD = new JSONObject();

        JSONObject requestJsonData = new JSONObject();
        requestJsonData.put("id", "0");
        requestJsonData.put("address", "dstElementValue");

        JSONArray requestJsonDataArray = new JSONArray(1);
        requestJsonDataArray.add(requestJsonData);

        requestD.put("data", requestJsonDataArray);*/

        String srcAdd = "。。。碑林莲湖...";

        List<String> xianAddlist = Arrays.asList("莲湖", "碑林", "高新", "新城", "雁塔", "长安", "临潼", "未央", "阎良", "周至", "鄠邑", "高陵", "灞桥", "蓝田", "经开", "西安");

        for (String addKey : xianAddlist) {
            if(srcAdd.contains(addKey)){
                // 请求数据封装
                JSONObject requestD = new JSONObject();

                JSONObject requestJsonData = new JSONObject();
                requestJsonData.put("id", "0");
                requestJsonData.put("address", srcAdd);

                JSONArray requestJsonDataArray = new JSONArray(1);
                requestJsonDataArray.add(requestJsonData);

                requestD.put("data", requestJsonDataArray);

                System.out.println(requestD);
                break;
            }

        }

    }


    @Test
    public void test1() {

        String str = "{\n" +
                "\t\"data\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"address\":\"西安市公安局西大街63\",\n" +
                "\t\t\t\"province\":\"陕西省\",\n" +
                "\t\t\t\"city\":\"西安市\",\n" +
                "\t\t\t\"district\":\"临潼区\",\n" +
                "\t\t\t\"name\":\"陕西省西安市临潼区西大街4号\",\n" +
                "\t\t\t\"lon\":\"109.207061\",\n" +
                "\t\t\t\"id\":\"2\",\n" +
                "\t\t\t\"lat\":\"34.371031\",\n" +
                "\t\t\t\"result_address\":\"陕西省西安市临潼区西大街4号\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"message\":\"数据查询成功!\",\n" +
                "\t\"stateCode\":200\n" +
                "}";


        JSONObject json = (JSONObject) JSONObject.parse(str);

        String stateCode = json.getString("stateCode");

        if(!"200".equals(stateCode)){

        }

        JSONArray cont = (JSONArray) json.get("data");
        JSONObject data = (JSONObject) cont.get(0);

        System.out.println(data.getString("result_address"));
        System.out.println(data.getString("lon"));
        System.out.println(data.getString("lat"));


    }
}
