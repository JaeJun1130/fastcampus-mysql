package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {
    public static MemberJpaEntity create(){
        EasyRandomParameters parameter = new EasyRandomParameters();
        return new EasyRandom(parameter).nextObject(MemberJpaEntity.class);
    }

    public static MemberJpaEntity create(Long seed) {
        EasyRandomParameters parameter = new EasyRandomParameters().seed(seed);
        return new EasyRandom(parameter).nextObject(MemberJpaEntity.class);
    }
}
