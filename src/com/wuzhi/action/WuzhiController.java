package com.wuzhi.action;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.util.DateUtil;
import com.util.Url;
import com.util.Wuzhi;
import com.wuzhi.entity.Article;
import com.wuzhi.entity.Articles;
import com.wuzhi.entity.User;
import com.wuzhi.service.ArticleService;
import com.wuzhi.service.WuzhiArticleService;
import com.wuzhi.service.WuzhiService;
import com.wuzhi.view.UserArticle;

@Controller("wuzhi")
public class WuzhiController {

	public List<User> userList;

	public List<Article> articles;

	public List<Articles> articleWithNoUser;

	public String userId;

	public String name;

	public String relBigPic;

	public List<UserArticle> userArticles;

	public Integer love;

	public Integer kindship;

	public Integer friendship;

	public String number[][];

	public String numberCopy[][];

	public String writeDays;

	@Autowired
	private WuzhiService wuzhiService;

	@Autowired
	private WuzhiArticleService wuzhiArticleService;

	@Autowired
	private ArticleService articlesService;

	@Autowired
	private ThreadWuzhi threadWuzhi;

	public String uploadUser() throws ParseException, MalformedURLException, InterruptedException {
		// for (int xx = 0; xx < 10000; xx++) {
		Date beginDate = new Date();
		User user = new User();
		List<String> wuzhi = new Wuzhi().getUserInfo();
		List<User> oldUsers = new ArrayList<User>(40);// 空列表存放根据新页面出来的40个用户，不一定存在
		List<User> findUsers = new ArrayList<User>();// 存放根据新页面出来从数据库查出来的用户
		String userIds = "";
		int i = 0;
		for (String item : wuzhi) {
			String userArray[] = item.split("\"");
			String userId = userArray[0].split("/")[2];
			if (i == 0) {
				userIds += " userid='" + userId + "'";
			} else {
				userIds += " or  userid='" + userId + "'";
			}
			i++;
		}
		findUsers = wuzhiService.findUsers(userIds);
		int findUserLength = findUsers.size();
		for (String item : wuzhi) {
			String userArray[] = item.split("\"");
			String userId = userArray[0].split("/")[2];
			int x = 0;
			for (int j = 0; j < findUserLength; j++) {
				if (userId.equals(findUsers.get(j).getUserid())) {
					oldUsers.add(findUsers.get(j));
					break;
				}
				x++;
			}
			if (x == findUserLength) {
				oldUsers.add(null);
			}
		}

		int oldUserIndex = 0;
		for (String item : wuzhi) {
			String userArray[] = item.split("\"");
			String userId = userArray[0].split("/")[2];
			User oldUser = oldUsers.get(oldUserIndex);
			// User oldUser =wuzhiService.findUser(userId);
			if (oldUser == null) {
				if (userArray.length > 2)
					user.setName(userArray[2]);
				else {
					user.setName("");
				}
				user.setUserid(userId);
				user.setPic(userArray[1]);
				user.setUrl(userArray[0]);
				String relPic = new Url().downloadNet(user.getPic(), user.getUserid(), "1");
				String relBigPic = new Url().downloadNet(user.getPic().replaceAll("small_avatar", "avatar"),
						user.getUserid(), "2");
				user.setAddtime(DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss"));
				user.setRelPic(relPic);
				user.setRelBigPic(relBigPic);
				wuzhiService.InsertUSer(user);
			} else {
				String oldName = "";
				String oldPic = "";
				String relPic = "";
				String relBigPic = "";
				String names[] = oldUser.getName().split(";;;xxx");
				String pics[] = oldUser.getPic().split(";;;xxx");
				if (names.length > 0) {
					oldName = names[0];
				}
				if (pics.length > 0) {
					oldPic = pics[0];
				}
				if (userArray.length > 2) {
					if (!userArray[2].equals(oldName)) {
						oldUser.setName(userArray[2] + ";;;xxx" + oldUser.getName());
					}
				} else if (!"".equals(oldUser.getName())) {
					oldUser.setName("" + ";;;xxx" + oldUser.getName());
				}
				if (!userArray[1].equals(oldPic)) {
					oldUser.setPic(userArray[1] + ";;;xxx" + oldUser.getPic());
					relPic = new Url().downloadNet(userArray[1], oldUser.getUserid(), "1");
					oldUser.setRelPic(relPic + ";;;xxx" + oldUser.getRelPic());
					relBigPic = new Url().downloadNet(userArray[1].replaceAll("small_avatar", "avatar"),
							oldUser.getUserid(), "2");
					oldUser.setRelBigPic(relBigPic + ";;;xxx" + oldUser.getRelBigPic());
				}
				if (userArray.length > 2) {
					if (!userArray[2].equals(oldName) || !userArray[1].equals(oldPic)) // 用户名或者头像有一个不一样修改
						wuzhiService.updateUser(oldUser);
				} else {
					if (!"".equals(oldName) || !userArray[1].equals(oldPic)) // 用户名或者头像有一个不一样修改
						wuzhiService.updateUser(oldUser);
				}
			}
			String url = "https://wuzhi.me" + userArray[0];
			addArticle(new Url().getContent(url), userId);
			oldUserIndex++;
		}
		Date endDate = new Date();
		System.out.println(endDate.getTime() - beginDate.getTime());
		// Thread.sleep(60000);
		// }
		// userList = wuzhiService.getAllUser();
		return "showWuzhi";
	}

	/**
	 * 显示所有头像
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public String showWuzhi() throws MalformedURLException {
		userList = wuzhiService.getAllUser();
		// for (User item : userList) {
		// String relPic = new Url().downloadNet(item.getPic(),item.getUserid(),
		// "1");
		// String relBigPic = new Url().downloadNet(
		// item.getPic().replaceAll("small_avatar", "avatar"),
		// item.getUserid(), "2");
		// item.setRelPic(relPic);
		// item.setRelBigPic(relBigPic);
		// wuzhiService.updateUser(item);
		// }
		// userList = userList.subList(0, 104);
		int length = userList.size();
		for (int i = 0; i < length; i++) {
			userList.get(i).setRelPic(userList.get(i).getRelPic().split(";;;xxx")[0]);
		}

		// 打乱list
		// Collections.shuffle(userList);
		return "showWuzhi";

	}

	public String showLove() {
		userList = wuzhiService.getAllUser();
		int length = userList.size();
		for (int i = 0; i < length; i++) {
			userList.get(i).setRelPic(userList.get(i).getRelPic().split(";;;xxx")[0]);
		}
		Collections.shuffle(userList);
		userList = userList.subList(0, 280);
		return "showLove";
	}

	/**
	 * 显示所有日志
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public String showArticle() throws MalformedURLException {
		articleWithNoUser = articlesService.getArticles();
		return "showArticle";

	}

	/**
	 * 展示个人日志
	 * 
	 * @return
	 */
	public String showUserArticle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String years = request.getParameter("years");

		String months = request.getParameter("months");

		String dates = request.getParameter("dates");
		writeDays = "";
		name = "";
		relBigPic = "";
		if (dates != null && Integer.parseInt(dates) < 10) {
			dates = "0" + dates;
		}
		userArticles = wuzhiArticleService.getUserArticles(userId, years, months, dates);
		List<Articles> userWriteList = articlesService.getUserArticles(userId, years, months);
		int length = userWriteList.size();
		for (int i = 0; i < length; i++) {
			try {
				writeDays += userWriteList.get(i).getTime().toString().substring(8, 10) + ";";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (userArticles.size() > 0) {
			name = userArticles.get(0).getName();
			if (name.split(";;;").length > 0) {
				name = name.split(";;;")[0];
			}
			relBigPic = userArticles.get(0).getRelBigPic();
			if (relBigPic.split(";;;").length > 0) {
				relBigPic = relBigPic.split(";;;")[0];
			}
		}
		return "userArticle";
	}

	/**
	 * 下载所有日志
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addUserArticle() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 15167; i < 45167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		// threadWuzhi.setIndex(15436);
		// threadWuzhi.setIndex(15436);
		// Thread t1 = new Thread(threadWuzhi);
		// t1.start();
		// threadWuzhi.setIndex(15436 + 30000 * 1);
		// Thread t2 = new Thread(threadWuzhi);
		// t2.start();
		// threadWuzhi.setIndex(15436 + 30000 * 2);
		// Thread t3 = new Thread(threadWuzhi);
		// t3.start();
		// threadWuzhi.setIndex(15436 + 30000 * 3);
		// Thread t4 = new Thread(threadWuzhi);
		// t4.start();
		// threadWuzhi.setIndex(15436 + 30000 * 4);
		// Thread t5 = new Thread(threadWuzhi);
		// t5.start();
		// threadWuzhi.setIndex(15436 + 30000 * 5);
		// Thread t6 = new Thread(threadWuzhi);
		// t6.start();
		// threadWuzhi.setIndex(15436 + 30000 * 6);
		// Thread t7 = new Thread(threadWuzhi);
		// t7.start();
		// threadWuzhi.setIndex(15436 + 30000 * 7);
		// Thread t8 = new Thread(threadWuzhi);
		// t8.start();
		// threadWuzhi.setIndex(15436 + 30000 * 8);
		// Thread t9 = new Thread(threadWuzhi);
		// t9.start();
		// threadWuzhi.setIndex(15436 + 30000 * 9);
		// Thread t10 = new Thread(threadWuzhi);
		// t10.start();
		return "success";
	}

	public String addUserArticle1() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 45167; i < 75167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle2() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 75167; i < 105167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle3() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 105167; i < 135167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle4() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 135167; i < 165167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle5() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 165167; i < 195167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle6() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 195167; i < 225167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle7() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 225167; i < 255167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle8() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 255167; i < 285167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	public String addUserArticle9() throws ParseException {
		String urlString = "https://wuzhi.me/u/";
		for (int i = 285167; i < 315167; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				addArticle(returnUrlString, i + "");
			}
			System.out.println(i);
		}
		return "success";
	}

	// 增加日志
	private void addArticle(String str, String userId) throws ParseException {
		Pattern pattern = Pattern.compile("(note_time\">).*?(</div>)$?");
		Matcher matcher = pattern.matcher(str);
		List<String> time = new ArrayList<String>();
		List<String> content = new ArrayList<String>();
		List<String> dateTime = new ArrayList<String>();
		while (matcher.find()) {
			time.add(matcher.group().replaceAll("note_time\">", "").replaceAll("</div>", ""));
		}
		// for (String item : time) {
		// System.out.println(item);
		// }
		pattern = Pattern.compile("(note_content\">)([\\s\\S]*?)(</div>)$?");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			content.add(matcher.group().replaceAll("note_content\">", "").replaceAll("</div>", ""));
		}
		// System.out.println(str);
		Pattern dateTimePattern = Pattern.compile("(\\d){4}[年](\\d){2}[月](\\d){2}[日]");
		Matcher dateTimeMatcher = dateTimePattern.matcher(str);
		while (dateTimeMatcher.find()) {
			dateTime.add(dateTimeMatcher.group());
		}
		// for (String item : content) {
		// System.out.println(item);
		// }
		int i = 0;
		Article article = new Article();
		for (String item : content) {
			article.setArticle(item);
			User user = new User();
			user.setUserid(userId);
			article.setUserid(user);
			// String date = DateUtil.format(new Date(), "yyyy-MM-dd");
			dateTime.set(0, dateTime.get(0).replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", ""));
			Date date2 = DateUtil.parse(dateTime.get(0) + " " + time.get(i) + ":00", "yyyy-MM-dd HH:mm:ss");
			/*
			 * Date nowDate=new Date(); nowDate.setSeconds(00); if
			 * (date2.after(nowDate)) { // 时间存在误差,60秒以内,吾志只显示分钟而且应该有进位 date2 =
			 * decrease(date2); }
			 */
			article.setTime(date2);
			article.setAddtime(DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			if (!wuzhiArticleService.isExist(userId, DateUtil.format(date2, "yyyy-MM-dd HH:mm:ss"), item)) {
				wuzhiArticleService.add(article);
			}
			i++;
		}
	}

	public Date decrease(Date trueDate) throws ParseException {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		date.setTime(trueDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate = dft.parse(dft.format(date.getTime()));
		return endDate;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserArticle> getUserArticles() {
		return userArticles;
	}

	public void setUserArticles(List<UserArticle> userArticles) {
		this.userArticles = userArticles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelBigPic() {
		return relBigPic;
	}

	public void setRelBigPic(String relBigPic) {
		this.relBigPic = relBigPic;
	}

	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}

	public Integer getKindship() {
		return kindship;
	}

	public void setKindship(Integer kindship) {
		this.kindship = kindship;
	}

	public Integer getFriendship() {
		return friendship;
	}

	public void setFriendship(Integer friendship) {
		this.friendship = friendship;
	}

	public String[][] getNumber() {
		return number;
	}

	public void setNumber(String[][] number) {
		this.number = number;
	}

	public String[][] getNumberCopy() {
		return numberCopy;
	}

	public void setNumberCopy(String[][] numberCopy) {
		this.numberCopy = numberCopy;
	}

	public List<Articles> getArticleWithNoUser() {
		return articleWithNoUser;
	}

	public void setArticleWithNoUser(List<Articles> articleWithNoUser) {
		this.articleWithNoUser = articleWithNoUser;
	}

	public String getWriteDays() {
		return writeDays;
	}

	public void setWriteDays(String writeDays) {
		this.writeDays = writeDays;
	}

}
