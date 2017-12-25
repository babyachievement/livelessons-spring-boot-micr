package demo;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Producer implements CommandLineRunner {
    private final RabbitMessagingTemplate messagingTemplate;

    public Producer(RabbitMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<100; i++) {
            Notification notification = new Notification(UUID.randomUUID().toString(),
                    "Hello, world!", new Date());

            Map<String, Object> headers = new HashMap<>();
            headers.put("notification-id", notification.getId());

            this.messagingTemplate.convertAndSend(MessagingApplication.NOTIFICATIONS,
                    notification, headers, message -> {
                        System.out.println("sending " + message.getPayload().toString());
                        return message;
                    });
        }
    }
}
