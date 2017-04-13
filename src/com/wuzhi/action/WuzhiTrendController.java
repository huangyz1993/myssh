package com.wuzhi.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.util.DateUtil;
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

	public String array[];

	public Integer arrayMon[];

	public Integer arrayTue[];

	public Integer arrayWed[];

	public Integer arrayThu[];

	public Integer arrayFri[];

	public Integer arraySat[];

	public Integer arraySun[];

	public List<Articles> articles = new ArrayList<Articles>();

	/**
	 * 展示词汇出现的频率
	 * 
	 * @return
	 */
	public String showTrend() {
		// List<Articles> allArticles = articlesService.getArticles();
		Date beginTime=new Date();
		List<Articles> allArticles = wuzhiArticleService.getArticles();
		Date endTime=new Date();
		System.out.println("花费时间:"+(endTime.getTime()-beginTime.getTime()));
		StringBuilder str = new StringBuilder("");
		endTime=new Date();
		System.out.println("花费时间1:"+(endTime.getTime()-beginTime.getTime()));
		for (Articles item : allArticles) {
			str.append(item.getArticle());
		}
		endTime=new Date();
		System.out.println("花费时间2:"+(endTime.getTime()-beginTime.getTime()));
		// str = str.replaceAll("[^(\u2E80-\u9FFF)(a-z)(A-Z)]", ""); //
		// 替换所有非中文和英文的;
		str = new StringBuilder(str.toString().replaceAll("[^(\u4e00-\u9fa5)]", "")); // 替换所有非中文和英文的;
		Map<String, Integer> maps = new HashMap<String, Integer>();
		endTime=new Date();
		System.out.println("花费时间3:"+(endTime.getTime()-beginTime.getTime()));
		for (int i = 0; i < str.length(); i++) {
			String key = String.valueOf((str.charAt(i)));
			if (!maps.containsKey(key))
				maps.put(key, 1);
			else {
				int val = maps.get(key);
				maps.put(key, val + 1);
			}

		}
		endTime=new Date();
		System.out.println("花费时间4:"+(endTime.getTime()-beginTime.getTime()));
		number = new String[maps.size()][2];
		int x = 0;
		for (Map.Entry i : maps.entrySet()) {
			number[x][0] = i.getValue() + "";
			number[x][1] = i.getKey() + "";
			x++;
		}
		endTime=new Date();
		System.out.println("花费时间5:"+(endTime.getTime()-beginTime.getTime()));
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
		endTime=new Date();
		System.out.println("花费时间6:"+(endTime.getTime()-beginTime.getTime()));
		return "showTrend";
	}

	/**
	 * 展示写日志的时间趋势
	 * 
	 * @return
	 */
	public String showTimeTrend() {
		// articles = articlesService.getArticles();
		articles = wuzhiArticleService.getArticles();
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

	@SuppressWarnings("deprecation")
	public String showWeak() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		// 或者用 Date 来初始化 Calendar 对象
		calendar.setTime(new Date());
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		int weak = calendar.get(Calendar.DAY_OF_WEEK);
		String time = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time1 = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time2 = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time3 = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time4 = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time5 = dft.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		String time6 = dft.format(calendar.getTime());
		Map weakMap = wuzhiArticleService.getOneWeakNum(time, time1, time2, time3, time4, time5, time6);
		array = new String[7];
		calendar.setTime(new Date(time.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "1", time);

		calendar.setTime(new Date(time1.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "2", time1);

		calendar.setTime(new Date(time2.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "3", time2);

		calendar.setTime(new Date(time3.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "4", time3);

		calendar.setTime(new Date(time4.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "5", time4);

		calendar.setTime(new Date(time5.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "6", time5);

		calendar.setTime(new Date(time6.replace("-", "/")));
		weak = calendar.get(Calendar.DAY_OF_WEEK);
		returnWeak(weak, array, weakMap, "7", time6);
		// switch (weak) {
		// case 1:
		// for (int i = 6; i >= 0; i--) {
		// array[i] = ((BigDecimal) weakMap.get((7 - i) + "")).toString();
		// }
		// ;
		// break;
		// case 2:
		// for (int i = 6; i >= 1; i--) {
		// array[i] = ((BigDecimal) weakMap.get((8 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// case 3:
		// for (int i = 6; i >= 2; i--) {
		// array[i] = ((BigDecimal) weakMap.get((9 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("2")).toString();
		// array[1] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// case 4:
		// for (int i = 6; i >= 3; i--) {
		// array[i] = ((BigDecimal) weakMap.get((10 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("3")).toString();
		// array[1] = ((BigDecimal) weakMap.get("2")).toString();
		// array[2] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// case 5:
		// for (int i = 6; i >= 4; i--) {
		// array[i] = ((BigDecimal) weakMap.get((11 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("4")).toString();
		// array[1] = ((BigDecimal) weakMap.get("3")).toString();
		// array[2] = ((BigDecimal) weakMap.get("2")).toString();
		// array[3] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// case 6:
		// for (int i = 6; i >= 5; i--) {
		// array[i] = ((BigDecimal) weakMap.get((12 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("5")).toString();
		// array[1] = ((BigDecimal) weakMap.get("4")).toString();
		// array[2] = ((BigDecimal) weakMap.get("3")).toString();
		// array[3] = ((BigDecimal) weakMap.get("2")).toString();
		// array[4] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// case 7:
		// for (int i = 6; i >= 6; i--) {
		// array[i] = ((BigDecimal) weakMap.get((13 - i) + "")).toString();
		// }
		// array[0] = ((BigDecimal) weakMap.get("6")).toString();
		// array[1] = ((BigDecimal) weakMap.get("5")).toString();
		// array[2] = ((BigDecimal) weakMap.get("4")).toString();
		// array[3] = ((BigDecimal) weakMap.get("3")).toString();
		// array[4] = ((BigDecimal) weakMap.get("2")).toString();
		// array[5] = ((BigDecimal) weakMap.get("1")).toString();
		// ;
		// break;
		// }

		return "showWeak";
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

	public void returnWeak(int weak, String arr[], Map weakMap, String i, String time) {
		List<Articles> articles = wuzhiArticleService.getArticlesByTime(time);
		switch (weak) {
		case 1:
			array[6] = ((BigDecimal) weakMap.get(i)).toString();
			arraySun = new Integer[24];
			for (int x = 0; x < 24; x++) {
				arraySun[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arraySun[Integer.parseInt(times)]++;
			}
			break;
		case 2:
			array[0] = ((BigDecimal) weakMap.get(i)).toString();
			arrayMon = new Integer[24];
			for (int x = 0; x < 24; x++) {
				arrayMon[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arrayMon[Integer.parseInt(times)]++;
			}
			break;
		case 3:
			array[1] = ((BigDecimal) weakMap.get(i)).toString();
			arrayTue = new Integer[24];
			for (int x = 0; x < 24; x++) {
				arrayTue[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arrayTue[Integer.parseInt(times)]++;
			}
			break;
		case 4:
			array[2] = ((BigDecimal) weakMap.get(i)).toString();
			arrayWed= new Integer[24];
			for (int x = 0; x < 24; x++) {
				arrayWed[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arrayWed[Integer.parseInt(times)]++;
			}
			break;
		case 5:
			array[3] = ((BigDecimal) weakMap.get(i)).toString();
			arrayThu= new Integer[24];
			for (int x = 0; x < 24; x++) {
				arrayThu[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arrayThu[Integer.parseInt(times)]++;
			}
			break;
		case 6:
			array[4] = ((BigDecimal) weakMap.get(i)).toString();
			arrayFri= new Integer[24];
			for (int x = 0; x < 24; x++) {
				arrayFri[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arrayFri[Integer.parseInt(times)]++;
			}
			break;
		case 7:
			array[5] = ((BigDecimal) weakMap.get(i)).toString();
			arraySat= new Integer[24];
			for (int x = 0; x < 24; x++) {
				arraySat[x] = 0;
			}
			for (Articles item : articles) {
				String times = DateUtil.format(item.getTime(), "HH");
				arraySat[Integer.parseInt(times)]++;
			}
			break;
		}
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

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	public Integer[] getArrayMon() {
		return arrayMon;
	}

	public void setArrayMon(Integer[] arrayMon) {
		this.arrayMon = arrayMon;
	}

	public Integer[] getArrayTue() {
		return arrayTue;
	}

	public void setArrayTue(Integer[] arrayTue) {
		this.arrayTue = arrayTue;
	}

	public Integer[] getArrayWed() {
		return arrayWed;
	}

	public void setArrayWed(Integer[] arrayWed) {
		this.arrayWed = arrayWed;
	}

	public Integer[] getArrayThu() {
		return arrayThu;
	}

	public void setArrayThu(Integer[] arrayThu) {
		this.arrayThu = arrayThu;
	}

	public Integer[] getArrayFri() {
		return arrayFri;
	}

	public void setArrayFri(Integer[] arrayFri) {
		this.arrayFri = arrayFri;
	}

	public Integer[] getArraySat() {
		return arraySat;
	}

	public void setArraySat(Integer[] arraySat) {
		this.arraySat = arraySat;
	}

	public Integer[] getArraySun() {
		return arraySun;
	}

	public void setArraySun(Integer[] arraySun) {
		this.arraySun = arraySun;
	}

}
