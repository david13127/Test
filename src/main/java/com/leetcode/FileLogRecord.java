package com.leetcode;

import java.util.*;

/**
 * @Description: 日志统计
 * @author : david
 * @date Date : 2021年03月31日 23:48
 * @version V1.0
 */
public class FileLogRecord {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 这里必须用，LinkedHashMap，按插入顺序排序。随后之后会按照出错次数再排序，但如果出错次数一样，还是要按照插入的顺序来
		// 所以这里必须用 LInkedHashMap
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

		while (sc.hasNext()) {

			String str = sc.nextLine();

			String[] arrStr1 = str.split(" ");
			int line = Integer.parseInt(arrStr1[1]);

			int id = arrStr1[0].lastIndexOf('\\');
			String filename = id == -1 ? arrStr1[0] : arrStr1[0].substring(id + 1);

			//统计频率
			String key = filename + " " + line;
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}

		// 对记录进行排序，需要使用稳定排序
		// 这样，如果出错次数一样多，仍然保持插入顺序
		List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
		list.sort((a, b) -> b.getValue() - a.getValue());

		//只输出前8条
		int m = 0;
		for (Map.Entry<String, Integer> mapping : list) {
			if (m >= 8) {
				break;
			}
			String[] str = mapping.getKey().split(" ");
			String filename = str[0];
			if (filename.length() > 16) {
				filename = filename.substring(filename.length() - 16);
			}
			String n = str[1];
			Integer count = mapping.getValue();
			System.out.printf("%s %s %d%n", filename, n, count);
			m++;
		}
	}
}
