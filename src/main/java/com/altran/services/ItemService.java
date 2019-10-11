package com.altran.services;

import com.altran.exceptions.ValidationException;
import com.altran.models.Item;
import com.altran.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Iterable<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Optional<Item> findById(String id){
        return itemRepository.findById(id);
    }

    public Item update(String id, Item item) throws ValidationException {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if(!optionalItem.isPresent())
            throw new ValidationException("003","Item not found");

        Item i = optionalItem.get();

        if(item.getName() != null)
            i.setName(item.getName());
        if(item.getPrice() != null)
            i.setPrice(item.getPrice());

        itemRepository.save(i);

        return i;

    }

    public void delete(String id) throws ValidationException {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if(!optionalItem.isPresent())
            throw new ValidationException("003","Item not found");

        Item item = optionalItem.get();
        itemRepository.delete(item);
    }
}
