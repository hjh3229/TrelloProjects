package com.example.trelloprojects.card.repository;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.columns.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Long countCardsByColumns(Columns columns);

    //무식하지만 직관적
    //reorder orderbyposition 칼럼을 다 가져와서 -> for문

    //지정된 위치로 보낼때, 앞에 숫자는 빼줘야되고, 중간에 숫자는 다시 확인해야되고
    //업데이트 를 두번달리는게 맞다
    //new, old 를 나눠서 진행
    //position 이 old->new (OLD,new 비교)

    @Transactional
    @Modifying
    @Query("UPDATE Card SET position = position + 1 WHERE position >= :newPosition AND position < :oldPosition AND columns.id = :id")
    void incrementBelowToPosition(@Param("newPosition") Long newPosition,
            @Param("oldPosition") Long oldPosition, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Card SET position = position - 1 WHERE position <= :newPosition AND position > :oldPosition AND columns.id = :id")
    void decrementAboveToPosition(@Param("newPosition") Long newPosition,
            @Param("oldPosition") Long oldPosition, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Card SET position = position - 1 WHERE position >= :position AND columns.id = :id")
    void decrementBelow(@Param("position") Long position,
            @Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Card SET position = position + 1 WHERE position <= :position AND Columns = :columns")
    void incrementBelow(@Param("position") Long position, @Param("columns") Columns columns);
}
