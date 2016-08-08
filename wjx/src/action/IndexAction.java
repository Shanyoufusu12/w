package action;

import java.util.Arrays;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	/**
	 * 首页Action
	 */
	private static final long serialVersionUID = 1L;

	public String inList;

	public String getInList() {
		return inList;
	}

	public void setInList(String inList) {
		this.inList = inList;
	}

	// 转向主页
	public String goIndex() {
		return "goIndex";
	}

	// 计算数组
	public String calculate() {
		System.out.println(inList);
		String[] a = inList.split(",");
		int[] b = new int[a.length];
		int[] c = new int[a.length];
		
		for (int x = 0; x < a.length; x++) {
			b[x] = Integer.parseInt(a[x]);
		}

		for (int z = 0; z < c.length; z++) {
			c[z]=1;
			for (int y = 0; y < b.length; y++) {
				if(z==y){
					continue;
				}
				c[z] *= b[y];
			}
		}
		String l=Arrays.toString(c);
		l=l.replace("[", "");
		l=l.replace("]", "");
		ServletActionContext.getRequest().getSession().setAttribute("l", l);
		return SUCCESS;
	}

}
