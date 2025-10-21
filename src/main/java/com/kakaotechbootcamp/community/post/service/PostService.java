package com.kakaotechbootcamp.community.post.service;

import com.kakaotechbootcamp.community.post.dto.request.PostCreateRequest;
import com.kakaotechbootcamp.community.post.dto.response.AuthorThumbNailDto;
import com.kakaotechbootcamp.community.post.dto.response.PostThumbNailResponseDto;
import com.kakaotechbootcamp.community.post.dto.response.PostsResponseDto;
import com.kakaotechbootcamp.community.post.entity.Post;
import com.kakaotechbootcamp.community.post.repository.PostRepository;
import com.kakaotechbootcamp.community.user.entity.User;
import com.kakaotechbootcamp.community.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
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

    @Transactional(readOnly = true)
    public PostsResponseDto getPosts(Long lastReadId, int limit) {
        Slice<Post> posts = postRepository.findByLastReadId(lastReadId, limit);
        long nextCursor = posts.hasNext()
            ? posts.getContent().get(posts.getNumberOfElements() - 1).getPostId()
            : -1;
        return makeSliceToPostDto(posts, nextCursor);
    }

    private PostsResponseDto makeSliceToPostDto(Slice<Post> posts, long nextCursor) {
        PostsResponseDto postsResponseDto = new PostsResponseDto(
            nextCursor, posts.hasNext(),
            new ArrayList<>());
        List<PostThumbNailResponseDto> postList = postsResponseDto.getPosts();
        posts.stream().forEach(e -> {
            User author = e.getAuthor();
            postList.add(
                new PostThumbNailResponseDto(e.getTitle(), e.getPostId(), 0, 0, e.getViewCount(),
                    e.getCreatedAt(),
                    new AuthorThumbNailDto(author.getNickname(), "default", author.getUserId())));
        });
        return postsResponseDto;
    }
}
