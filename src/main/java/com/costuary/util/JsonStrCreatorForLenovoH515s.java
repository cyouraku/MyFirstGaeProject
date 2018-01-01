package com.costuary.util;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.costuary.bean.ServerHealthInfoBean;
import com.costuary.bean.XmlBean;

public class JsonStrCreatorForLenovoH515s implements IJsonStrCreator {

	//use singleton pattern
	private static JsonStrCreatorForLenovoH515s jsonStrCreatorForDellVostro220s = new JsonStrCreatorForLenovoH515s();
	private JsonStrCreatorForLenovoH515s(){};

	public static JsonStrCreatorForLenovoH515s getInstance(){
		return jsonStrCreatorForDellVostro220s;
	}

	/**
	 * create json string for LenovoH515s
	 */
	public String creatJsonStr(ServerHealthInfoBean bean) {
		JsonObject value = null;
		value = Json.createObjectBuilder().add("seqNo", bean.getRecord_id())
				 .add("cpu_core1", bean.getCpu_teperature())
				 .add("gpu_core1", bean.getGpu_temperature())
//				 .add("cpu_core2", bean.getCpu_teperature2())
				 .add("cpu_fan", bean.getFan_speed())
//				 .add("mtb_fan", bean.getFan_speed2())
//				 .add("serverIp", bean.getServerIp())
			     .add("date",bean.getRecord_timeStamp()).build();
		//Debug:
		System.out.println(value.toString());
		return "[" + value.toString() + "]";
	}

	/**
	 * create json string with null value for LenovoH515s
	 */
	public String creatJsonStrNull() {
		JsonObject value = null;
		value = Json.createObjectBuilder().add("seqNo", "null")
				 .add("cpu_core1","null")
				 .add("gpu_core1", "null")
//				 .add("cpu_core2", "null")
				 .add("cpu_fan", "null")
//				 .add("mtb_fan", "null")
//				 .add("serverIp", "null")
			     .add("date","null").build();
		//Debug:
		System.out.println(value.toString());
		return "[" + value.toString() + "]";
	}

	/**
	 * create json string[] for LenovoH515s
	 */
	public String[] creatJsonStrList(List<ServerHealthInfoBean> beanList) {
		int length = beanList.size();
		String[] jsonStr = new String[length];
		ServerHealthInfoBean bean;
		JsonObject value = null;
		if(length != 0){
			for(int i = 0;i < length;i++){
				bean = beanList.get(i);
				value = Json.createObjectBuilder()
						.add("seqNo", bean.getRecord_id())
						 .add("cpu_core1", bean.getCpu_teperature())
						 .add("gpu_core1", bean.getGpu_temperature())
//						 .add("cpu_core2", bean.getCpu_teperature2())
						 .add("cpu_fan", bean.getFan_speed())
//						 .add("mtb_fan", bean.getFan_speed2())
//						 .add("serverIp", bean.getServerIp())
					     .add("date",bean.getRecord_timeStamp()).build();
				jsonStr[i] = value.toString();
				//Debug:
				System.out.println(jsonStr[i]);
			}
		}
		return jsonStr;
	}

	/**
	 * create json string[] for LenovoH515s from XmlBean list
	 */
	public String[] creatJsonStrListFromXmlBean(List<XmlBean> beanList) {
		int length = beanList.size();
		String[] jsonStr = new String[length];
		XmlBean bean;
		JsonObject value = null;
		if(length != 0){
			for(int i = 0;i < length;i++){
				bean = beanList.get(i);
				value = Json.createObjectBuilder()
						.add("seqNo", bean.getId())
						 .add("cpu_core1", bean.getCpu())
						 .add("gpu_core1", bean.getGpu())
//						 .add("cpu_core2", bean.getCpu_teperature2())
						 .add("cpu_fan", bean.getFan())
//						 .add("mtb_fan", bean.getFan_speed2())
//						 .add("serverIp", bean.getServerIp())
					     .add("date",bean.getTimestampStr()).build();
				jsonStr[i] = value.toString();
				//Debug:
				System.out.println(jsonStr[i]);
			}
		}
		return jsonStr;
	}

}
