package com.kenny.service.logistics.pay.utils;

/**
 * 项目名称：baofoo-fopay-sdk-java
 * 类名称：代付请求参数
 * 类描述：
 * 创建人：陈少杰
 * 创建时间：2014-10-22 下午2:58:22
 * 修改人：陈少杰
 * 修改时间：2014-10-22 下午2:58:22
 * @version
 */
public class RequestParams {
	
	public final static String XML = "xml";
	public final static String JSON = "json";

	private int memberId; // 商户号
	private int terminalId; // 终端号
	private String dataType; // 数据类型 xml/json
	private String dataContent; // 加密数据串
	private String version;
	private String requestUrl;//请求地址
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(int terminalId) {
		this.terminalId = terminalId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}
