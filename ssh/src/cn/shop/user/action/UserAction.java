package cn.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.user.model.User;
import cn.shop.user.service.UserService;

/**
 * 用户模块的Action
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	//获得输入的验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//驱动模型使用的对象
	private User user=new User();
	public User getModel() {
		return user;
	}
	//注入userservice
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private static final long serialVersionUID = 1L;

	public String registPage() throws Exception {
		return "registPage";
	}

	/*
	 * 使用AJAX进行异步校验用户名的执行方法
	 * 
	 */
	public String findByName() throws IOException{
		//调用service进行查询
		User existUser = userService.findByName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser != null){
			//用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
			System.out.println("1");
		}else{
			//用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
			System.out.println("2");
		}
		return NONE;
	}
	/*
	 * 用户注册
	 * 
	 */
	public String regist(){
		//获得session中验证码
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(checkcode.equalsIgnoreCase(checkcode1)){
			//验证成功
			userService.save(user);
			this.addActionMessage("注册成功，请去邮箱激活！");
			return "msg";			
		}else{
			//验证失败
			this.addActionError("验证码错误！");
			return "checkcodefail";
		}
		
	}
	/*
	 * 
	 * 用户激活
	 */
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser!=null){
			//激活成功
			existUser.setCode(null);
			existUser.setState(1);
			userService.update(existUser);
			this.addActionMessage("激活成功：请登录！");
		}else{
			//激活失败
			this.addActionMessage("激活失败：激活吗错误或者过期！");
		}
		return "msg";
	}
	/*
	 * 登录页面跳转
	 * 
	 */
	public String loginPage(){
		return "loginPage";
	}
	/*
	 * 
	 * 用户登录验证
	 */
	public String login(){
		User existUser=userService.login(user);
		if(existUser != null){
			//登陆成功
			//将用户信息存入session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//跳转到首页
			return "loginSuccess";
		}else{
			//登录失败
			this.addActionError("登录失败：用户名或密码错误或用户未激活！");
			return LOGIN;			
		}
	}
	/*
	 * 退出登陆
	 * 
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
