package uj.jwzp.w2.e3.logic.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uj.jwzp.w2.e3.model.Transaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "transactions")
public class TransactionsListAdapter {
    @XmlElement(required = true, name = "transaction")
    private List<Transaction> transactions;
}
