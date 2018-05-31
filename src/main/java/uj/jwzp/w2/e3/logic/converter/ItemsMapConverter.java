package uj.jwzp.w2.e3.logic.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsMapConverter extends XmlAdapter<ItemsMapConverter.AdaptedMap, Map<uj.jwzp.w2.e3.model.Item, Integer>> {
        public static class AdaptedMap {
            public List<Item> item = new ArrayList<Item>();
        }

        public static class Item {
            @XmlElement
            private String name;
            @XmlElement
            private BigDecimal price;
            @XmlElement
            public Integer count;
        }

        @Override
        public Map<uj.jwzp.w2.e3.model.Item, Integer> unmarshal(AdaptedMap adaptedMap) throws Exception {
            Map<uj.jwzp.w2.e3.model.Item, Integer> map = new HashMap<uj.jwzp.w2.e3.model.Item, Integer>();
            for(Item item : adaptedMap.item) {
                map.put(new uj.jwzp.w2.e3.model.Item(item.name, item.price), item.count);
            }
            return map;
        }

        @Override
        public AdaptedMap marshal(Map<uj.jwzp.w2.e3.model.Item, Integer> map) throws Exception {
            AdaptedMap adaptedMap = new AdaptedMap();
            for(Map.Entry<uj.jwzp.w2.e3.model.Item, Integer> mapEntry : map.entrySet()) {
                Item entry = new Item();
                uj.jwzp.w2.e3.model.Item item = mapEntry.getKey();
                entry.name = item.getName();
                entry.price = item.getPrice();
                entry.count = mapEntry.getValue();
                adaptedMap.item.add(entry);
            }
            return adaptedMap;
        }
}
