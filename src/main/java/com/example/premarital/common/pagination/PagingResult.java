package com.example.premarital.common.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class PagingResult<T> {
    private Integer totalPages;
    private long totalElements;
    private Integer size;
    private Integer page;
    private boolean empty;
    private Collection<T> content;

    public PagingResult(Collection<T> content, Integer totalPages, long totalElements, Integer size, Integer page, boolean empty) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page + 1;
        this.empty = empty;
    }
}
