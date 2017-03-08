package com.wuzhi.dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<Articles> articles = getSession().createQuery("from Articles order by time desc").list();
		String condition = "";
		String userids[] = new String[104];
		int articlesLength = articles.size();
		int index = 0;
		boolean isRepeat = false;
		for (int i = 0; i < articlesLength&&index<104; i++) {
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
				index++;
			}
		}
		condition += "'" + articles.get(103).getUserid() + "'";
		return getSession().createQuery("from User  where userid in(" + condition + ")").list();
	}

}
