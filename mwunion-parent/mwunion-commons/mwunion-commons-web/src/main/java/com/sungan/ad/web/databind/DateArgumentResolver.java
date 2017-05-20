package com.sungan.ad.web.databind;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sungan.ad.exception.AdRuntimeException;

/**
 * 说明:
 * @version V1.1
 */
public class DateArgumentResolver implements HandlerMethodArgumentResolver {
	private Log log = LogFactory.getLog(DateArgumentResolver.class);
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		String parameterName = parameter.getParameterName();
		try {
			Class<?> parameterType = parameter.getParameterType();
			if(parameterType.newInstance() instanceof Date){
				return true;
			}
		} catch (Exception e) {
			log.warn("日期解析器异常，属性名："+parameterName+"  "+e.getMessage());
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String parameterName = parameter.getParameterName();
		String value = webRequest.getParameter(parameterName); 
		String valueFormat = webRequest.getParameter(parameterName+"_format"); 
		SimpleDateFormat format = null;
		if(StringUtils.isNotBlank(valueFormat)){
			 format = new SimpleDateFormat(valueFormat);
		}else{
			 format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		 if(StringUtils.isNotBlank(value)){
			 try {
				Date parse = format.parse(value);
				 return parse;
			} catch (Exception e) {
				log.error("数年解析异常:"+e.getMessage());
				throw new AdRuntimeException(parameterName+"日期格式错误");
			}
		 }
		return null;
	}

}
