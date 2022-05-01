package ru.spbstu.korobtsov.core.notification.sms;

public interface SmsNotificationService {

    void sendSms(String from, String to, String message);
}
