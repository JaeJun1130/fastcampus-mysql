package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberJpaEntityTest {
    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    public void testChangeNickName() {
        MemberJpaEntity memberJpaEntity = MemberFixtureFactory.create();
        String expected = "test";

        memberJpaEntity.changeNickname(expected);

        Assertions.assertEquals(expected, memberJpaEntity.getNickname());
    }

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 없다. (10글자 초과시 실패)")
    public void testNicknameMaxLength() {
        MemberJpaEntity memberJpaEntity = MemberFixtureFactory.create();
        String expected = "testtesttesttest";

        Assertions.assertThrows(IllegalArgumentException.class, () -> memberJpaEntity.changeNickname(expected));
    }
}