package com.softeer.BE.service;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.domain.entity.Notice;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeDto getNotice(Long noticeId) {
        Notice detail = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.NOTICE_NOT_FOUND));
        return NoticeDto.toDto(detail);
    }

    public CursorResult<NoticeDto> getNoticeList(Long cursorId, Integer pageSize) {
        List<Notice> noticeList = this.noticeRepository.findByIdLessThanOrderByIdDesc(cursorId, PageRequest.of(0, pageSize)).getContent();
        List<NoticeDto> noticeDtoList = noticeList.stream().map(NoticeDto::toDto).toList();
        boolean hasNext = false;
        if(!noticeDtoList.isEmpty()) {
            Long lastId = noticeDtoList.get(noticeDtoList.size()-1).getId();
            hasNext = this.noticeRepository.existsByIdLessThan(lastId);
        }
        return new CursorResult<>(noticeDtoList, hasNext);
    }
}
