package com.example.fastcampusmysql.domain.member.entity;


import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class MemberJpaEntity {
    private final static Long NAME_MAX_LENGTH = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private LocalDate birthday;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public MemberJpaEntity(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        validationNickname(nickname);

        this.id = id;

        this.nickname = Objects.requireNonNull(nickname);
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static MemberJpaEntity of(RegisterMemberCommand registerMemberCommand) {
        return MemberJpaEntity.builder()
                .nickname(registerMemberCommand.nickname())
                .email(registerMemberCommand.email())
                .birthday(registerMemberCommand.birthday())
                .build();
    }

    void validationNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
