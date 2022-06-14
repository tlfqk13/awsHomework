package com.example.awshomework.service.posts;

import com.example.awshomework.domain.posts.PostRepository;
import com.example.awshomework.domain.posts.Posts;
import com.example.awshomework.web.dto.PostsResponseDto;
import com.example.awshomework.web.dto.PostsSaveRequestDto;
import com.example.awshomework.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" +id ));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
