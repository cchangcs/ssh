package com.vincent.converters;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class SSHDateConverter extends StrutsTypeConverter{

	private DateFormat dateFormat;
	
	
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		// TODO Auto-generated method stub
		if(toClass == Date.class){
			try {
				return dateFormat.parse(values[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		if( o instanceof Date){
			return dateFormat.format((Date)o);
		}
		return null;
	}

}
