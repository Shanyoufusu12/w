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
 * �û�ģ���Action
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	//����������֤��
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//����ģ��ʹ�õĶ���
	private User user=new User();
	public User getModel() {
		return user;
	}
	//ע��userservice
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private static final long serialVersionUID = 1L;

	public String registPage() throws Exception {
		return "registPage";
	}

	/*
	 * ʹ��AJAX�����첽У���û�����ִ�з���
	 * 
	 */
	public String findByName() throws IOException{
		//����service���в�ѯ
		User existUser = userService.findByName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser != null){
			//�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
			System.out.println("1");
		}else{
			//�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
			System.out.println("2");
		}
		return NONE;
	}
	/*
	 * �û�ע��
	 * 
	 */
	public String regist(){
		//���session����֤��
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(checkcode.equalsIgnoreCase(checkcode1)){
			//��֤�ɹ�
			userService.save(user);
			this.addActionMessage("ע��ɹ�����ȥ���伤�");
			return "msg";			
		}else{
			//��֤ʧ��
			this.addActionError("��֤�����");
			return "checkcodefail";
		}
		
	}
	/*
	 * 
	 * �û�����
	 */
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser!=null){
			//����ɹ�
			existUser.setCode(null);
			existUser.setState(1);
			userService.update(existUser);
			this.addActionMessage("����ɹ������¼��");
		}else{
			//����ʧ��
			this.addActionMessage("����ʧ�ܣ������������߹��ڣ�");
		}
		return "msg";
	}
	/*
	 * ��¼ҳ����ת
	 * 
	 */
	public String loginPage(){
		return "loginPage";
	}
	/*
	 * 
	 * �û���¼��֤
	 */
	public String login(){
		User existUser=userService.login(user);
		if(existUser != null){
			//��½�ɹ�
			//���û���Ϣ����session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//��ת����ҳ
			return "loginSuccess";
		}else{
			//��¼ʧ��
			this.addActionError("��¼ʧ�ܣ��û��������������û�δ���");
			return LOGIN;			
		}
	}
	/*
	 * �˳���½
	 * 
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
