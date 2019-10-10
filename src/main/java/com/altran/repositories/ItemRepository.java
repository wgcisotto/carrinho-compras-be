package com.altran.repositories;

import com.altran.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {

    @Override
    void delete(Item deleted);

}
