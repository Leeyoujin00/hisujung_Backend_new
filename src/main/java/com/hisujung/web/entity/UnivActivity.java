package com.hisujung.web.entity;

import com.hisujung.web.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public UnivActivity(String title, String postDepartment) {
        this.title = title;
        this.postDepartment = postDepartment;
    }
}
