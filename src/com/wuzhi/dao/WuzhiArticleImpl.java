package com.wuzhi.dao;

import java.util.List;

import javax.persistence.OrderBy;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.wuzhi.entity.Article;
import com.wuzhi.view.UserArticle;

@Repository("wuzhiArticleDao")
public class WuzhiArticleImpl extends BaseDao implements WuzhiArticleDao {

	@Override
	public void insertArticle(Article article) {
		getSession().save(article);
	}

	@Override
	public boolean findByUserAndTime(String userId, String time, String content) {
		if (getSession().createQuery("from Article where userid='" + userId + "' and time='" + time + "'").list()
				.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Article order by time desc").list();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<UserArticle> getUserArticles(String userId, String year, String month, String date) {
		// TODO Auto-generated method stub
		String sql = "";
		if (date != null) {
			sql = "time like '%" + year + "-" + month + "-" + date + "%'";
		} else {
			sql = "time like '%" + year + "-" + month + "%'";
		}
		if (year == null) {
			return (List<UserArticle>) getSession()
					.createCriteria(Article.class, "article")
					.createAlias("article." + "userid", "user", Criteria.LEFT_JOIN)
					.add(Restrictions.eq("userid." + "userid", userId))
					.setProjection(
							Projections.projectionList().add(Projections.property("article." + "id").as("id"))
									.add(Projections.property("user.name").as("name"))
									.add(Projections.property("user.relBigPic").as("relBigPic"))
									.add(Projections.property("article.article").as("article"))
									.add(Projections.property("article.time").as("time"))).addOrder(Order.desc("time"))
					.setResultTransformer(new AliasToBeanResultTransformer(UserArticle.class)).list().subList(0, 1);
		}
		List<UserArticle> list = (List<UserArticle>) getSession()
				.createCriteria(Article.class, "article")
				.createAlias("article." + "userid", "user", Criteria.LEFT_JOIN)
				.add(Restrictions.eq("userid." + "userid", userId))
				.setProjection(
						Projections.projectionList().add(Projections.property("article." + "id").as("id"))
								.add(Projections.property("user.name").as("name"))
								.add(Projections.property("user.relBigPic").as("relBigPic"))
								.add(Projections.property("article.article").as("article"))
								.add(Projections.property("article.time").as("time"))).addOrder(Order.desc("time"))
				.add(Restrictions.sqlRestriction(sql))
				.setResultTransformer(new AliasToBeanResultTransformer(UserArticle.class)).list();
		if (list.size() > 0) {
			return list;
		} else {
			return (List<UserArticle>) getSession()
					.createCriteria(Article.class, "article")
					.createAlias("article." + "userid", "user", Criteria.LEFT_JOIN)
					.add(Restrictions.eq("userid." + "userid", userId))
					.setProjection(
							Projections.projectionList().add(Projections.property("article." + "id").as("id"))
									.add(Projections.property("user.name").as("name"))
									.add(Projections.property("user.relBigPic").as("relBigPic"))
									.add(Projections.property("article.article").as("article"))
									.add(Projections.property("article.time").as("time"))).addOrder(Order.desc("time"))
					.setResultTransformer(new AliasToBeanResultTransformer(UserArticle.class)).list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getTrend(String keyword) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Article where article like '%" + keyword + "%'").list();
	}

	@Override
	public Integer getByKeyword(String keyword, String time) {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery("from Article where article like '%" + keyword + "%' and time like '%" + time + "%'")
				.list().size();
	}
}
