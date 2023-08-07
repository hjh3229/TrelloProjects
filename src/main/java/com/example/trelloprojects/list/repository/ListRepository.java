package com.example.trelloprojects.list.repository;

import com.example.trelloprojects.list.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, Long> {

}
