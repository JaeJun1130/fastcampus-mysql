package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJdbcTemplateRepository {
    private static final String TABLE = "member";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Member> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        RowMapper<Member> memberRowMapper = (ResultSet resultSet, int rowNum) ->
                Member.builder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .birthday(resultSet.getObject("birthday", LocalDate.class))
                        .createdAt(resultSet.getObject("createAt", LocalDateTime.class))
                        .build();

        Member member = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, memberRowMapper);

        return Optional.ofNullable(member);
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            return this.insert(member);
        }

        return this.update(member);
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        Number number = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        System.out.println("number = " + number);

        return Member.builder()
                .id(1L)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Member update(Member member) {
        return member;
    }
}
