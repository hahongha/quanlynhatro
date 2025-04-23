//package com.utc.rental.rental.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.utc.rental.rental.entity.RoomType;
//
//public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {
//	Optional<RoomType> findByName(String name);
//	
//	boolean existsById(Long id);
//	
//	boolean existsByName(String name);
//	
//	@Query("SELECT r FROM RoomType r WHERE r.name LIKE %:x% and (r.size between :m and :n) AND " 
//			+ "r.furniture LIKE %:furnitureList%")
//	Page<RoomType> search(@Param("x") String name, @Param("m")Long minSize, @Param("n")Long maxSize,@Param("furnitureList") String furnitureList,
//			Pageable pageable);
//	
//	@Query("SELECT r FROM RoomType r WHERE r.name LIKE %:x% and (r.size between :m and :n)")
//	Page<RoomType> search2(@Param("x") String name, @Param("m")Long minSize, @Param("n")Long maxSize, Pageable pageable);
//	
//}
