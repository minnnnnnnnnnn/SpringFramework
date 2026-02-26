package org.zerock.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class ReplyListPagingDto {

    private List<ReplyDto> replyDtoList;
    private int totalCount;
    private int page, size;

    private int start, end;
    private boolean prev, next;
    private List<Integer> pageNums;

    public ReplyListPagingDto(List<ReplyDto> replyDtoList, int totalCount, int page, int size) {

        this.replyDtoList = replyDtoList;
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;

        int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
        this.start = tempEnd - 9;

        this.prev = start != 1;

        if ((tempEnd * size) > totalCount) {
            this.end = (int)(Math.ceil(totalCount / (double)size));
        } else {
            this.end = tempEnd;
        }
        this.next = totalCount > (this.end * size);

        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
