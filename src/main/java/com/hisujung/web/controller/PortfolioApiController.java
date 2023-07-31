package com.hisujung.web.controller;

import com.hisujung.web.annotation.LoginUser;
import com.hisujung.web.dto.PortfolioResponseDto;
import com.hisujung.web.dto.PortfolioSaveRequestDto;
import com.hisujung.web.dto.PortfolioUpdateRequestDto;
import com.hisujung.web.entity.SessionUser;
import com.hisujung.web.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PortfolioApiController {

    private final PortfolioService portfolioService;

    @PostMapping("/portfolio/new")
    public Long save(@RequestBody PortfolioSaveRequestDto requestDto) { //dto에 멤버id 포함해 보내도록 함

        return portfolioService.save(requestDto);
    }

    @PostMapping("portfolio/{id}")
    public Long update(@PathVariable Long id, @RequestBody PortfolioUpdateRequestDto requestDto) {
        return portfolioService.update(id, requestDto);
    }

    @GetMapping("portfolio/{id}")
    public PortfolioResponseDto findById(@PathVariable Long id) {
        return portfolioService.findById(id);
    }

    @GetMapping("member/portfoliolist")
    public String findMemberPortfolioList(@LoginUser SessionUser user, Model model){
        model.addAttribute("portfolios", portfolioService.findAllDescByMember(user.getId()));

        if(user != null) {
            model.addAttribute("userName", user.getUserName());
        }

        String a = "ok";

        return "a";
    }

}
