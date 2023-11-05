package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
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

    public Optional<MemberJpaEntity> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        RowMapper<MemberJpaEntity> memberRowMapper = (ResultSet resultSet, int rowNum) ->
                MemberJpaEntity.builder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .birthday(resultSet.getObject("birthday", LocalDate.class))
                        .createdAt(resultSet.getObject("createAt", LocalDateTime.class))
                        .build();

        MemberJpaEntity memberJpaEntity = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, memberRowMapper);

        return Optional.ofNullable(memberJpaEntity);
    }

    public MemberJpaEntity save(MemberJpaEntity memberJpaEntity) {
        if (memberJpaEntity.getId() == null) {
            return this.insert(memberJpaEntity);
        }

        return this.update(memberJpaEntity);
    }

    private MemberJpaEntity insert(MemberJpaEntity memberJpaEntity) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(memberJpaEntity);
        Number number = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        System.out.println("number = " + number);

        return MemberJpaEntity.builder()
                .id(1L)
                .nickname(memberJpaEntity.getNickname())
                .email(memberJpaEntity.getEmail())
                .birthday(memberJpaEntity.getBirthday())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private MemberJpaEntity update(MemberJpaEntity memberJpaEntity) {
        return memberJpaEntity;
    }
}
