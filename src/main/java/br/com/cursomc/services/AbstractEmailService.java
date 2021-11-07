package br.com.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage mailMessage = prepareSimpleMailMessageFromPedido(obj);
		sendMail(mailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(obj.getCliente().getEmail());
		mailMessage.setFrom(sender);
		mailMessage.setSubject("Pedido confirmado! Código: " + obj.getId());
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText(obj.toString());
		return mailMessage;
	}

	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mimeMessage = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj)
			throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(obj.getCliente().getEmail());
		helper.setFrom(sender);
		helper.setSubject("Pedido confirmado! Código: " + obj.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}

}
