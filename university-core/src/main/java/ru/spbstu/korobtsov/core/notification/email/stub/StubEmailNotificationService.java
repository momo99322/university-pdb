package ru.spbstu.korobtsov.core.notification.email.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbstu.korobtsov.core.notification.email.EmailNotificationService;

@Slf4j
@Service
public class StubEmailNotificationService implements EmailNotificationService {

    @Override
    public void send(String from, String to, String message) {
        log.debug("Sending message: from={}, to={}, message={}", from, to, message);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("Sent message: from={}, to={}, message={}", from, to, message);
    }
}
