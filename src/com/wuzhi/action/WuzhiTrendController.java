package com.wuzhi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.util.DateUtil;
import com.wuzhi.entity.Article;
import com.wuzhi.entity.Articles;
import com.wuzhi.service.ArticleService;
import com.wuzhi.service.WuzhiArticleService;

@Controller("trend")
public class WuzhiTrendController {

	public String number[][];

	public String numberCopy[][];

	public Integer timeNumber[];

	public String keyword = "";

	public String time = "";

	public Integer keywordNum = 0;

	@Autowired
	private WuzhiArticleService wuzhiArticleService;

	@Autowired
	private ArticleService articlesService;

	public List<Articles> articles = new ArrayList<Articles>();

	/**
	 * 展示词汇出现的频率
	 * 
	 * @return
	 */
	public String showTrend() {
		List<Articles> allArticles = articlesService.getArticles();
		String str = "";
		for (Articles item : allArticles) {
			str += item.getArticle();
		}
		// str = str.replaceAll("[^(\u2E80-\u9FFF)(a-z)(A-Z)]", ""); //
		// 替换所有非中文和英文的;
		str = str.replaceAll("[^(\u4e00-\u9fa5)]", ""); // 替换所有非中文和英文的;
		Map<String, Integer> maps = new HashMap<String, Integer>();
		for (int i = 0; i < str.length(); i++) {
			String key = String.valueOf((str.charAt(i)));
			if (!maps.containsKey(key))
				maps.put(key, 1);
			else {
				int val = maps.get(key);
				maps.put(key, val + 1);
			}

		}
		number = new String[maps.size()][2];
		int x = 0;
		for (Map.Entry i : maps.entrySet()) {
			number[x][0] = i.getValue() + "";
			number[x][1] = i.getKey() + "";
			x++;
		}
		int length = number.length;
		for (int i = 0; i < length - 1; i++)
			for (int j = i + 1; j < length; j++) {
				int temp;
				String word = "";
				if (Integer.parseInt(number[i][0]) < Integer.parseInt(number[j][0])) {
					temp = Integer.parseInt(number[i][0]);
					word = number[i][1];
					number[i][1] = number[j][1];
					number[i][0] = number[j][0];
					number[j][0] = temp + "";
					number[j][1] = word;

				}
			}
		numberCopy = number;
		return "showTrend";
	}

	/**
	 * 展示写日志的时间趋势
	 * 
	 * @return
	 */
	public String showTimeTrend() {
		articles = articlesService.getArticles();
		timeNumber = new Integer[24];
		for (int i = 0; i < 24; i++) {
			timeNumber[i] = 0;
		}
		for (Articles item : articles) {
			String time = DateUtil.format(item.getTime(), "HH");
			timeNumber[Integer.parseInt(time)]++;
		}
		return "timeTrend";
	}

	/**
	 * 展示出现词汇的的时间趋势
	 * 
	 * @return
	 */
	public String showTimeKeyTrend() {
		if (keyword.equals(""))
			return "showTimeKeyTrend";
		articles = wuzhiArticleService.getArticles(keyword);
		timeNumber = new Integer[24];
		for (int i = 0; i < 24; i++) {
			timeNumber[i] = 0;
		}
		for (Articles item : articles) {
			String time = DateUtil.format(item.getTime(), "HH");
			timeNumber[Integer.parseInt(time)]++;
		}
		return "showTimeKeyTrend";
	}

	/**
	 * 输入关键字查询
	 * 
	 * @return
	 */
	public String showKeyword() {
		if (!keyword.equals(""))
			keywordNum = wuzhiArticleService.getByKeyword(keyword, time);
		return "showKeyword";
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

	public Integer[] getTimeNumber() {
		return timeNumber;
	}

	public void setTimeNumber(Integer[] timeNumber) {
		this.timeNumber = timeNumber;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getKeywordNum() {
		return keywordNum;
	}

	public void setKeywordNum(Integer keywordNum) {
		this.keywordNum = keywordNum;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

}
