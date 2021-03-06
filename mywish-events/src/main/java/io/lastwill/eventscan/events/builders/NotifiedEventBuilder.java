package io.lastwill.eventscan.events.builders;

import io.mywish.wrapper.WrapperTransactionReceipt;
import io.mywish.wrapper.ContractEventDefinition;
import io.lastwill.eventscan.events.model.contract.NotifiedEvent;
import org.springframework.stereotype.Component;

@Component
public class NotifiedEventBuilder extends BaseEmptyEventBuilder<NotifiedEvent> {
    public NotifiedEventBuilder() {
        super("Notified");
    }

    @Override
    protected NotifiedEvent buildEmpty(final ContractEventDefinition definition, String address, final WrapperTransactionReceipt transactionReceipt) {
        return new NotifiedEvent(definition, transactionReceipt, address);
    }
}
