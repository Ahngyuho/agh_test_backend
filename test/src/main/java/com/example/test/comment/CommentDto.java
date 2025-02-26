package com.example.test.comment;

import com.example.test.board.Board;
import lombok.Builder;
import lombok.Getter;

public class CommentDto {



    @Builder
    @Getter
    public static class RegisterReq{
        private String content;
        private String writer;

        public Comment toEntity(Board board) {
            return Comment.builder()
                    .content(content)
                    .writer(writer)
                    .board(board)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Detail {
        private String comment;
        private String writer;

        public static Detail fromEntity(Comment comment) {
            return Detail.builder()
                    .comment(comment.getContent())
                    .writer(comment.getWriter())
                    .build();
        }
    }

}
