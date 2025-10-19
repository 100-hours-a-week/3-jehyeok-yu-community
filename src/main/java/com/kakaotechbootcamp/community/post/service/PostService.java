package com.kakaotechbootcamp.community.post.service;

import com.kakaotechbootcamp.community.post.dto.request.PostCreateRequest;
import com.kakaotechbootcamp.community.post.entity.Post;
import com.kakaotechbootcamp.community.post.repository.PostRepository;
import com.kakaotechbootcamp.community.user.entity.User;
import com.kakaotechbootcamp.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;
    public final UserRepository userRepository;

    public void create(long userId, PostCreateRequest req) {
        User user = userRepository.getReferenceById(userId);
        Post post = Post.create(user, req.getTitle(), req.getContent());
        postRepository.save(post);
    }
}
