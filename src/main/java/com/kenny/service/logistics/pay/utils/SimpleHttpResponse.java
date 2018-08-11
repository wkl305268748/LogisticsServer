package com.kenny.service.logistics.pay.utils;

import java.io.Serializable;

/**
 * 项目名称：baofoo-fopay-sdk-java 类名称：表单参数 类描述： 创建人：陈少杰 创建时间：2014-10-22 下午2:58:22 修改人：陈少杰 修改时间：2014-10-22 下午2:58:22
 * 
 * @version
 */
public class SimpleHttpResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;

	private String entityString;

	private String errorMessage;

	/**
	 * @param statusCode
	 * @param entityString
	 */
	public SimpleHttpResponse(int statusCode, String entityString, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.entityString = entityString;
		this.errorMessage = errorMessage;
	}

	/**
	 * 是否成功
	 * 
	 * @return
	 */
	public boolean isRequestSuccess() {
		return HttpUtil.isRequestSuccess(statusCode);
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the entityString
	 */
	public String getEntityString() {
		return entityString;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
