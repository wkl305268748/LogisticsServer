package com.kenny.service.logistics.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/bfpay/testPay")
public class TestPayController {

	/**
	 * @desc 调用支付
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "")
	public String returnUrl() throws Exception {
		// PayUtils.payToBankCard();

		return null;
	}

}
