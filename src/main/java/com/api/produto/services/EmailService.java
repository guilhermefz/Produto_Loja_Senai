package com.api.produto.services;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendNewPasswordEmail(String email, String senha) {
        try {
            // Cria uma nova mensagem de e-mail no formato MINE(suporte HTML, anexos, etc.)
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //Cria um helper para facilitar a montagem do e-mail, parâmetros
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage, true, "UTF-8");
            helper.setFrom("senai@artostech.com.br");
            helper.setTo(email);
            helper.setSubject("Nova Senha de Acessso");

            //Montar o corpo HTML dinamicamente
            String htmlBody = "<!DOCTYPE html>"
                    + "<html><body style='font-family: Arial;'>"
                    + "<div style='background-color: #FFF; padding:20px; border-radius:8px'>"
                    + "<h2 style='text-align: center;'>Nova Senha</h2>"
                    +"<p style='text-align: center; color: #0000FF'>"+senha+"</p>";
//            helper.addAttachment("relatorio.pdf", new File("relatorio.pdf"));
            helper.setText(htmlBody, true);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            throw new RuntimeException("Falha ao enviar email: "+e);
        }
    }
}
