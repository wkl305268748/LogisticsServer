package com.kenny.service.logistics.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import org.apache.log4j.Logger;

import com.kenny.service.logistics.pay.utils.BaofooClient;
import com.kenny.service.logistics.pay.utils.Keys;
import com.kenny.service.logistics.pay.utils.RequestParams;
import com.kenny.service.logistics.pay.utils.RsaCodingUtil;
import com.kenny.service.logistics.pay.utils.SecurityUtil;
import com.kenny.service.logistics.pay.utils.SimpleHttpResponse;
import com.kenny.service.logistics.pay.utils.TransContent;
import com.kenny.service.logistics.pay.utils.TransHead;
import com.kenny.service.logistics.pay.utils.TransReqBF0040001;
import com.kenny.service.logistics.pay.utils.TransRespBF0040001;

public class PayUtils {

	private static Logger logger = Logger.getLogger(PayUtils.class);

	public static void payToBankCard(String order,String money,String name,String card,String bank,String phone,String idcard) throws ErrorCodeException {
		TransReqBF0040001 enty = new TransReqBF0040001();
		enty.setTrans_money(money);
		enty.setTo_acc_name(name);
		enty.setTo_acc_no(card);
		enty.setTo_bank_name(bank);
		enty.setTrans_card_id(idcard);
		enty.setTrans_mobile(phone);
		enty.setTrans_no(order);
		payToBankCard(enty);
	}

	public static void payToBankCard(TransReqBF0040001 entity) throws ErrorCodeException {

		try {

			// 对私：收款人姓名、收款人银行帐号、收款人银行名称、银行卡身份证件号、（银行卡预留手机号）
			// 对公：收款人姓名、收款人银行帐号、收款人银行名称、收款人开户行省名、收款人开户行市名、收款人开户行机构名

			TransContent<TransReqBF0040001> transContent = new TransContent<TransReqBF0040001>(Keys.dataType);

			List<TransReqBF0040001> trans_reqDatas = new ArrayList<TransReqBF0040001>();

			TransReqBF0040001 transReqData = new TransReqBF0040001();
			transReqData.setTrans_no(entity.getTrans_no());
			transReqData.setTrans_money(entity.getTrans_money());
			transReqData.setTo_acc_name(entity.getTo_acc_name());
			transReqData.setTo_acc_no(entity.getTo_acc_no());
			transReqData.setTo_bank_name(entity.getTo_bank_name());
			transReqData.setTrans_card_id(entity.getTrans_card_id());
			trans_reqDatas.add(transReqData);

			transContent.setTrans_reqDatas(trans_reqDatas);

			String bean2XmlString = transContent.obj2Str(transContent);

			String origData = bean2XmlString;

			/**
			 * 加密规则：项目编码UTF-8 第一步：BASE64 加密 第二步：商户私钥加密
			 */
			origData = new String(SecurityUtil.Base64Encode(origData));
			String encryptData = RsaCodingUtil.encryptByPriPfxFile(origData, Keys.keyStorePath, Keys.keyStorePassword);

			// 发送请求
			RequestParams params = new RequestParams();
			params.setMemberId(Integer.parseInt(Keys.MemberID));
			params.setTerminalId(Integer.parseInt(Keys.TerminalID));
			params.setDataType(Keys.dataType);
			params.setDataContent(encryptData);// 加密后数据
			params.setVersion("4.0.0");
			params.setRequestUrl(Keys.payToBankUrl);
			SimpleHttpResponse response = BaofooClient.doRequest(params);

			logger.info("宝付请求返回结果：" + response.getEntityString());

			TransContent<TransRespBF0040001> str2Obj = new TransContent<TransRespBF0040001>(Keys.dataType);
			String reslut = response.getEntityString();
			/**
			 * 在商户终端正常的情况下宝付同步返回会以密文形式返回,如下： 此时要先宝付提供的公钥解密：RsaCodingUtil.decryptByPubCerFile(reslut, pub_key)
			 * 再次通过BASE64解密：new String(new Base64().decode(reslut)) 在商户终端不正常或宝付代付系统异常的情况下宝付同步返回会以明文形式返回
			 */
			// 明文返回处理可能是报文头参数不正确、或其他的异常导致；
			if (reslut.contains("trans_content")) {
				// 明文返回
				// 我报文错误处理
				str2Obj = (TransContent<TransRespBF0040001>) str2Obj.str2Obj(reslut, TransRespBF0040001.class);
				// 业务逻辑判断
			} else {
				// 密文返回
				// 第一步：公钥解密
				reslut = RsaCodingUtil.decryptByPubCerFile(reslut, Keys.pub_key);
				// 第二步BASE64解密
				reslut = SecurityUtil.Base64Decode(reslut);
				System.out.println(reslut);
				str2Obj = (TransContent<TransRespBF0040001>) str2Obj.str2Obj(reslut, TransRespBF0040001.class);
				// 业务逻辑判断
				TransHead list = str2Obj.getTrans_head();

				String return_code = list.getReturn_code();
				String return_msg = list.getReturn_msg();
				if (!return_code.equals("0000")) {
					throw new ErrorCodeException(new ErrorCode(-1,return_msg));
				}
			}
		}
		catch (ErrorCodeException e){
			throw new ErrorCodeException(e.getErrorCode());
		}
		catch (Exception e){
			throw new ErrorCodeException(new ErrorCode(-1,e.getMessage()));
		}
	}
}
