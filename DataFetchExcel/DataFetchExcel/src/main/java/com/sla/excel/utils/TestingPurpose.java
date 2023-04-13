package com.sla.excel.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;



public class TestingPurpose {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 Date stringDate= DateFor.parse("14/09/2022 12:20:00");
		 System.out.println("====="+stringDate.getMonth());
		  
//		    Date secondDate = sdf.parse("15-Apr-22");
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		 DateFormat df = new SimpleDateFormat("dd-MMM-yy");
//		    Date firstDate = sdf.parse("11-Mar-22");
//		    Date secondDate = sdf.parse("15-Apr-22");
////		    Date day=new Date();
////		    String day1= String.valueOf(firstDate);
////		    String day2= String.valueOf(firstDate);
//		    long diffInMillies =  Math.abs(firstDate.getTime() - secondDate.getTime());
//		    long diff =  TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//		 // creating the date 1 with sample input date.
//		    System.out.println(diff);
////			Date date1 = new Date(firstDate);
//			
//			// creating the date 2 with sample input date.
//			Date date2 = new Date(2020, 11, 30);
//			
//			// getting milliseconds for both dates
////			long date1InMs = date1.getTime();
////			long date2InMs = date2.getTime();
//			long date1InMs = firstDate.getTime();
//			long date2InMs = secondDate.getTime();
//			
//			// getting the diff between two dates.
//			long timeDiff = 0;
//			if(date1InMs > date2InMs) {
//				timeDiff = date1InMs - date2InMs;
//			} else {
//				//timeDiff = date2InMs - date1InMs;
//				timeDiff = date1InMs - date2InMs;
//			}
//			
//			// converting diff into days
//			int daysDiff = (int) (timeDiff / (1000 * 60 * 60* 24));
//			
//			// print diff in days
//			System.out.println("No of days diff is : "+daysDiff);
			
		   
		  
		    
		    
		
	}

}
