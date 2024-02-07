package com.softeer.BE.service;

import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.domain.entity.Notice;
import com.softeer.BE.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void createNotice(NoticeDto noticeDto) {
        if(noticeDto.getTitle().isEmpty()) throw new RuntimeException("title is empty");
        if(noticeDto.getContent().isEmpty()) throw new RuntimeException("content is empty");
        this.noticeRepository.save(noticeDto.toEntity());
    }

    public NoticeDto getNotice(Long noticeId) {
        Optional<Notice> detail = noticeRepository.findById(noticeId);
        if(detail.isEmpty()) throw new RuntimeException("not found");
        return NoticeDto.toDto(detail.get());
    }

    public List<NoticeDto> getNoticeList(Long cursorId, Long pageSize) {
        List<Notice> noticeList = (cursorId == 0L) ?
                this.noticeRepository.findAllByOrderByIdDesc(pageSize):
                this.noticeRepository.findByIdLessThanOrderByIdDesc(cursorId, pageSize);
        return noticeList.stream()
                .map(NoticeDto::toDto)
                .toList();
    }
}
