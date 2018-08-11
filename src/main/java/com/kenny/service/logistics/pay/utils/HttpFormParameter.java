package com.kenny.service.logistics.pay.utils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HttpFormParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private boolean hidden;

	/**
	 * 
	 */
	public HttpFormParameter() {
		super();
	}

	/**
	 * @param name
	 * @param value
	 */
	public HttpFormParameter(String name, String value) {
		this(name, value, true);
	}

	/**
	 * @param name
	 * @param value
	 * @param hidden
	 */
	public HttpFormParameter(String name, String value, boolean hidden) {
		super();
		this.name = name;
		this.value = value;
		this.hidden = hidden;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden
	 *            the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
