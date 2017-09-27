package org.zerock.EncodeService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class encodeProcess {
	
	String text;
	public String getEncode(){
		return text;
	}
public encodeProcess(String s){	
	this.text = encodeURIComponent(s);
}
	private static String encodeURIComponent(String s)
	{
	  String result = null;

	  try
	  {
	    result = URLEncoder.encode(s, "UTF-8")
	                       .replaceAll("\\+", "%20")
	                       .replaceAll("\\%21", "!")
	                       .replaceAll("\\%27", "'")
	                       .replaceAll("\\%28", "(")
	                       .replaceAll("\\%29", ")")
	                       .replaceAll("\\%7E", "~");
	  }

	  // This exception should never occur.
	  catch (UnsupportedEncodingException e)
	  {
	    result = s;
	  }

	  return result;
	}
}
