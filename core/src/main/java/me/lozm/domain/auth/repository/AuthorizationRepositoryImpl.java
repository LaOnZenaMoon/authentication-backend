package me.lozm.domain.auth.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;


//    public List<AuthorizationVo> getAuthorizationList(Pageable pageable, SearchDto searchDto) {
//        return jpaQueryFactory
//                .select(Projections.fields(
//                        AuthorizationVo.class,
//                ))
//                .from(board)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }

}
