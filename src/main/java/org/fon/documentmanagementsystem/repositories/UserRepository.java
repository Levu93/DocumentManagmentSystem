/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.repositories;

import java.util.List;
import org.fon.documentmanagementsystem.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vukasin
 */
@Repository
public interface UserRepository extends CrudRepository<User, String>{
    
    User findByUsernameAndPassword(String username, String password);
    
//    @Query("SELECT u FROM user u WHERE u.idPodsistema = :idp")
//    List<User> findBySubsystem(@Param("idp") long idp);
    
}
