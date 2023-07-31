package com.hisujung.web.entity;

import com.hisujung.web.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class ExternalAct extends BaseTimeEntity {

    @Id @Column(name = "external_act_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String info;

    private String link;

    private LocalDateTime startDate;

    private LocalDateTime deadline;

    @Builder
    public ExternalAct(String title, String info, String link, LocalDateTime startDate, LocalDateTime deadline) {
        this.title = title;
        this.info = info;
        this.link = link;
        this.startDate = startDate;
        this.deadline = deadline;

    }



}
