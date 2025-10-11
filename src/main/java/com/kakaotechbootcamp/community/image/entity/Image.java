package com.kakaotechbootcamp.community.image.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(nullable = false, unique = true)
    private String originalName;

    @Column(nullable = false)
    private String objectKey;

    private Image(String originalName, String objectKey) {
        this.originalName = originalName;
        this.objectKey = objectKey;
    }

    static public Image create(String originalName, String objectKey) {
        return new Image(originalName, objectKey);
    }
}
