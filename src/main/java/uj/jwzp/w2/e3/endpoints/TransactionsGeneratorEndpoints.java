package uj.jwzp.w2.e3.endpoints;

import org.springframework.web.bind.annotation.*;
import uj.jwzp.w2.e3.logic.TransactionsGenerator;
import uj.jwzp.w2.e3.logic.converter.TransactionsListAdapter;
import uj.jwzp.w2.e3.model.TransactionGeneratorArgs;
import uj.jwzp.w2.e3.model.property.DateRangeProperty;
import uj.jwzp.w2.e3.model.property.IntRangeProperty;

import java.io.IOException;

@RestController
public class TransactionsGeneratorEndpoints {
    private TransactionsGenerator generator;

    public TransactionsGeneratorEndpoints(TransactionsGenerator generator) {
        this.generator = generator;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET,
            produces = {"application/json", "application/xml", "application/yml"})
    @ResponseBody
    public TransactionsListAdapter request(
            @RequestParam("customersId") IntRangeProperty customersIds,
            @RequestParam("dateRange") DateRangeProperty dateRange,
            @RequestParam("itemsCount") IntRangeProperty itemsCount,
            @RequestParam("itemsQuantity") IntRangeProperty itemsQuantity,
            @RequestParam("eventsCount") Integer eventsCount
    ) {
        generator.prepare(new TransactionGeneratorArgs(customersIds, dateRange, itemsCount, itemsQuantity, eventsCount));
        try {
            return new TransactionsListAdapter(generator.generate());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
