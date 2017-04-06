year = new Date().getFullYear();
month = new Date().getMonth() + 1;
// if($("#years").val()!=null&&$("#years").val()!=""){
// year=$("#years").val();
// month =$("#months").val();
// $("#nowYear").text(year);
// $("#nowMonth").text(month);
// }
$("#nowYear").text(year);
if(parseInt(month)<10){
	$("#nowMonth").text("0"+month);
}else{
	$("#nowMonth").text(month);
}
date = new Date().getDate();
day = new Array();
isLeapYear = 0;// 是否闰年
weakDay = 1;// 星期几
initDay();
initTime();
writeDays = $("#writeDays").val().split(";");
for (i = 0; i < $("#weak tr td").length; i++) {
	for (j = 0; j < writeDays.length; j++) {
		if (parseInt(writeDays[j]) == parseInt($("#weak tr td:eq(" + i + ")").html())) {
			$("#weak tr td:eq(" + i + ")").css("color", "blue");
			$(".lineTime:eq(" + (i-2) + ")").css("color", "blue");
			break;
		}
	}

}

function initTime() {
	initDay();
	for (i = 0; i < $("#weak tr td").length; i++) {
		$("#weak tr td:eq(" + i + ")").text("");
	}
	if (year % 100 == 0 && year % 400 == 0) {
		isLeapYear = 1;
	}
	if (year % 100 != 0 & year % 4 == 0) {
		isLeapYear = 1;
	}
	if (parseInt(month) == 2 && isLeapYear == 1) {
		day.splice(29, 2);
	} else if (parseInt(month) == 2 && isLeapYear != 1) {
		day.splice(28, 3);
	}
	switch (parseInt(month)) {
	case 4:
	case 6:
	case 9:
	case 11:
		day.splice(30, 1);
		;
		break;
	}
	completeTime = year + "/" + month + "/" + 1;
	weakDay = new Date(completeTime).getDay();
	switch (weakDay) {
	case 1:
		$("#weak tr td:eq(0)").text(1);
		break;
	case 2:
		$("#weak tr td:eq(1)").text(1);
		break;
	case 3:
		$("#weak tr td:eq(2)").text(1);
		break;
	case 4:
		$("#weak tr td:eq(3)").text(1);
		break;
	case 5:
		$("#weak tr td:eq(4)").text(1);
		break;
	case 6:
		$("#weak tr td:eq(5)").text(1);
		break;
	case 0:
		$("#weak tr td:eq(6)").text(1);
		break;
	}
	initColor();
	for (i = 1; i < day.length+1; i++) {
		if (weakDay == 0) {
			weakDay += 7;
		}
		$("#weak tr td:eq(" + (i + weakDay - 1) + ")").text(day[i]);
		if (day[i] == date) {
			$("#weak tr td:eq(" + (i + weakDay - 1) + ")").css("color", "red");
		}

	}
	if (date == 1) {
		$("#weak tr td:eq(" + (weakDay - 1) + ")").css("color", "red");
	}
	$(".lineTime").remove();
	for(i = 1; i < day.length+1;i++){
		$(".verticalBar").append(" <div class='lineTime'><div class='lineOne'></div>"+i+"</div>");
	}
}

$("#weak tr td").click(function() {
	if($(this).css("color")=="rgb(0, 0, 0)"){
		return;
	}
	initColor();
// console.log($(this).css("color")=="rgb(0, 0, 0)");
// console.log($(this).css("color"));
// console.log("rgb(0,0,0)");
	$(this).css("color", "red");
	date=$(this).text();
	// alert($("#nowYear").text());
	$.post("/mySSH/wuzhi!showUserArticle", {
		years : $("#nowYear").text(),
		months : $("#nowMonth").text(),
		dates : $(this).text()
	}, function(result) {
		str = (result.match("id=\"articleContent\">[\\s\\S]*?</div>")) + "";
		// alert(str);
		str = str.replace("id=\"articleContent\">", "").replace("</div>", "");
		// alert(str);
		$("#articleContent").html(str);
		nowHight=$("#articleContent").height();
		$("#articleContent").css("height",0);
		str = (result.match("type=\"hidden\"[\\s\\S]*?>")) + "";
		// alert(str);
		str = str.replace("type=\"hidden\"", "").replace("value=\"", "")
				.replace("\"", "").replace(" ", "").replace("/>", "");
		writeDays = str.split(";");
		for (i = 0; i < $("#weak tr td").length; i++) {
			if ($("#weak tr td:eq(" + i + ")").text()!= date) {
				$("#weak tr td:eq(" + i + ")").css("color", "black");
			}
			for (j = 0; j < writeDays.length; j++) {
				if (parseInt(writeDays[j]) == parseInt($("#weak tr td:eq(" + i + ")").html())&&$("#weak tr td:eq(" + i + ")").text()!= date) {
					$("#weak tr td:eq(" + i + ")").css("color", "blue");
					break;
				}
			}

		}
		$("#articleContent").animate({height:nowHight},1000,"swing",justtest);
		//$("#articleContent").css("height",'auto');
	});
})
function justtest(){
	$("#articleContent").css("height",'');
}

