package com.sb.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.sb.app.users.models.entity.User;

@RepositoryRestResource(path = "users")
public interface UserDao extends PagingAndSortingRepository<User, Long>{

	//public User findByUsernameAndEmail(String username, String email); // Query Metodo
	@RestResource(path = "findUsername")
	public User findByUsername(String username);
	
	@RestResource(path = "queryUsername")
	@Query("select u from User u where u.username=?1")
	public User obtenerPorUsername(@Param("user") String username);
}
