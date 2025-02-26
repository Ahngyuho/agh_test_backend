package com.example.test.board;

import com.example.test.comment.Comment;
import com.example.test.comment.CommentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    @Getter
    @Builder
    public static class RegisterReq{
        @Schema(description = "게시글 제목", example = "test title")
        private String title;
        @Schema(description = "게시글 내용", example = "test content")
        private String content;
        @Schema(description = "게시글 작성자", example = "test writer")
        private String writer;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Info{
        private Long idx;
        private String title;
        private String content;
        private String writer;
        private int commentCount;

        public static Info fromEntity(Board board){
            return Info.builder()
                    .idx(board.getIdx())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .commentCount(board.getComments().size())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Detail{
        private String title;
        private String content;
        private String writer;
        private List<CommentDto.Detail> comments;

        public static Detail fromEntity(Board board){
            return Detail.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .comments(board.getComments().stream().map(CommentDto.Detail::fromEntity).collect(Collectors.toList()))
                    .build();
        }
    }
}
