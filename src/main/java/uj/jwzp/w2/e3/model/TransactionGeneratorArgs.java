package uj.jwzp.w2.e3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uj.jwzp.w2.e3.model.property.DateRangeProperty;
import uj.jwzp.w2.e3.model.property.IntRangeProperty;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionGeneratorArgs {
    private IntRangeProperty customerIds;
    private DateRangeProperty dateRange;
    private IntRangeProperty itemsCount;
    private IntRangeProperty itemsQuantity;
    private Integer eventsCount;
}
