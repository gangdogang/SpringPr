package com.study.board.Controller;

import com.study.board.DTO.CommentRequestDto;
import com.study.board.DTO.CommentResponseDto;
import com.study.board.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/post")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDto request){
        Long commentId = commentService.createComment(postId, request);
        return ResponseEntity.ok(commentId);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId){
        List<CommentResponseDto> response = commentService.getComments(postId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto request){
        commentService.updateComment(commentId, request);
        return ResponseEntity.noContent().build();
    }

}
