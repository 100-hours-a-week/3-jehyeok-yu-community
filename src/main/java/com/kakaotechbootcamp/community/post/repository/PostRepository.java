package com.kakaotechbootcamp.community.post.repository;

import com.kakaotechbootcamp.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
