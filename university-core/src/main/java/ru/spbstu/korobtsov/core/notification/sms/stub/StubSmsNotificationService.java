package ru.spbstu.korobtsov.core.notification.sms.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.core.notification.sms.SmsNotificationService;

@Service
public class StubSmsNotificationService implements SmsNotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubSmsNotificationService.class);

    @Override
    public void send(String from, String to, String message) {
        LOGGER.debug("Sending message: from={}, to={}, message{}", from, to, message);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.debug("Sent message: from={}, to={}, message{}", from, to, message);
    }
}
