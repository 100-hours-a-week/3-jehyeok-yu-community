package com.kakaotechbootcamp.community.post.repository;

import com.kakaotechbootcamp.community.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long>,
    PostImageRepositoryCustom {

}
