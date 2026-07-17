package com.study.board.Controller;

import com.study.board.DTO.CommentRequestDto;
import com.study.board.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/post")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Long> createComment(@Valid @PathVariable Long postId, @RequestBody CommentRequestDto request){
        Long commentId = commentService.createComment(postId, request);
        return ResponseEntity.ok(commentId);
    }


}
