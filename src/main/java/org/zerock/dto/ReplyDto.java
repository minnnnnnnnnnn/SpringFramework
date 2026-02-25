package org.zerock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private Long rno;
    private String replyText;
    private String replyer;
    private LocalDateTime replyDate;
    private LocalDateTime updateDate;
    private boolean delFlag;

    private Long bno;
}
