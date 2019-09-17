package com.fse.projmanagement.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projmanagement.dao.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

	UserDao findByUserId(Long userId);

}
