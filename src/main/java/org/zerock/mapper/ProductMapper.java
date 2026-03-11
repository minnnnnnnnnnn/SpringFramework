package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ProductDto;

public interface ProductMapper {

    int insert(ProductDto productDto);

    int insertImages(ProductDto productDto);

    ProductDto selectOne(@Param("pno") Integer pno);

    int deleteOne(@Param("pno") Integer pno);

    int deleteImages(@Param("pno") Integer pno);

    int updateOne(ProductDto productDto);

}
