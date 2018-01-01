package com.costuary.util;

public class JsonStrFactory implements IJsonStrFactory {

	@Override
	public JsonStrCreatorForDellVostro220s creatJsonStrCreatorForDellVostro220s() {
		return JsonStrCreatorForDellVostro220s.getInstance();
	}

	@Override
	public JsonStrCreatorForLenovoH515s creatJsonStrCreatorForLenovoH515s() {
		return JsonStrCreatorForLenovoH515s.getInstance();
	}

}
