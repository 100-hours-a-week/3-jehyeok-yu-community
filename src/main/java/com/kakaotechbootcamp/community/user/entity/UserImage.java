package com.kakaotechbootcamp.community.user.entity;

import com.kakaotechbootcamp.community.image.entity.Image;
import com.kakaotechbootcamp.community.utils.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImage extends BaseEntity {

    @Id
    public Long userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id", nullable = false, unique = true)
    private Image image;

    @Column(nullable = false, length = 255, unique = true)
    private String thumbnailObjectKey;

    private UserImage(User user, Image image, String thumbnailObjectKey) {
        this.user = user;
        this.image = image;
        this.thumbnailObjectKey = thumbnailObjectKey;
    }

    // 팩토리 메서드
    static public UserImage create(User user, Image image, String thumbnailObjectKey) {
        return new UserImage(user, image, thumbnailObjectKey);
    }
}
