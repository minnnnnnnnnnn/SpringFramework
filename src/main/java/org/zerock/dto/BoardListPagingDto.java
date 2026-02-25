package org.zerock.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class BoardListPagingDto {

    private List<BoardDto> boardDtoList;
    private int totalCount;
    private int page, size;

    private int start, end;
    private boolean prev, next;
    private List<Integer> pageNums;

    public BoardListPagingDto(List<BoardDto> boardDtoList, int totalCount, int page, int size) {

        this.boardDtoList = boardDtoList;
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;

        // start 계산
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        this.start = tempEnd - 9;

        this.prev = start != 1; // start 값이 1이 아니라면 true

        if ((tempEnd * size) > totalCount) {
            this.end = (int) (Math.ceil(totalCount / (double)size));
        } else {
            this.end = tempEnd;
        }
        this.next = totalCount > (this.end * size);

        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
