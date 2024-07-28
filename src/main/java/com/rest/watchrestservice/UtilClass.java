package com.rest.watchrestservice;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class UtilClass {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    public static PageRequest buildPageRequest(Integer pageSize, Integer pageNumber, Sort sort){
        int processedPageSize;
        int processedPageNumber;

        if (pageSize !=  null && pageSize > 0){
            if (pageSize > 1000){
                processedPageSize = 1000;
            }
            else {
                processedPageSize = pageSize;
            }
        }
        else {
            processedPageSize = DEFAULT_PAGE_SIZE;
        }

        if (pageNumber != null && pageNumber > 0){
            processedPageNumber = pageNumber - 1;
        }
        else {
            processedPageNumber = DEFAULT_PAGE;
        }

        return PageRequest.of(processedPageNumber,processedPageSize, sort);
    }
}
