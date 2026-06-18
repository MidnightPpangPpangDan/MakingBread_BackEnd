package io.mb_backend.makingbackend.user.repository;

import io.mb_backend.makingbackend.common.constant.ErrorCode;
import io.mb_backend.makingbackend.common.exception.BusinessException;
import io.mb_backend.makingbackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
