package com.hisujung.web.service;

import com.hisujung.web.dto.ExtActListResponseDto;
import com.hisujung.web.dto.LikeExtActRequestDto;
import com.hisujung.web.entity.ExternalAct;
import com.hisujung.web.entity.Member;
import com.hisujung.web.jpa.ExternalActRepository;
import com.hisujung.web.jpa.LikeExternalActRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExtActService {

    private final ExternalActRepository externalActRepository;
    private final LikeExternalActRepository likeExternalActRepository;

    //전체 대외활동 조회
    public List<ExtActListResponseDto> findAllByDesc() {
        return externalActRepository.findAll().stream().map(ExtActListResponseDto::new).collect(Collectors.toList());
    }

    //제목 키워드별 대외활동 조회
    public List<ExtActListResponseDto> findByTitle(String keyword) {
        return externalActRepository.findByTitleContaining(keyword).stream().map(ExtActListResponseDto::new).collect(Collectors.toList());
    }

    //========= 대외활동 좋아요 ========
    @Transactional
    public Long saveLike(Member member, Long actId) {
        ExternalAct e = externalActRepository.findById(actId).orElseThrow();
        LikeExtActRequestDto dto = LikeExtActRequestDto.builder()
                .member(member)
                .externalAct(e)
                .build();

        return likeExternalActRepository.save(dto.toEntity()).getId();
    }

}
