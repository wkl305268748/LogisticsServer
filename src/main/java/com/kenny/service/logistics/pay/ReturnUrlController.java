package com.kenny.service.logistics.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/bfpay/returnUrl")
public class ReturnUrlController {

	/**
	 * @desc 调用支付
	 * @return
	 */
	@GetMapping(value = "")
	public String returnUrl() {
		// 这个地方具体的自己逻辑
		return null;
	}

}
