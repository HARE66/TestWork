package ru.naumen.prilepskiy;

//Третий файл – это тестовый класс SearcherTest. С его помощью можно проверить, что решение работает.

public class SearcherTest {

	public static void main (String[] args){
		
		final String start = "start"; //условия поиска
		
		Searcher sear = new Searcher ();
		
		long TimeMillisPresent = System.currentTimeMillis();
		long TimeMillisPast = System.currentTimeMillis() - 1000;
		
		String[] classNames = {"startA","startC","startF","startD","startB","startB"}; // = null;
		long[] modificationDates = {TimeMillisPresent,TimeMillisPast,TimeMillisPresent,TimeMillisPresent,TimeMillisPresent,TimeMillisPresent}; //{3,2,33,1,4}; // = null;
		
		
		sear.refresh(classNames, modificationDates);
		String [] s = sear.guess(start);
		
		for (int i = 0; s.length > i; i++) {
			System.out.println(s[i]);
		}
	}
}


/* 2016 - 1970 = 46 лет
 * 
 * System.out.println(System.currentTimeMillis());  1468379603593 мс = 46 лет.
 * System.out.println(Long.MAX_VALUE);				9223372036854775807
 * 
 * */

