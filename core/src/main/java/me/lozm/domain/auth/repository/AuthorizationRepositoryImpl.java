package me.lozm.domain.auth.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lozm.domain.auth.vo.AuthorizationVo;
import me.lozm.object.dto.SearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
