package com.hisujung.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hisujung.web.annotation.LoginUser;
import com.hisujung.web.dto.PortfolioListResponseDto;
import com.hisujung.web.dto.PortfolioResponseDto;
import com.hisujung.web.dto.PortfolioSaveRequestDto;
import com.hisujung.web.dto.PortfolioUpdateRequestDto;
import com.hisujung.web.entity.SessionUser;
import com.hisujung.web.json.JsonConverter;
import com.hisujung.web.service.PortfolioService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioApiController {

    private final PortfolioService portfolioService;

    @PostMapping("/new")
    public Long save(@RequestBody PortfolioSaveRequestDto requestDto) { //dto에 멤버id 포함해 보내도록 함

        return portfolioService.save(requestDto);
    }

    @PostMapping("update/{id}")
    public Long update(@PathVariable Long id, @RequestBody PortfolioUpdateRequestDto requestDto) {
        return portfolioService.update(id, requestDto);
    }

    @GetMapping("{id}")
    public PortfolioResponseDto findById(@PathVariable Long id) {
        return portfolioService.findById(id);
    }

    @GetMapping("portfoliolist")
    public String findMemberPortfolioList(@LoginUser SessionUser user, Model model){
        model.addAttribute("portfolios", portfolioService.findAllDescByMember(user.getId()));

        if(user != null) {
            model.addAttribute("userName", user.getUserName());
        }

        JsonArray result = new JsonArray();

        List<PortfolioListResponseDto> resultList = portfolioService.findAllDescByMember(user.getId());
        String json;

        try {
            json = JsonConverter.convertPListToJson(resultList);
            System.out.println(json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }

    }

    @GetMapping("list/{id}")
    public List<PortfolioListResponseDto> findMemberidPortfolioList(@PathVariable Long id){
        //model.addAttribute("portfolios", portfolioService.findAllDescByMember(id));

//        if(user != null) {
//            model.addAttribute("userName", user.getUserName());
//        }

        JsonArray result = new JsonArray();

        List<PortfolioListResponseDto> resultList = portfolioService.findAllDescByMember(id);

        resultList.forEach(p -> System.out.println(p));
        return resultList;
    }

    @GetMapping("/update/{id}")
    public PortfolioResponseDto portfolioUpdate(@PathVariable long id, Model model) {
        PortfolioResponseDto dto = portfolioService.findById(id);
        return dto;
    }

}
