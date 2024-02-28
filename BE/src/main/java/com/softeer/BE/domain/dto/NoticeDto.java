package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;
public class NoticeDto {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class NoticeTitle{
        private Long id;
        private String title;
        private LocalDateTime createdAt;
        public static NoticeDto.NoticeTitle of(Notice n){
            return new NoticeTitle(n.getId(), n.getTitle(), n.getCreatedAt());
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class NoticeDetail{
        private Long id;
        private String title;
        private String content;
        private String imageUrl;
        private String imageName;
        private LocalDateTime createdAt;
        public static NoticeDto.NoticeDetail of(Notice n){
            return new NoticeDetail(n.getId(), n.getTitle(), n.getContent(), n.getImageUrl(), n.getImageName(), n.getCreatedAt());
        }
    }
}
