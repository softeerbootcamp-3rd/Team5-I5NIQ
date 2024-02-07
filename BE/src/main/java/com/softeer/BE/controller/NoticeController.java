package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.NoticeDto;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDto> getNotice(@PathVariable Long noticeId) {
        NoticeDto noticeDto = noticeService.getNotice(noticeId);
        return ApiResponse.onSuccess(noticeDto);
    }

    // cursorId는 클라이언트가 받은 Notice의 가장 낮은 id를 의미하고, 처음 요청시 0을 보냄
    @GetMapping("/list")
    public ApiResponse<List<NoticeDto>> getNoticeList(@RequestParam Long cursorId, @RequestParam Integer pageSize) {
        if (pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        List<NoticeDto> noticeDtoList = noticeService.getNoticeList(cursorId, pageSize);
        return ApiResponse.onSuccess(noticeDtoList);
    }

    @PostMapping("/create")
    public ApiResponse<NoticeDto> postNotice(@RequestBody NoticeDto noticeDto) {
        this.noticeService.createNotice(noticeDto);
        return ApiResponse.onSuccess(null);
    }
}
