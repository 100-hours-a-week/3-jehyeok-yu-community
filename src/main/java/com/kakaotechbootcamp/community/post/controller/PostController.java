package com.kakaotechbootcamp.community.post.controller;

import com.kakaotechbootcamp.community.post.dto.request.PostCreateRequest;
import com.kakaotechbootcamp.community.post.dto.response.PostsResponseDto;
import com.kakaotechbootcamp.community.post.service.PostService;
import com.kakaotechbootcamp.community.utils.response.ApiResponse;
import com.kakaotechbootcamp.community.utils.security.AuthPrincipal;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
        @AuthenticationPrincipal AuthPrincipal principal,
        @RequestBody PostCreateRequest req) {
        postService.create(principal.getUserId(), req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PostsResponseDto>> getPosts(
        @RequestParam(value = "lastReadId", required = false) @Min(value = 1) Long lastReadId,
        @RequestParam(value = "limit", defaultValue = "10", required = false) int limit) {
        PostsResponseDto responsePostsDto = postService.getPosts(lastReadId, limit);
        return ResponseEntity.ok(ApiResponse.ok(responsePostsDto));
    }
}
