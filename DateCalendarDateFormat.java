package simple.javase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalendarDateFormat {
    
	public static void main(String[] args) {
        Date date=new Date();//创建当前日期时间的Date对象
        System.out.println(date);
        
        Calendar calendar=Calendar.getInstance();//Calendar类用于完成对日期和时间字段的操作
        calendar.set(2017,11,29); //设置calendar当前的时间段为此
         calendar.add(Calendar.DATE, 1); //将 日 +1
        calendar.add(Calendar.YEAR, 1); //将 年  +1
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        System.out.println(year+":"+month+":"+day);
	
		calendar.setTime(date);
		System.out.println(calendar.getTime());
		
		//该类专门用于将日期格式化维字符串或者用特定模式显示
		DateFormat dateFormatFull=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL); //完整的中文格式时间日期
		DateFormat dateFormatLong=DateFormat.getDateInstance(DateFormat.LONG);  //长格式
		DateFormat dateFormatMediun=DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		DateFormat dateFormatShort=DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(dateFormatFull.format(date)+"\n"+dateFormatLong.format(date)+"\n"+dateFormatMediun.format(date)+"\n"+dateFormatShort.format(date));
		
		
        
	}

}
