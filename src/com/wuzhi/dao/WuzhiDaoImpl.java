package com.wuzhi.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.wuzhi.entity.Articles;
import com.wuzhi.entity.User;

@Repository("wuzhiDao")
public class WuzhiDaoImpl extends BaseDao implements WuzhiDao {

	@Override
	public void insertWuzhiUser(User obj) {
		getSession().save(obj);
	}

	@Override
	public void updateWuzhiUser(User obj) {
		// TODO Auto-generated method stub
		getSession().update(obj);
	}

	public User findByUserId(String userId) {
		return (User) getSession().createQuery("from User where userid='" + userId + "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByUserIds(String userIds) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from User where " + userIds + "").list();
	}

	// 筛选出最近写的104个用户
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<Articles> articles = getSession().createQuery("from Articles order by time desc").list();
		String condition = "";// 条件
		String condition1 = "";// 防止in自动排序
		String userids[] = new String[104];
		int articlesLength = articles.size();
		int index = 0;
		boolean isRepeat = false;
		for (int i = 0; i < articlesLength && index < 104; i++) {
			isRepeat = false;
			for (int j = 0; j < 104; j++) {
				if (articles.get(i).getUserid().equals(userids[j])) {
					isRepeat = true;
					break;
				}
			}
			if (!isRepeat) {
				userids[index] = articles.get(i).getUserid();
				condition += "'" + articles.get(i).getUserid() + "',";
				if (index == 0) {
					if (StringUtils.isNumeric(articles.get(i).getUserid())) {
						condition1 += "order by INSTR('," + articles.get(i).getUserid() + ",";
					} else {
						condition1 += "order by INSTR(',\"" + articles.get(i).getUserid() + "\",";
					}
				} else if (index == 103) {
					if (StringUtils.isNumeric(articles.get(i).getUserid())) {
						condition1 += "" + articles.get(i).getUserid() + ",',CONCAT(',',userid,','))";
					} else {
						condition1 += "\"" + articles.get(i).getUserid() + "\",',CONCAT(',',userid,','))";
					}
				} else {
					if (StringUtils.isNumeric(articles.get(i).getUserid())) {
						condition1 += "" + articles.get(i).getUserid() + ",";
					} else {
						condition1 += "\"" + articles.get(i).getUserid() + "\",";
					}
				}
				index++;
			}
		}
		condition += "'" + articles.get(103).getUserid() + "'";
		return getSession().createQuery("from User  where userid in(" + condition + ")" + condition1 + "").list();
	}

	/**
	 * 获取所有用户
	 */
	@Override
	public List<User> getUserAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from User").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser(Integer page) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("from User order by addtime");
		q.setFirstResult((page - 1) * 104);
		q.setMaxResults(104);
		return q.list();
	}
	@Override
	public Integer getUserCount() {
		Query q = getSession().createQuery("select count(*) from User");
		return Integer.parseInt(q.list().get(0).toString());
	}
}
