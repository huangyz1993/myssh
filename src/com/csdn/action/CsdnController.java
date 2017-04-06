package com.csdn.action;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.csdn.entity.Csdn;
import com.csdn.service.CsdnService;
import com.util.DateUtil;

@Controller("csdn")
public class CsdnController {

	@Autowired
	private CsdnService csdnService;

	public String getCsdn() throws ParseException {
		Csdn csdn=new Csdn();
		csdn.setAddtime(DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));
		csdn.setClick_num("1");
		csdn.setTitle("2");
		csdn.setUrl("111");
		csdnService.insertCsdn(csdn);
		return null;
	}
}
