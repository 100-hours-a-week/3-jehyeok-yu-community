package com.kakaotechbootcamp.community.comment.repository;

import com.kakaotechbootcamp.community.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
