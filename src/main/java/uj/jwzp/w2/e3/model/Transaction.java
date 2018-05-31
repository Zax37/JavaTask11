package uj.jwzp.w2.e3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.NonFinal;
import uj.jwzp.w2.e3.logic.converter.DateAdapter;
import uj.jwzp.w2.e3.logic.converter.ItemsMapConverter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "transaction")
public class Transaction {
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    @XmlElement
    @JsonProperty("id")
    private int id;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private ZonedDateTime timestamp;
    @XmlElement
    private int customerId;

    @XmlElement(name = "items")
    @XmlJavaTypeAdapter(ItemsMapConverter.class)
    private LinkedHashMap<Item, Integer> itemsOrdered;

    @XmlElement
    public BigDecimal getSum() {
        BigDecimal ret = BigDecimal.ZERO;
        for (Map.Entry<Item, Integer> entry : itemsOrdered.entrySet()) {
            ret = ret.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return ret;
    }
}