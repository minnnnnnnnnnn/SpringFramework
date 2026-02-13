package org.zerock.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long bno;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private boolean delFlag;

}
