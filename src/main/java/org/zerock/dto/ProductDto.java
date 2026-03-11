package org.zerock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer pno;
    private String pname;
    private String pdesc;
    private int price;
    private boolean sale;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private List<ProductImageDto> imageList;

    public void addImage(String uuid, String fileName) {

        if (imageList == null) {
            imageList = new ArrayList<>();
        }

        ProductImageDto imageDto = ProductImageDto.builder()
                .uuid(uuid)
                .fileName(fileName)
                .pno(this.pno)
                .ord(this.imageList.size()) // 0, 1, 2...
                .build();

        imageList.add(imageDto);
    }

    public void clearImages() {

        imageList.clear();
    }
}
