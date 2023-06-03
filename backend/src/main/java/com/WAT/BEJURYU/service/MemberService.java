package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 정보입니다."));
    }

    public boolean notExistById(final Long id) {
        return !memberRepository.existsById(id);
    }

    @Transactional
    public Member save(final Member member) {
        return memberRepository.save(member);
    }
    @Transactional
    public MemberChangeNicknameResponse updateNickname(MemberChangeNicknameRequest request) {
        final Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        member.updateNickname(request.getNickname());

        return new MemberChangeNicknameResponse(request.getNewNickname());
    }
}
