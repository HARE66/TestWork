package ru.naumen.prilepskiy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//������ ���� � ��� ����� Searcher. � ��� ������� refresh � guess ������ ���� �������� ������ ���������. 

public class Searcher implements ISearcher {

	private String[] strSortCollection;
	public final byte maxNumValues = 12; // ����������� ������������ ������ ������� String[] ������������ ������� guess
	
	@Override
	public void refresh(String[] classNames, long[] modificationDates) {
		// TODO Auto-generated method stub
		
		Map<String, Long> treeMap = new TreeMap<String, Long>();
		Map<String, Long> listSortCollection = new LinkedHashMap<String, Long>();

		if (modificationDates != null) { //������ � ������
			if (classNames.length == modificationDates.length) { //������ ����. ������������ = ������ ����. ���
				for (int i = 0; i < modificationDates.length; i++) { //double arrays to Set
					try {
						treeMap.put(classNames[i], modificationDates[i]);
					} catch (NullPointerException e) {
						e.printStackTrace();
						System.err.println("������!!! �� �������� �������� - classNames ������� null!!!");
					}
				}
				
				listSortCollection = SortMapCollections.sortByValue(treeMap); //������������� �� ��������
				ArrayList<String> valueList = new ArrayList<String>(listSortCollection.keySet()); //Map to List
				strSortCollection = valueList.toArray(new String[valueList.size()]); //List to Array

/*				for (int k = 0; strSortCollection.length > k; k++) {
					System.out.println("string[" + k + "] :" + strSortCollection[k]);
				}
*/
			} else {
				System.err.println(
						"������!!! �� �������� ��������� �������� ������ ���������� ��������� � ��������: classNames "
								+ classNames.length + "; " + "modificationDates " + modificationDates.length);
			}
		} else {
			System.err.println("������!!! �� �������� �������� - modificationDates ������� null!!!");
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
			if (ind == 0) { //���� ������� ����������
				if(i == maxNumValues) break; //���������� ���-�� �������
				//System.out.println(strSortCollection[i]);
				listGuess.add(strSortCollection[i]); //��������� � ���������
			}
			//System.out.println(ind);
		}
		String[] strSearchArray = listGuess.toArray(new String[listGuess.size()]);
		return strSearchArray;
	}
}

