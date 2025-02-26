package com.example.test.board;

import com.example.test.comment.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시판 기능")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    @PostMapping("/register")
    @Operation(summary = "게시글 등록", description = "게시글을 등록하는 기능입니다.")
    public void register(@RequestBody BoardDto.RegisterReq req) {
        boardService.register(req);
    }

    @GetMapping("/list")
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회하는 기능입니다.")
    public ResponseEntity<List<BoardDto.Info>> list() {
        return ResponseEntity.ok(boardService.list());
    }

    @GetMapping("/{idx}")
    @Operation(summary = "게시글 단건 조회", description = "단건 게시글을 조회하는 기능입니다.")
    public ResponseEntity<BoardDto.Detail> get(@PathVariable Long idx) {
        return ResponseEntity.ok(boardService.get(idx));
    }

    @PostMapping("/{idx}/comment/register")
    @Operation(summary = "댓글 등록", description = "댓글을 등록하는 기능입니다.")
    public void registerComment(@PathVariable Long idx,@RequestBody CommentDto.RegisterReq req) {
        boardService.registerComment(idx,req);
    }

}
