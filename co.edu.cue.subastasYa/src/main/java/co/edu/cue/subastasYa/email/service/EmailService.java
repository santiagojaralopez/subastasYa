package co.edu.cue.subastasYa.email.service;

import co.edu.cue.subastasYa.email.dto.OfferEmailValueDTO;
import co.edu.cue.subastasYa.email.dto.PasswordEmailValuesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    
    @Autowired
    TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendPasswordEmail(PasswordEmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront+dto.getTokenPassword());

            Context context = new Context();
            context.setVariables(model);

            String htmlText = templateEngine.process("password-email", context);

            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendOfferEmail(OfferEmailValueDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("bidderUserName", dto.getBidderUserName());
            model.put("offerValue", dto.getValue());
            model.put("publicationName", dto.getPublicationName());

            Context context = new Context();
            context.setVariables(model);

            String htmlText = templateEngine.process("offer-notification", context);

            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
