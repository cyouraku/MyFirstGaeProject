package com.costuary.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.costuary.bean.XmlBean;

public class StringUtil {

	public static String getString(byte[] bytes,String charset){
		String result = "";
		try {
			result = new String(bytes,charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}


	public static boolean checkInputStrLength(String inputStr){
		int length = inputStr.length();
		if(length < 20){
			return true;
		}else{
			return false;
		}
	}

	//for showLastTenRecordsOfTemp.jsp
//    <th data-options="field:'ID',width:80,align:'center'" style="font-size: 16px">序列</th>
//    <th data-options="field:'CPU',width:80,align:'center'" style="font-size: 16px">CPU温度</th>
//    <th data-options="field:'GPU',width:100,align:'center'" style="font-size: 16px">GPU温度</th>
//    <th data-options="field:'FAN(RPM)',width:80,align:'center'" style="font-size: 16px">风扇转速</th>
//    <th data-options="field:'TIMESTAMP',width:80,align:'center'" style="font-size: 16px">时间戳</th>
	public static String[] createJsonStrList(List<XmlBean> beanList){
		int length = beanList.size();
		String[] jsonStr = new String[length];
		XmlBean bean;
		JsonObject value = null;
		if(length != 0){
			for(int i = 0;i < length;i++){
				bean = beanList.get(i);
				value = Json.createObjectBuilder()
					     .add("ID", bean.getId())
					     .add("CPU", bean.getCpu())
					     .add("GPU",bean.getGpu())
					     .add("FAN(RPM)", bean.getFan())
					     .add("TIMESTAMP", bean.getTimestampStr()).build();
				jsonStr[i] = value.toString();
				//Debug:
				System.out.println(jsonStr[i]);
			}
		}
		return jsonStr;
	}

	public static String creatJsonStrNull() {
		JsonObject value = null;
		value = Json.createObjectBuilder().add("ID", "null").add("CPU", "null").add("GPU", "null")
				.add("FAN(RPM)", "null").add("TIMESTAMP", "null").build();
		//Debug:
		System.out.println(value.toString());
		return "[" + value.toString() + "]";
	}
}
