package com.study.board.Service;

import com.study.board.Domain.Post;
import com.study.board.Repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostConcurrencyTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    void 조회수_100번_동시_증가시키면_100이_되는가() throws InterruptedException {
        Long postId = 1L;
        int threadCount = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    postService.increaseViewCount(postId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();  // 100개 스레드가 전부 끝날 때까지 대기

        Post post = postRepository.findById(postId).orElseThrow();
        System.out.println("최종 조회수: " + post.getViewCount());

        assertThat(post.getViewCount()).isEqualTo(100L);
    }
}