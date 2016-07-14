package ru.naumen.prilepskiy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Второй файл – это класс Searcher. В его методах refresh и guess должна быть написана логика программы. 

public class Searcher implements ISearcher {

	private String[] strSortCollection;
	public final byte maxNumValues = 12; // ограничение максимальной длинны массива String[] возвращаемый методом guess
	
	@Override
	public void refresh(String[] classNames, long[] modificationDates) {
		// TODO Auto-generated method stub
		
		Map<String, Long> treeMap = new TreeMap<String, Long>();
		Map<String, Long> listSortCollection = new LinkedHashMap<String, Long>();

		if (modificationDates != null) { //массив с датами
			if (classNames.length == modificationDates.length) { //длинна масс. наименований = длинне масс. дат
				for (int i = 0; i < modificationDates.length; i++) { //double arrays to Set
					try {
						treeMap.put(classNames[i], modificationDates[i]);
					} catch (NullPointerException e) {
						e.printStackTrace();
						System.err.println("Ошибка!!! Во входящий параметр - classNames передан null!!!");
					}
				}
				
				listSortCollection = SortMapCollections.sortByValue(treeMap); //отсортировать по названию
				ArrayList<String> valueList = new ArrayList<String>(listSortCollection.keySet()); //Map to List
				strSortCollection = valueList.toArray(new String[valueList.size()]); //List to Array

/*				for (int k = 0; strSortCollection.length > k; k++) {
					System.out.println("string[" + k + "] :" + strSortCollection[k]);
				}
*/
			} else {
				System.err.println(
						"Ошибка!!! Во входящие параметры передано разное количество элементов в массивах: classNames "
								+ classNames.length + "; " + "modificationDates " + modificationDates.length);
			}
		} else {
			System.err.println("Ошибка!!! Во входящий параметр - modificationDates передан null!!!");
		}
	}


	@Override
	public String[] guess(String start) {
		// TODO Auto-generated method stub

		List<String> listGuess = new ArrayList<String>();

		//System.out.println("public String[] guess(String " + start + ")");
		int k = strSortCollection.length;
		for (int i = 0; k > i; i++) {
			int ind = strSortCollection[i].indexOf(start); 
			if (ind == 0) { //если найдено совпадение
				if(i == maxNumValues) break; //ограничить кол-во выборки
				//System.out.println(strSortCollection[i]);
				listGuess.add(strSortCollection[i]); //добавляем в коллекцию
			}
			//System.out.println(ind);
		}
		String[] strSearchArray = listGuess.toArray(new String[listGuess.size()]);
		return strSearchArray;
	}
}

