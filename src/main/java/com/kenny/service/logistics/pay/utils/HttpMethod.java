package com.kenny.service.logistics.pay.utils;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * 项目名称：baofoo-fopay-sdk-java
 * 类名称：表单参数
 * 类描述：
 * 创建人：陈少杰
 * 创建时间：2014-10-22 下午2:58:22
 * 修改人：陈少杰
 * 修改时间：2014-10-22 下午2:58:22
 * @version
 */
@XmlType(name = "httpMethod")
@XmlEnum
public enum HttpMethod {
	GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS;

	/**
	 * @return
	 */
	public String value() {
		return name();
	}

	/**
	 * @param v
	 * @return
	 */
	public static HttpMethod fromValue(String v) {
		return valueOf(v);
	}

	/**
	 * @return
	 */
	public static HttpMethod getDefault() {
		return POST;
	}
}
