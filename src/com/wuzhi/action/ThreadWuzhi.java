package com.wuzhi.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.util.DateUtil;
import com.util.Url;
import com.wuzhi.entity.Article;
import com.wuzhi.entity.User;
import com.wuzhi.service.WuzhiArticleService;

@Repository("threadWuzhi")
public class ThreadWuzhi implements Runnable {
	@Autowired
	private WuzhiArticleService wuzhiArticleService;

	public int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		String urlString = "https://wuzhi.me/u/";
		int endIndex = index + 30000;
		for (int i = index; i < endIndex; i++) {
			String returnUrlString = new Url().getContent(urlString + i);
			if (returnUrlString.indexOf("not_found") > -1 || returnUrlString.indexOf("该用户日记设置了只自己可见") > -1) {

			} else {
				try {
					addArticle(returnUrlString, i + "");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(i);
		}
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

}
