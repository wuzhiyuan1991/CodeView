package cn.com.shopec.mall.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 基础控制器
 * @author xieyong
 *
 */
public class BaseController {

	@Resource
	HttpServletRequest	request;

	public HttpSession getSession() {
		return request.getSession();
	}
}
