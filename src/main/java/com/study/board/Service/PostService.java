package com.study.board.Service;

import com.study.board.DTO.PostUpdateRequestDto;
import com.study.board.Domain.Post;
import com.study.board.Domain.User;
import com.study.board.DTO.PostRequestDto;
import com.study.board.DTO.PostResponseDto;
import com.study.board.Exception.NotFoundException;
import com.study.board.Repository.PostRepository;
import com.study.board.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createPost(PostRequestDto requestDto) {
        User writer = userRepository.findById(requestDto.getWriterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(writer)
                .build();

        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다"));

        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getWriter().getNickname(),
                post.getViewCount(), post.getLikeCount());
    }

    public List<PostResponseDto> getPostList() {
        return postRepository.findAllWithWriter()
                .stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getWriter().getNickname(),
                        post.getViewCount(),
                        post.getLikeCount()
                ))
                .toList();
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));

        postRepository.delete(post);
    }


    @Transactional
    public void increaseViewCount(Long postId){
        int updatedCount = postRepository.increaseViewCount(postId);

        if(updatedCount==0){
            throw new NotFoundException("존재하지 않는 게시글입니다.");
        }
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("존재하지 않는 게시글입니다"));

        post.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );

    }

}