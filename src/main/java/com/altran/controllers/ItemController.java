package com.altran.controllers;

import com.altran.exceptions.ValidationException;
import com.altran.models.Item;
import com.altran.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method= RequestMethod.GET, value="/items")
    public Iterable<Item> items() {
        return itemService.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/items")
    public Item save(@RequestBody @Valid Item item) {
        return itemService.save(item);
    }

    @RequestMapping(method=RequestMethod.GET, value="/items/{id}")
    public Optional<Item> show(@PathVariable String id) {
        return itemService.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/items/{id}")
    public Item update(@PathVariable String id, @RequestBody @Valid Item item) throws Exception {
        return itemService.update(id, item);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/items/{id}")
    public String delete(@PathVariable String id) throws ValidationException {
        itemService.delete(id);

        return "";
    }




}
