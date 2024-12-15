package com.nayo.shop.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public Map<String, Integer> updateNum(Integer num){
        Map<String, Integer> map = new HashMap<>();
        if(num <= 1){
            map.put("updatedNum",1);
            return map;
        }
        map.put("updatedNum",num);
        return map;
    }
    public Integer saveSales(SalesDTO salesDTO, Authentication auth) {
        Sales sales = new Sales();

        int price = salesDTO.getPrice() * salesDTO.getCount();

        sales.setCount(salesDTO.getCount());
        sales.setPrice(price);
        sales.setItemName(salesDTO.getItemName());
        sales.setMember_id(auth.getName());
        sales.setSeller(salesDTO.getSeller());

        Sales saveSales = salesRepository.save(sales);

        Integer id = saveSales.getId();
        return id;
    }
    public Optional<Sales> findById(Integer id){
        System.out.println("찾을 아이템 id");
        return salesRepository.findById(id);
    }
}
