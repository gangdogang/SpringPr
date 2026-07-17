package com.study.board.Service;


import com.study.board.DTO.CommentRequestDto;
import com.study.board.DTO.CommentResponseDto;
import com.study.board.Domain.Comment;
import com.study.board.Domain.Post;
import com.study.board.Domain.User;
import com.study.board.Exception.NotFoundException;
import com.study.board.Repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final com.study.board.Repository.CommentRepository commentRepository;
    private final com.study.board.Repository.PostRepository postRepository;
    private final com.study.board.Repository.UserRepository userRepository;

    public CommentService(com.study.board.Repository.CommentRepository commentRepository, com.study.board.Repository.PostRepository postRepository, com.study.board.Repository.UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createComment(Long postId, CommentRequestDto request){
        // 어느 게시글에 달 것인가
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("게시글이 존재하지 않습니다."));

        //누가 댓글을 작성하는지 찾기
        User writer = userRepository.findById(request.getWriterId())
                .orElseThrow(()-> new NotFoundException("사용자가 존재하지 않습니다."));


        //찾은 게시글과 작성자를 이용해 댓글 생성
        Comment comment = Comment.builder()
                .content(request.getContent())
                .writer(writer)
                .post(post)
                .build();

        //새 댓글을 DB에 저장
        Comment savedComment = commentRepository.save(comment);

        //생성된 댓글의 ID를 호출자에게 돌려준다
        return savedComment.getId();
    }


    @Transactional
    public List<CommentResponseDto> getComments(Long postId){
         List<Comment> comments = commentRepository.findAllByPostId(postId);

         return comments.stream().map(comment -> new CommentResponseDto(
                 comment.getId(),
                 comment.getContent(),
                 comment.getWriter().getNickname()
         ))
                 .toList();


    }

}
