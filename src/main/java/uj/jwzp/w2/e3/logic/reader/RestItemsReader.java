package uj.jwzp.w2.e3.logic.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uj.jwzp.w2.e3.model.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service("itemsReader")
public class RestItemsReader implements ItemsReader {
    public List<Item> read() {
        RestTemplate restTemplate = new RestTemplate();
        Item[] items = restTemplate.getForObject("https://csv-items-generator.herokuapp.com/items", Item[].class);
        return Arrays.asList(items);
    }

    @Override
    public void close() {

    }
}
