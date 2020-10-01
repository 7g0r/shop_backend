package com.ichmielewski.shop_backend.employee.crud;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageWithFile implements MimeMessagePreparator {
    private final String from;
    private final String to;
    private final String subject;
    private final String text;
    private final String filename;
    private final byte[] fileData;

    public MessageWithFile(String from, String to, String subject, String text, String filename, byte[] fileData) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.filename = filename;
        this.fileData = fileData;
    }


    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        mimeMessage.setFrom(from);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject(subject);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.addAttachment(filename, new ByteArrayResource(fileData));
        helper.setText(text, true);

    }
}
