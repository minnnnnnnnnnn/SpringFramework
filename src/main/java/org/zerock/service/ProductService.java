package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDto;
import org.zerock.dto.ProductListDto;
import org.zerock.dto.ProductListPagingDto;
import org.zerock.mapper.ProductMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ProductService {

    private final ProductMapper productMapper;

    public Integer register(ProductDto productDto) {

        productMapper.insert(productDto);

        Integer pno = productDto.getPno();
        productMapper.insertImages(productDto);

        return pno;
    }

    public ProductListPagingDto getList(int page, int size) {

        // 페이지 번호가 0보다 작으면 무조건 1페이지
        page = page <= 0 ? 1 : page;

        // 사이즈가 10보다 작거나 100보다 크면 10
        size = (size <= 10 || page > 100) ? 10 : size;

        int skip = (page - 1) * size;

        List<ProductListDto> list = productMapper.list(skip, size);

        int total = productMapper.listCount();

        return new ProductListPagingDto(list, total, page, size);
    }


}
