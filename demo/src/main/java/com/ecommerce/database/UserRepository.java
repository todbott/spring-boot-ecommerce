// package com.ecommerce.database;

// import org.springframework.data.repository.CrudRepository;

// // This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// // CRUD refers Create, Read, Update, Delete

// public interface UserRepository extends CrudRepository<com.ecommerce.database.User, Integer> {

// }

package com.ecommerce.database;

//import com.ecommerce.database.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String username);
}