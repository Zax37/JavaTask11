package uj.jwzp.w2.e3.logic.converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;

public class DateAdapter extends XmlAdapter<String, ZonedDateTime> {
    @Override
    public String marshal(ZonedDateTime v) {
        return DateRangeConverter.DATE_TIME_FORMATTER.format(v);
    }

    @Override
    public ZonedDateTime unmarshal(String v) {
        return ZonedDateTime.parse(v, DateRangeConverter.DATE_TIME_FORMATTER);
    }
}