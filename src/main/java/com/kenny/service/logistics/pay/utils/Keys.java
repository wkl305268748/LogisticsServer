package com.kenny.service.logistics.pay.utils;

public class Keys {
	public static String PageUrl = "http://localhost:8080/PayGat-Pc/PageUrl.html";

	public static String ReturnUrl = "http://localhost:8080/v1/bfpay/returnUrl";

	public static String Md5key = "abcdefg";

	public static String MemberID = "100000178";//"1218008";

	public static String TerminalID = "100000859";//"40374";

	// 正式地址https://gw.baofoo.com/payindex
	public static String payUrl = "https://vgw.baofoo.com/payindex";// 请求地址

	public static String InterfaceVersion = "4.0";

	public static String KeyType = "1";// 加密类型(固定值为1)

	public static String NoticeType = "1";// 通知类型 = "1";

	public static String dataType = "xml"; // 数据类型 xml/json

	//https://public.baofoo.com/baofoo-fopay/pay/BF0040001.do
	public static String payToBankUrl = "https://paytest.baofoo.com/baofoo-fopay/pay/BF0040001.do";

	public static String keyStorePath = "./cer_test/m_pri.pfx";

	public static String keyStorePassword = "123456";

	public static String pub_key = "./cer_test/baofu.cer";
}
