package com.tunes.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="album_recommendation")
public class AlbumRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="album_recommendation_id")
    private Integer albumRecommendationId;
    @NotNull(message = "album id field is required")
    @Column(name="album_id")
    private Integer albumId;
    @NotNull(message = "user id field is required")
    @Column(name="user_id")
    private Integer userId;
    @NotNull(message = "true or false response required")
    private boolean liked;

    public AlbumRecommendation() {
    }

    public AlbumRecommendation(Integer albumRecommendationId, Integer albumId, Integer userId, boolean liked) {
        this.albumRecommendationId = albumRecommendationId;
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public AlbumRecommendation(Integer albumId, Integer userId, boolean liked) {
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public Integer getAlbumRecommendationId() {
        return albumRecommendationId;
    }

    public void setAlbumRecommendationId(Integer albumRecommendationId) {
        this.albumRecommendationId = albumRecommendationId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumRecommendation that = (AlbumRecommendation) o;
        return liked == that.liked && Objects.equals(albumRecommendationId, that.albumRecommendationId) && Objects.equals(albumId, that.albumId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumRecommendationId, albumId, userId, liked);
    }

    @Override
    public String toString() {
        return "AlbumRecommendation{" +
                "albumRecommendationId=" + albumRecommendationId +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
