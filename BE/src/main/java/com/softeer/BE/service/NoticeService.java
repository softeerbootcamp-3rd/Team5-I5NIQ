package com.softeer.BE.service;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.domain.entity.Notice;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeDto.NoticeDetail getNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.NOTICE_NOT_FOUND));
        return NoticeDto.NoticeDetail.of(notice);
    }

    public CursorResult<NoticeDto.NoticeTitle> getNoticeList(Long cursorId, Integer pageSize) {
        List<Notice> noticeList = this.noticeRepository.findByIdLessThanOrderByIdDesc(cursorId, PageRequest.of(0, pageSize)).getContent();
        List<NoticeDto.NoticeTitle> noticeTitleList = noticeList.stream().map(NoticeDto.NoticeTitle::of).toList();
        boolean hasNext = false;
        if(!noticeTitleList.isEmpty()) {
            Long lastId = noticeTitleList.get(noticeTitleList.size()-1).getId();
            hasNext = this.noticeRepository.existsByIdLessThan(lastId);
        }
        return new CursorResult<>(noticeTitleList, hasNext);
    }
}
