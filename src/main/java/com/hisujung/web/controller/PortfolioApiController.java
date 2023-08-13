package com.hisujung.web.controller;

import com.hisujung.web.ApiResponse;
import com.hisujung.web.dto.PortfolioListResponseDto;
import com.hisujung.web.dto.PortfolioResponseDto;
import com.hisujung.web.dto.PortfolioSaveRequestDto;
import com.hisujung.web.dto.PortfolioUpdateRequestDto;
import com.hisujung.web.entity.Member;
import com.hisujung.web.service.PortfolioService;
import com.hisujung.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioApiController {

    private final PortfolioService portfolioService;
    private final UserService userService;

    //회원의 포트폴리오 생성
    @PostMapping("/new")
    public ApiResponse<Long> save(@RequestBody PortfolioSaveRequestDto requestDto, Authentication auth) {

        Member member = userService.getLoginUserByLoginId(auth.getName());

        Long result = portfolioService.save(member, requestDto);
        if(result == -1L) {
            return (ApiResponse<Long>) ApiResponse.createError("포트폴리오 업데이트에 실패했습니다.");
        }
        return ApiResponse.createSuccess(result);
    }

    //회원의 포트폴리오 업데이트
    @PostMapping("update/id")
    public ApiResponse<Long> update(@RequestParam Long id, @RequestBody PortfolioUpdateRequestDto requestDto) {
        Long result = portfolioService.update(id, requestDto);
        if(result == -1L) {
            return (ApiResponse<Long>) ApiResponse.createError("포트폴리오 업데이트에 실패했습니다.");
        }
        return ApiResponse.createSuccess(result);
    }

    //회원의 포트폴리오 포트폴리오id(PK) 로 조회
    @GetMapping("id")
    public ApiResponse<PortfolioResponseDto> findById(@RequestParam Long id) {
        PortfolioResponseDto result = portfolioService.findById(id);
        if(result == null) {
            return (ApiResponse<PortfolioResponseDto>) ApiResponse.createError("포트폴리오 업데이트에 실패했습니다.");
        }

        return ApiResponse.createSuccess(result);
    }

    //로그인한 회원의 포트폴리오 조회
    @GetMapping("/portfoliolist")
    public ApiResponse<List<PortfolioListResponseDto>> findMemberPortfolioList(Authentication auth){

        Member loginUser = userService.getLoginUserByLoginId(auth.getName());

        if(loginUser == null) {
            return (ApiResponse<List<PortfolioListResponseDto>>)ApiResponse.createError("회원 조회에 실패하였습니다.");
        }
        List<PortfolioListResponseDto> resultList = portfolioService.findAllDescByMember(loginUser.getId());
        if(resultList == null) {
            return (ApiResponse<List<PortfolioListResponseDto>>)ApiResponse.createError("포트폴리오가 존재하지 않습니다.");
        }
        return ApiResponse.createSuccess(resultList);
    }


    //개별 포트폴리오 조회
//    @GetMapping("/portfolio/{id}")
//    public ApiResponse<PortfolioResponseDto> portfolioUpdate(@PathVariable long id, Model model) {
//        PortfolioResponseDto result = portfolioService.findById(id);
//        if (result == null) {
//            return (ApiResponse<PortfolioResponseDto>) ApiResponse.createError("해당 포트폴리오가 존재하지 않습니다.");
//        }
//        return ApiResponse.createSuccess(result);
//    }

    //포트폴리오 삭제
    @DeleteMapping("/portfolio/id")
    public ApiResponse<Long> delete(@RequestParam Long id) {
        portfolioService.delete(id);
        return ApiResponse.createSuccess(id);
    }
}
