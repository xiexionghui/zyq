/**
 * 谢雄辉
 *2020年4月26日
 */
package com.ubo.zyq.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 谢雄辉
 * 2020年4月26日  
 */
@Controller
public class WxApiInterface {

	
	@RequestMapping("/info")
	@ResponseBody
	public String infosuccess() {
		
		return "测试微信api接口请求";
		
		
	}
}
