package sungil.management.utils;

import java.util.ArrayList;
import java.util.List;

public class PageNationUtil {
    public static List<Integer> getPageNationNumbers(List<?> list, int itemLength) {
        List<Integer> pageNumbers = new ArrayList<>();
        int page = (list.size() / itemLength) + (list.size() % itemLength != 0 ? 1 : 0);

        for (int i = 0; i < page; i++) {
            pageNumbers.add(i + 1);
        }

        return pageNumbers;
    }


}
