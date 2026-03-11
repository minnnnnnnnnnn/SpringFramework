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
public class ProductImageDto {

    private Integer ino;
    private Integer pno;
    private String fileName;
    private String uuid;
    private int ord;
    private LocalDateTime regDate;

}
