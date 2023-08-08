package com.example.trelloprojects.columns.repository;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.columns.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColumnsRepository extends JpaRepository<Columns, Long> {

    Long countColumnsByBoard(Board board);


    @Modifying
    @Query("UPDATE Columns SET position = position + 1 WHERE position >= :newPosition AND position < :oldPosition  AND id <> :id")
    void incrementBelowToPosition(@Param("newPosition") Long newPosition,
            @Param("oldPosition") Long oldPosition,
            @Param("id") String id);


    @Modifying
    @Query("UPDATE Columns SET position = position - 1 WHERE position <= :newPosition AND position > :oldPosition  AND id <> :id")
    void decrementAboveToPosition(@Param("newPosition") Long newPosition,
            @Param("oldPosition") Long oldPosition,
            @Param("id") String id);
}
