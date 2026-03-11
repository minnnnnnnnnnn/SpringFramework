package org.zerock.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductListDto {

    private Integer pno;
    private String pname;
    private String pdesc;
    private int price;
    private boolean sale;
    private String writer;
    private String uuid;
    private String fileName;

}
