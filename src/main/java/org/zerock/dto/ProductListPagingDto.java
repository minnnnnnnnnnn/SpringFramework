package org.zerock.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class ProductListPagingDto {

    private List<ProductListDto> productDtoList;
    private int totalCount;

    private int page, size;
    private int start, end;
    private boolean prev, next;

    private List<Integer> pageNums;

    public ProductListPagingDto(List<ProductListDto> productDtoList, int totalCount, int page, int size) {
        this.productDtoList = productDtoList;
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;

        // start 계산을 위한 end 페이지
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        this.start = tempEnd - 9;
        this.prev = start != 1; // start 값이 1이 아니라면 이전페이지 필요

        // 임시 end값 * size 가 totalCount 보다 크면 다시 계산 필요
        if ((tempEnd * size) > totalCount) {
            this.end = (int)(Math.ceil(totalCount/(double)size));
        } else {
            this.end = tempEnd;
        }

        // end 값 * size 보다 totalCount가 크면 다음페이지 필요
        this.next = totalCount > (this.end * size);

        // 화면에 출력한 번호들 계산
        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }


}
