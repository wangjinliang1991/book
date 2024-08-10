package com.example.book.repo;

import com.example.book.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUserNameAndUserPassword(String account, String password);

    UserInfo findByUserName(String userName);
}
