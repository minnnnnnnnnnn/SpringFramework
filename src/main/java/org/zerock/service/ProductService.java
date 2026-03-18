package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDto;
import org.zerock.mapper.ProductMapper;

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


}
