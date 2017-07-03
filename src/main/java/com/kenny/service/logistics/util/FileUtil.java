package com.kenny.service.logistics.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
@Component
public class FileUtil {

    public static String IMGS_DIR = "files";


    /**
     * 随机生成文件名称
     *
     * @param file
     * @return
     */
    public static String radomName(MultipartFile file) {
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的位置到文件的最后，最后得到扩展名，如".png"
        String videoName = file.getOriginalFilename().toLowerCase();
        String ext = videoName.substring(videoName.lastIndexOf("."), videoName.length());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //重新定义文件名
        String fileName = uuid + ext;
        return fileName;
    }

    /**
     * 存储图片
     *
     * @param img
     * @return 存储图片名称
     */
    public static String saveImg(MultipartFile img) throws IOException {
        String fileName = radomName(img);
        if (!Files.exists(Paths.get(IMGS_DIR)))
            Files.createDirectory(Paths.get(IMGS_DIR));
        Files.copy(img.getInputStream(), Paths.get(IMGS_DIR, fileName));

        return fileName;
    }

    /**
     * 返回一张图片，ContentType为图片类型
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static ResponseEntity<?> getImg(String fileName) {
        ResourceLoader loader = new DefaultResourceLoader();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(loader.getResource(Paths.get(IMGS_DIR, fileName).toUri().toString()));
    }
}
