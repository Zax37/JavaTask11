package uj.jwzp.w2.e3.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.jwzp.w2.e3.logic.reader.ItemsReader;
import uj.jwzp.w2.e3.model.Item;
import uj.jwzp.w2.e3.model.Transaction;
import uj.jwzp.w2.e3.model.TransactionGeneratorArgs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class TransactionsGenerator {
    private final ItemsReader reader;
    private TransactionGeneratorArgs args;

    @Autowired
    public TransactionsGenerator(
        ItemsReader itemsReader
    ) {
        this.reader = itemsReader;
    }

    public Transaction getSingleRandomTransaction(List<Item> availableItems, int id) {
        Collections.shuffle(availableItems);
        int count = args.getItemsCount().random();
        LinkedHashMap<Item, Integer> orderedItems = new LinkedHashMap<>();
        for (int j = 0; j < count; j++) {
            orderedItems.put(availableItems.get(j%availableItems.size()), args.getItemsQuantity().random());
        }

        return new Transaction(
                id,
                args.getDateRange().random(),
                args.getCustomerIds().random(),
                orderedItems
        );
    }

    public void prepare(TransactionGeneratorArgs args) {
        this.args = args;
    }

    public List<Transaction> generate() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        List<Item> availableItems = reader.read();

        for (int i = 1; i <= args.getEventsCount(); i++) {
            log.trace("Generating transaction " + i);
            Transaction transaction = getSingleRandomTransaction(availableItems, i);
            transactions.add(transaction);
        }

        log.info("Successfully generated " + args.getEventsCount() + " transactions.");
        return transactions;
    }
}
