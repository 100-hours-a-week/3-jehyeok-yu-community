package com.kakaotechbootcamp.community.postlike.repository;

import com.kakaotechbootcamp.community.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long>,
    PostLikeRepositoryCustom {

}
