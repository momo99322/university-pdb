package ru.spbstu.korobtsov.core.notification.email;

public interface EmailNotificationService {

    void sendEmail(String from, String to, String message);
}
