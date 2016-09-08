package cn.shop.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/*
 * 分页查询工具类
 * 
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>>{
	
	private String hql;
	private Object[] params;
	private int startIndex;
	private int pageSize;
	

	public PageHibernateCallback(String hql, Object[] params,
			int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}



	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		//1 鎵цhql璇彞
		Query query = session.createQuery(hql);
		//2 瀹為檯鍙傛暟
		if(params != null){
			for(int i = 0 ; i < params.length ; i ++){
				query.setParameter(i, params[i]);
			}
		}
		//3 鍒嗛〉
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		
		return query.list();
	}

}
