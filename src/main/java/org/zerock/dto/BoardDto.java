package org.zerock.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getCreatedDate() {

        return regDate.format(DateTimeFormatter.ISO_DATE);
    }

}
