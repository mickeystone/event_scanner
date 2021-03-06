package io.lastwill.eventscan.services.commands;

import io.lastwill.eventscan.model.NetworkType;
import io.lastwill.eventscan.services.monitors.NetworkStuckMonitor;
import io.mywish.bot.service.BotCommand;
import io.mywish.bot.service.ChatContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class BotCommandNetworks implements BotCommand {
    @Getter
    private final String name = "/networks";
    @Getter
    private final String usage = "";
    @Getter
    private final String description = "Information about latest blocks in each network";

    @Autowired
    private NetworkStuckMonitor networkStuckMonitor;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ZoneId zone = ZoneId.of("Europe/Moscow");

    @Override
    public void execute(ChatContext context, List<String> args) {
        List<String> messages = new ArrayList<>();
        Map<NetworkType, NetworkStuckMonitor.LastEvent> lastEvents = networkStuckMonitor.getLastEvents();
        for (NetworkType network : NetworkType.values()) {
            NetworkStuckMonitor.LastEvent lastEvent = lastEvents.get(network);
            if (lastEvent != null) {
                long blockNo = lastEvent.getBlockNo();
                ZonedDateTime receivedTime = ZonedDateTime.ofInstant(
                        lastEvent.getReceivedTime().toInstant(ZoneOffset.UTC),
                        zone
                );
                ZonedDateTime blockTime = ZonedDateTime.ofInstant(lastEvent.getTimestamp(), zone);
                messages.add(network.name() +
                        "\n\tLast block: " + lastEvent.getBlockNo() +
                        "\n\tReceived time: " + receivedTime.format(dateFormatter) +
                        "\n\tBlock time: " + blockTime.format(dateFormatter)
                );
            }
        }
        context.sendMessage(String.join("\n\n", messages));
    }
}
