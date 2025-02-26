package com.example.test.board;

import com.example.test.comment.CommentDto;
import com.example.test.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public List<BoardDto.Info> list() {
        List<BoardDto.Info> result = new ArrayList<>();
        for(Board board : boardRepository.findAll()) {
            result.add(BoardDto.Info.fromEntity(board));
        }
        return result;
    }

    public BoardDto.Detail get(Long idx) {
        Board board = boardRepository.findByIdx(idx).orElseThrow();
        return BoardDto.Detail.fromEntity(board);
    }


    public void register(BoardDto.RegisterReq req) {
        boardRepository.save(req.toEntity());
    }

    public void registerComment(Long idx, CommentDto.RegisterReq req) {
        Board board = boardRepository.findByIdx(idx).orElseThrow();
        commentRepository.save(
                req.toEntity(board)
        );
    }
}
