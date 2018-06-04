package iful.edu.RestMVC.utils;

public class PaginationUtils {

	public static int getOffcet(int page) {
		int entries;
		if (page == 1) {
			return entries = 0;
		} else {
			return entries = (page - 1) * 10 + 1;
		}
	}

}
