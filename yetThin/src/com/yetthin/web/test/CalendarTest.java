package com.yetthin.web.test;

import java.util.Calendar;

public class CalendarTest {
	private static final int [] START_END_TIME={9,20,16,30};
	private static final int [] END_START_MIDDLE={11,00,13,00};
	
		public static void main(String[] args) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			 
			 if((cal.get(Calendar.HOUR_OF_DAY)>START_END_TIME[0]&&
					 cal.get(Calendar.HOUR_OF_DAY)<END_START_MIDDLE[0])||
					 			(cal.get(Calendar.HOUR_OF_DAY)>END_START_MIDDLE[2]&&
					 					cal.get(Calendar.HOUR_OF_DAY)<START_END_TIME[2])&&
					 							cal.get(Calendar.DAY_OF_WEEK)>Calendar.SUNDAY&&
					 								cal.get(Calendar.DAY_OF_WEEK)<Calendar.SATURDAY
					 )
				 System.out.println("sass");
		}
}
