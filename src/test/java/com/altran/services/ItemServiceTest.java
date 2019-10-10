package com.altran.services;

import com.altran.models.Item;
import com.altran.repositories.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    private Item item;

    private static final String ID = "5d9eb050b1746f1d188c8e55";

    @Before
    public void init(){
        item = Item.builder().name("Item 1").price(10L).build();
    }

    @Test
    public void save(){

        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

        Item iCreated = itemService.save(item);

        Assert.assertEquals(item, iCreated);

    }

    @Test
    public void findById(){
        Mockito.when(itemRepository.findById(ID)).thenReturn(Optional.ofNullable(item));

        Optional<Item> itemFound = itemService.findById(ID);

        Assert.assertEquals(item.getName(), itemFound.get().getName());
    }

    @Test
    public void update() throws Exception {
        Mockito.when(itemRepository.findById(ID)).thenReturn(Optional.ofNullable(item));

        Item itemUpdated = itemService.update(ID, item);

        Assert.assertEquals(item.getName(), itemUpdated.getName());
    }

}
