package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDto> getNotice(@PathVariable Long noticeId) {
        NoticeDto noticeDto = noticeService.getNotice(noticeId);
        return ApiResponse.onSuccess(noticeDto);
    }

    // cursorId는 클라이언트가 받은 Notice의 가장 낮은 id를 의미하고, 처음 요청시 생략 가능
    @GetMapping("/list")
    public ApiResponse<CursorResult<NoticeDto>> getNoticeList(@RequestParam(required = false) Long cursorId,
                                                              @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        if (cursorId == null) cursorId = Long.MAX_VALUE;
        CursorResult<NoticeDto> noticeDtoList = noticeService.getNoticeList(cursorId, pageSize);
        return ApiResponse.onSuccess(noticeDtoList);
    }
}
