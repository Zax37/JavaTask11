package uj.jwzp.w2.e3.logic.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<TransactionsListAdapter> {
    public YamlHttpMessageConverter () {
        super(new MediaType("application", "yml"));
    }

    @Override
    protected boolean supports (Class<?> clazz) {
        return true;
    }

    @Override
    protected TransactionsListAdapter readInternal (Class<? extends TransactionsListAdapter> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        Yaml yaml = new Yaml(new TransactionsRepresenter());
        return yaml.loadAs(inputMessage.getBody(), clazz);
    }

    @Override
    protected void writeInternal (TransactionsListAdapter transactionsListAdapter, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(new TransactionsRepresenter(), options);
        OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody());
        writer.write(yaml.dumpAs(transactionsListAdapter.getTransactions(), new Tag("!transactions"), null));
        writer.close();
    }
}