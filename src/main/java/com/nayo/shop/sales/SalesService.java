package com.nayo.shop.sales;

import lombok.RequiredArgsConstructor;
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
    public Optional<Sales> sales(Integer id) {
        return salesRepository.findById(id);
    }
}
