package com.nayo.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {//<Entity명, id컬럼타입>

}
