package com.utc.rental.rental.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rental.rental.entity.Electric_Water;

public interface ElectricWaterRepo extends JpaRepository<Electric_Water, String> {
	@Query("SELECT a FROM Electric_Water a " + "WHERE a.room.id = :x " + "AND (a.month BETWEEN :m1 AND :m2) "
			+ "AND (a.year BETWEEN :y1 AND :y2) " + "AND a.type = :t ORDER BY a.updateAt DESC")
	List<Electric_Water> findEW(@Param("x") Long roomId, @Param("m1") int month1, @Param("m2") int month2,
			@Param("y1") int year1, @Param("y2") int year2, @Param("t") String type);

	@Query("SELECT a FROM Electric_Water a WHERE a.room.id = :roomId AND a.type = :type AND a.recordDate BETWEEN :startDate AND :endDate ORDER BY a.updateAt DESC")
	List<Electric_Water> findByRoomAndTypeAndDateRange(@Param("roomId") Long roomId, @Param("type") String type,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query("SELECT a FROM Electric_Water a WHERE a.room.id = :roomId ORDER BY a.updateAt DESC")
	Page<Electric_Water> findByRoom(@Param("roomId") Long roomId, Pageable pageable);
	
	@Query("""
			SELECT ew
			FROM Electric_Water ew
			WHERE (:type IS NULL OR ew.type = :type)
			  AND (:roomId IS NULL OR ew.room.id = :roomId)
			  AND (ew.year * 100 + ew.month BETWEEN :m1 AND :m2)
			  order by ew.updateAt DESC
			""")
		Page<Electric_Water> search(
		    @Param("type") String type,
		    @Param("roomId") Long roomId,
		    @Param("m1") Integer m1,
		    @Param("m2") Integer m2,
		    Pageable pageable
		);

}
