package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return MemberDto.of(memberReadService.getMember(id));
    }

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand registerMemberCommand) {
        return MemberDto.of(memberWriteService.register(registerMemberCommand));
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return MemberDto.of(memberReadService.getMember(id));
    }
}
