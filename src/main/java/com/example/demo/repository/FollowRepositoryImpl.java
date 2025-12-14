package com.example.demo.repository;

import com.example.demo.domain.follows.QFollow;
import com.example.demo.domain.users.QUser;
import com.example.demo.dto.FollowerDto;
import com.example.demo.dto.FollowingDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FollowingDto> findFollowingDtos(Long userId, Pageable pageable) {
        QFollow follow = QFollow.follow;
        QUser user = QUser.user;

        List<FollowingDto> content = queryFactory
                .select(Projections.constructor(FollowingDto.class,
                        follow.following.id, follow.following.name
                ))
                .from(follow)
                .join(follow.following, user)
                .where(follow.follower.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.follower.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    @Override
    public Page<FollowerDto> findFollowerDtos(Long userId, Pageable pageable) {
        QFollow follow = QFollow.follow;
        QUser user = QUser.user;

        List<FollowerDto> content = queryFactory
                .select(Projections.constructor(FollowerDto.class,
                        follow.follower.id, follow.following.name
                ))
                .from(follow)
                .join(follow.follower, user)
                .where(follow.following.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.following.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }
}
