package com.stray.cat.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    public void sendEmail(String qq,String title){
        SimpleMailMessage mailMessage  = new SimpleMailMessage();
        mailMessage.setSubject("举报");
        mailMessage.setText("您的文章"+title+"存在违规内容已本删除！");
        mailMessage.setTo(qq);
        mailMessage.setFrom("2848351894@qq.com");
        mailSender.send(mailMessage);
    }

}
