package org.zerock.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ProductDto;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Commit
    @Test
    public void testInsert() {
        ProductDto productDto = ProductDto.builder()
                .pname("Product")
                .pdesc("Product desc")
                .writer("user1")
                .price(4000)
                .build();

        productMapper.insert(productDto);

        productDto.addImage(UUID.randomUUID().toString(), "test_1.jpg");
        productDto.addImage(UUID.randomUUID().toString(), "test__2.jpg");

        productMapper.insertImages(productDto);
    }

    @Test
    public void testSelectOne() {

        Integer pno = 1;
        ProductDto productDto = productMapper.selectOne(pno);

        log.info(productDto);

        productDto.getImageList().forEach(log::info);
    }

    @Transactional
    @Commit
    @Test
    public void testDeleteOne() {

        ProductDto productDto = ProductDto.builder()
                .pno(1)
                .pname("Update Product")
                .pdesc("Update")
                .price(6000)
                .build();

        productDto.addImage(UUID.randomUUID().toString(), "test3.jpg");
        productDto.addImage(UUID.randomUUID().toString(), "test4.jpg");
        productDto.addImage(UUID.randomUUID().toString(), "test5.jpg");

        productMapper.deleteImages(productDto.getPno());

        productMapper.updateOne(productDto);

        productMapper.insertImages(productDto);

    }

}
