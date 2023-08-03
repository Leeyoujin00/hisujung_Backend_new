package com.hisujung.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hisujung.web.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class UnivActivity extends BaseTimeEntity {

    @Id @Column(name = "univ_activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String postDepartment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDate;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime deadline;

    @Column(columnDefinition = "TEXT")
    private String content;


    @Builder
    public UnivActivity(String title, String postDepartment, LocalDateTime startDate, String content) {
        this.title = title;
        this.postDepartment = postDepartment;
        this.startDate = startDate;
        //this.deadline = deadline;
        this.content = content;
    }
}
