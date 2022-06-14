package com.example.awshomework.domain.posts;

import com.example.awshomework.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 엔티티 클래스에는 절대 setter 메소드를 만들지 않는다.
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        // 생성자나 빌더나 생성 시점에 값을 채워주는 역할
        // 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없음.
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
