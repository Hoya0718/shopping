package com.nayo.shop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Integer> {

    @Query(value = "SELECT s FROM Sales s JOIN FETCH s.member")
    List<Sales> customFindAll();
}
