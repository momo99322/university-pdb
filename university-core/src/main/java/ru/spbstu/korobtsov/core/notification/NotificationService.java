package ru.spbstu.korobtsov.core.notification;

public interface NotificationService {

    void send(String from, String to, String message);
}
