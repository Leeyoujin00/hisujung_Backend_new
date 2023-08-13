package com.hisujung.web.dto;

import com.hisujung.web.entity.Member;
import com.hisujung.web.entity.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioSaveRequestDto {

    private String title;
    private String urlLink;
    private String description;

    @Builder
    public PortfolioSaveRequestDto(String title, String urlLink, String description) {
        this.title = title;
        this.urlLink = urlLink;
        this.description = description;
    }

    public Portfolio toEntity(Member member) {
        return Portfolio.builder()
                .member(member)
                .title(title)
                .urlLink(urlLink)
                .description(description)
                .build();
    }

}
