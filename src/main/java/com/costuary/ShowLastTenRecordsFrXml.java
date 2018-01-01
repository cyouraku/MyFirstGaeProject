package com.costuary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.costuary.bean.RootBean;
import com.costuary.bean.XmlBean;
import com.costuary.util.IJsonStrCreator;
import com.costuary.util.JsonStrFactory;
@SuppressWarnings("serial")
public class ShowLastTenRecordsFrXml extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		JsonStrFactory factory = new JsonStrFactory();
		IJsonStrCreator creator = factory.creatJsonStrCreatorForLenovoH515s();
		List<XmlBean> beanList = new ArrayList<XmlBean>();
    	String filePath = "LastTenRecordsFromH515S.xml";
    	File file = new File(filePath);
    	/* 初始化jaxb编组器（marshaler)*/
    	JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RootBean.class );
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			RootBean rootBean = (RootBean)unmarshaller.unmarshal(file);
			beanList = rootBean.getBeanList();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		StringBuilder jsonVal = new StringBuilder();
		String[] jsonValList = creator.creatJsonStrListFromXmlBean(beanList);
		int length = jsonValList.length;
		if (length >= 1) {
			jsonVal.append("[");
			for (int i = 0; i < (length - 1); i++) {
				jsonVal.append(jsonValList[i] + ",");
			}
			jsonVal.append(jsonValList[length - 1]);
			jsonVal.append("]");
		} else {
			jsonVal.append(creator.creatJsonStrNull());
		}
        req.setAttribute("jsonValList", jsonVal.toString());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/showLastTenRecordsOfTemp.jsp");
        dispatcher .forward(req, resp);
	}
}
