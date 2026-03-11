package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.ProductDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@Log4j2
public class ProductController {

    @GetMapping("/register")
    public void registerGET() {

        log.info("product register");
    }

    @PostMapping("/register")
    public String register(ProductDto productDto, @RequestParam("files")MultipartFile[] files, RedirectAttributes reAtr) {

        log.info("--------");
        log.info(productDto);
        log.info(files);

        List<String> uploadNames = uploadFiles(files);

        uploadNames.forEach(name -> {

            String uuid = name.substring(0, 36);
            String fileName = name.substring(37);

            log.info(uuid);
            log.info(fileName);

            productDto.addImage(uuid, fileName);
        });

        return "redirect:/product/list";
    }

    private List<String> uploadFiles(MultipartFile[] files) throws RuntimeException {

        List<String> uploadNames = new ArrayList<>();

        if (files == null || files.length == 0) {

            return uploadNames;
        }
        String uploadPath = "C:\\upload";

        log.info("--------uploadPath--------");
        log.info(uploadPath);

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String fileName = file.getOriginalFilename();
            String uploadName = UUID.randomUUID().toString() + "_" + fileName;

            File targetFile = new File(uploadPath, uploadName);

            try (
                InputStream fin = file.getInputStream();
                OutputStream fos = new FileOutputStream(targetFile);
            ) {
                log.info(targetFile.getAbsolutePath());

                FileCopyUtils.copy(fin, fos);
                uploadNames.add(uploadName);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }

            if (file.getContentType().startsWith("image")) {

                try {
                    Thumbnails.of(targetFile)
                            .size(200, 200)
                            .toFile(new File(uploadPath, "s_" + uploadName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadNames;
    }

    private void deleteFiles(List<String> fileNames) {

        try {
            File uploadPath = new File("C:\\upload");

            for (String fileName : fileNames) {
                File targetFile = new File(uploadPath, fileName);

                targetFile.delete();

                File targetThumb = new File(uploadPath, "s_" + fileName);

                targetThumb.delete();
            }
        } catch (Exception e) {}
    }
}
