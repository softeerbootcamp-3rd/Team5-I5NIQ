package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Notice;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private Long id;

    private String title;

    private String content;

    private String imageUrl;

    private String imageName;

    public Notice toEntity() {
        return Notice.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .imageUrl(this.imageUrl)
                .imageName(this.imageName)
                .build();
    }

    public static NoticeDto toDto(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imageUrl(notice.getImageUrl())
                .imageName(notice.getImageName())
                .build();
    }
}
