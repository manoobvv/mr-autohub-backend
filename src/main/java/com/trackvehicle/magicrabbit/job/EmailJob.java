package com.trackvehicle.magicrabbit.job;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class EmailJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

        final JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        final String subject = jobDataMap.getString("subject");
        final String body = jobDataMap.getString("body");
        final String recipientEmail = jobDataMap.getString("email");

        sendMail(this.mailProperties.getUsername(), recipientEmail, subject, body);
    }

    private void sendMail(final String fromEmail, final String toEmail, final String subject, final String body) {
        try {
            logger.info("Sending Email to {}", toEmail);
            final MimeMessage message = this.mailSender.createMimeMessage();

            final MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);

            this.mailSender.send(message);
        } catch (final MessagingException ex) {
            logger.error("Failed to send email to {}", toEmail);
        }
    }
}
