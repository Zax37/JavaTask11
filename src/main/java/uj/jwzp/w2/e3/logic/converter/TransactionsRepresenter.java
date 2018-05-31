package uj.jwzp.w2.e3.logic.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;
import uj.jwzp.w2.e3.model.Item;
import uj.jwzp.w2.e3.model.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionsRepresenter extends Representer {
    public TransactionsRepresenter() {
        this.representers.put(Transaction.class, new RepresentTransaction());
        this.representers.put(ItemLine.class, new RepresentItemLine());
    }

    private class RepresentTransaction implements Represent {
        public Node representData(Object data) {
            Transaction transaction = (Transaction) data;
            Map<String, Object> map = new HashMap<>();
            map.put("id", transaction.getId());
            map.put("timestamp", transaction.getTimestamp().format(DateRangeConverter.DATE_TIME_FORMATTER));
            map.put("customerId", transaction.getCustomerId());
            map.put("items", transaction.getItemsOrdered().entrySet().parallelStream().map(
                    entry -> new ItemLine(entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue())
            ).collect(Collectors.toList()));
            map.put("sum", transaction.getSum());
            return representMapping(new Tag("!transaction"), map, false);
        }
    }

    @Getter
    private class ItemLine extends Item {
        Integer count;

        ItemLine(String name, BigDecimal price, Integer count) {
            super(name, price);
            this.count = count;
        }
    }

    private class RepresentItemLine implements Represent {
        @Override
        public Node representData(Object data) {
            ItemLine item = (ItemLine) data;
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.getName());
            map.put("price", item.getPrice());
            map.put("count", item.getCount());
            return representMapping(new Tag("!item"), map, false);
        }
    }
}