function nextYear() {
	chooseYear = parseInt($("#nowYear").text()) + 1;
	$("#nowYear").text(chooseYear);
	year = chooseYear;
	initTime();
	getWriteDays();
}

function lastYear() {
	chooseYear = parseInt($("#nowYear").text()) - 1;
	$("#nowYear").text(chooseYear);
	year = chooseYear;
	initTime();
	getWriteDays();
}
function nextMonth() {
	chooseMonth = parseInt($("#nowMonth").text()) + 1;
	if (chooseMonth > 12) {
		$("#nowYear").text(parseInt($("#nowYear").text()) + 1);
		;
		year = parseInt($("#nowYear").text());
		chooseMonth = 1;
	}
	if (chooseMonth > 0 && chooseMonth < 10) {
		chooseMonth = "0" + chooseMonth;
	}
	$("#nowMonth").text(chooseMonth);
	month = chooseMonth;
	initTime();
	getWriteDays();
}

function lastMonth() {
	chooseMonth = parseInt($("#nowMonth").text()) - 1;
	if (chooseMonth < 1) {
		$("#nowYear").text(parseInt($("#nowYear").text()) - 1);
		;
		year = parseInt($("#nowYear").text());
		chooseMonth = 12;
	}
	if (chooseMonth > 0 && chooseMonth < 10) {
		chooseMonth = "0" + chooseMonth;
	}
	$("#nowMonth").text(chooseMonth);
	month = chooseMonth;
	initTime();
	getWriteDays();
}

function initDay() {
	for (i = 0; i <= 30; i++) {
		day[i] = i + 1;
	}
}

function initColor() {
	for (i = 0; i < $("#weak tr td").length; i++) {
		console.log($("#weak tr td:eq(" + i + ")").css("color") );
		if ($("#weak tr td:eq(" + i + ")").text()== date) {
			$("#weak tr td:eq(" + i + ")").css("color", "black");
		}

	}
}

function getWriteDays() {
	$.post("/mySSH/wuzhi!showUserArticle", {
		years : $("#nowYear").text(),
		months : $("#nowMonth").text()
	}, function(result) {
		str = (result.match("id=\"articleContent\">[\\s\\S]*?</div>")) + "";
		// alert(str);
		str = str.replace("id=\"articleContent\">", "").replace("</div>", "");
		// alert(str);
		$("#articleContent").html(str);
		str = (result.match("type=\"hidden\"[\\s\\S]*?>")) + "";
		// alert(str);
		str = str.replace("type=\"hidden\"", "").replace("value=\"", "")
				.replace("\"", "").replace(" ", "").replace("/>", "");
		writeDays = str.split(";");
		for (i = 0; i < $("#weak tr td").length; i++) {
			$("#weak tr td:eq(" + i + ")").css("color", "black");
			for (j = 0; j < writeDays.length; j++) {
				if (parseInt(writeDays[j]) == parseInt($("#weak tr td:eq(" + i + ")").html())) {
					$("#weak tr td:eq(" + i + ")").css("color", "blue");
					break;
				}
			}

		}
	});
}

/** * js 工具类** */

function stringToInt(string) {
	return parseInt(string);
}

function calculateWeak(year, month, day) {
	if (month == 1 || month == 2) {
		month += 12;
		year -= 1;
	}
	alert(year + "  " + month + "  " + day)
	weakday = Math.floor((day + 2 * month + 3 * (month + 1) / 5 + year + year
			/ 4 - year / 100 + year / 400) % 7);

	alert(weakday);
	/**
	 * switch(weakday): { case 1:weakDay=1;break; //����һ case
	 * 2:weakDay=2;break; case 3:weakDay=3;break; case 4:weakDay=4;break; case
	 * 5:weakDay=5;break; case 6:weakDay=6;break; case 7:weakDay=7;break; }
	 */
	return weakday;
}