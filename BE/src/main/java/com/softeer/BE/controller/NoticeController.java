package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDto.NoticeDetail> getNotice(@PathVariable Long noticeId) {
        NoticeDto.NoticeDetail detail = noticeService.getNotice(noticeId);
        return ApiResponse.onSuccess(detail);
    }

    // cursorId는 클라이언트가 받은 Notice의 가장 낮은 id를 의미하고, 처음 요청시 생략 가능
    @GetMapping("/list")
    public ApiResponse<CursorResult<NoticeDto.NoticeTitle>> getNoticeList(@RequestParam(required = false) Long cursorId,
                                                              @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        if (cursorId == null) cursorId = Long.MAX_VALUE;
        CursorResult<NoticeDto.NoticeTitle> noticeTitleList = noticeService.getNoticeList(cursorId, pageSize);
        return ApiResponse.onSuccess(noticeTitleList);
    }
}
