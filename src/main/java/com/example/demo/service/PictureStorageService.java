package com.example.demo.service;


import com.example.demo.dto.PictureDto;
import com.example.demo.model.Picture;
import com.example.demo.repository.PictureRepository;
import com.example.demo.util.PictureUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PictureStorageService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String encoded = Base64Utils.encodeToString(file.getBytes());
        Picture picture = new Picture(encoded,file.getContentType());

        return pictureRepository.save(picture);
    }

    public Picture getPicture(long id){
        return pictureRepository.findById(id).get(0);
    }

    public List<Picture> getAllPictures(){
        List<Picture> images = pictureRepository.findAll();
        System.out.println("lista de imagenes:"+images);
        return images;
    }

    public Picture uploadImage(MultipartFile file) throws IOException {

        if(!file.isEmpty()){
            Picture picture = new Picture();
//            picture.setName(file.getOriginalFilename());
            picture.setType(file.getContentType());
            String encoded = Base64Utils.encodeToString(file.getBytes());
//            picture.setImage(PictureUtil.compressImage(file.getBytes()));
            picture.setImage(encoded);
            pictureRepository.save(picture);
        return picture;
        }
        return null;
    }
    @Transactional
    public int updateImage(long id,String image, String type) {
            return pictureRepository.updatePictureFields(id, image, type);
    }
/*
    public String downloadImage(String fileName){
        List<Picture> imageData = pictureRepository.findByName(fileName);
        return imageData.get(0).getImage();
    }
*/
}
