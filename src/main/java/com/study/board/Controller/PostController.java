package com.study.board.Controller;

import com.study.board.DTO.PostRequestDto;
import com.study.board.DTO.PostResponseDto;
import com.study.board.DTO.PostUpdateRequestDto;
import com.study.board.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@Valid @RequestBody PostRequestDto requestDto) {
        Long postId = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        return ResponseEntity.ok(postService.getPostList());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{postId}/view")
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long postId){
        postService.increaseViewCount(postId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId,
    @Valid @RequestBody PostUpdateRequestDto requestDto){
        postService.updatePost(postId, requestDto);

        return ResponseEntity.noContent().build();
    }
}