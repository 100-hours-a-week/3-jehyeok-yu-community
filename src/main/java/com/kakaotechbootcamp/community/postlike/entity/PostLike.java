package com.kakaotechbootcamp.community.postlike.entity;

import com.kakaotechbootcamp.community.post.entity.Post;
import com.kakaotechbootcamp.community.user.entity.User;
import com.kakaotechbootcamp.community.utils.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private boolean isDeleted;

    private PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
        this.isDeleted = false;
    }

    // 팩토리 메서드
    static public PostLike create(Post post, User user) {
        return new PostLike(post, user);
    }
}
