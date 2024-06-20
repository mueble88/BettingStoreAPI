package com.example.demo.repository;

import com.example.demo.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Blob;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findById(long id);

//    List<Picture>findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Picture picture SET picture.image = :image, picture.type = :type WHERE picture.id = :id")
    int updatePictureFields( @Param("id") long id,
                             @Param("image")String image,
                             @Param("type")String type);

}
