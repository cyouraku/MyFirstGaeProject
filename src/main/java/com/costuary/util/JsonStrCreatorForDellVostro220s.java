package com.costuary.util;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.costuary.bean.ServerHealthInfoBean;
import com.costuary.bean.XmlBean;

public class JsonStrCreatorForDellVostro220s implements IJsonStrCreator {

	//use singleton pattern
	private static JsonStrCreatorForDellVostro220s jsonStrCreatorForDellVostro220s = new JsonStrCreatorForDellVostro220s();
	private JsonStrCreatorForDellVostro220s(){};

	public static JsonStrCreatorForDellVostro220s getInstance(){
		return jsonStrCreatorForDellVostro220s;
	}

	/**
	 * create json string for DellVostro220s
	 */
	public String creatJsonStr(ServerHealthInfoBean bean) {
		JsonObject value = null;
		value = Json.createObjectBuilder().add("seqNo", bean.getRecord_id())
//				 .add("gpu_core1", bean.getGpu_temperature())
				 .add("cpu_core1", bean.getCpu_teperature())
				 .add("cpu_core2", bean.getCpu_teperature2())
				 .add("cpu_fan", bean.getFan_speed())
				 .add("mtb_fan", bean.getFan_speed2())
				 .add("serverIp", bean.getServerIp())
			     .add("date",bean.getRecord_timeStamp()).build();
		//Debug:
		System.out.println(value.toString());
		return "[" + value.toString() + "]";
	}

	/**
	 * create json string with null value for DellVostro220s
	 */
	public String creatJsonStrNull() {
		JsonObject value = null;
		value = Json.createObjectBuilder().add("seqNo", "null")
//				 .add("gpu_core1", "null")
				 .add("cpu_core1","null")
				 .add("cpu_core2", "null")
				 .add("cpu_fan", "null")
				 .add("mtb_fan", "null")
				 .add("serverIp", "null")
			     .add("date","null").build();
		//Debug:
		System.out.println(value.toString());
		return "[" + value.toString() + "]";
	}

	/**
	 * create json string[] for DellVostro220s
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
//						 .add("gpu_core1", bean.getGpu_temperature())
						 .add("cpu_core1", bean.getCpu_teperature())
						 .add("cpu_core2", bean.getCpu_teperature2())
						 .add("cpu_fan", bean.getFan_speed())
						 .add("mtb_fan", bean.getFan_speed2())
						 .add("serverIp", bean.getServerIp())
					     .add("date",bean.getRecord_timeStamp()).build();
				jsonStr[i] = value.toString();
				//Debug:
				System.out.println(jsonStr[i]);
			}
		}
		return jsonStr;
	}

	@Override
	public String[] creatJsonStrListFromXmlBean(List<XmlBean> beanList) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
