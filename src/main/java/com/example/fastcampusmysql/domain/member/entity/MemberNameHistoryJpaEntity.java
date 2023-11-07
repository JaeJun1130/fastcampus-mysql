package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "member_name_history")
public class MemberNameHistoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public MemberNameHistoryJpaEntity(Long id, MemberJpaEntity member, String name, LocalDateTime createdAt) {
        this.id = id;
        this.member = Objects.requireNonNull(member);
        this.name = Objects.requireNonNull(name);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
