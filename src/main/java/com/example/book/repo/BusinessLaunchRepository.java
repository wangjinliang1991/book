package com.example.book.repo;

import com.example.book.pojo.BusinessLaunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangjl
 * @date 2024/8/25
 */
@Repository
public interface BusinessLaunchRepository extends JpaRepository<BusinessLaunch,Integer> {

}
