package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.pdf.PdfBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import com.ichmielewski.shop_backend.dto.AbstractDTO;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractService <DTO extends AbstractDTO> {
    private final AbstractClient<DTO> client;
    private final JavaMailSender mailSender;
    private final String name;
    private final String mailFrom;

    protected AbstractService (AbstractClient<DTO> client, JavaMailSender mailSender, String name, String mailFrom) {
        this.client = client;
        this.mailSender = mailSender;
        this.name = name;
        this.mailFrom = mailFrom;
    }

    @Async
    public CompletableFuture<Void> sendMail(String to) {
        try {
            MessageWithFile message = new MessageWithFile(mailFrom,
                    to, name,
                    "This is " + name,
                    name + ".pdf", PdfBuilder.build(client.getAll()));

            mailSender.send(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }


}
