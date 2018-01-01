package com.costuary.util;

import java.util.List;

import com.costuary.bean.ServerHealthInfoBean;
import com.costuary.bean.XmlBean;

public interface IJsonStrCreator {

	/**
	 *create json string
	 * @return
	 */
	String creatJsonStr(ServerHealthInfoBean bean);

	/**
	 *create json string with null info
	 * @return
	 */
	String creatJsonStrNull();

	/**
	 *create json string[] for ServerHealthInfoBean list
	 * @return
	 */
	String[] creatJsonStrList(List<ServerHealthInfoBean> beanList);


	/**
	 *create json string[] form XmlBean list
	 * @return
	 */
	String[] creatJsonStrListFromXmlBean(List<XmlBean> beanList);

}
