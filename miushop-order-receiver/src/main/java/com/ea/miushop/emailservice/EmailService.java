///*
// * =============================================================================
// * 
// *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
// * 
// *   Licensed under the Apache License, Version 2.0 (the "License");
// *   you may not use this file except in compliance with the License.
// *   You may obtain a copy of the License at
// * 
// *       http://www.apache.org/licenses/LICENSE-2.0
// * 
// *   Unless required by applicable law or agreed to in writing, software
// *   distributed under the License is distributed on an "AS IS" BASIS,
// *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *   See the License for the specific language governing permissions and
// *   limitations under the License.
// * 
// * =============================================================================
// */
//package com.ea.miushop_cart.emailservice;
//
//import java.util.List;
//import java.util.Locale;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//
//import com.ea.miushop.domain.Order;
//import com.ea.miushop_cart.domain.CartItem;
//
//@Service("emailService")
//public class EmailService {
//	static final String MIUSHOP = "templates/images/miu.jpg";
//
//	private static final String JPG_MIME = "image/jpg";
//
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Autowired
//	private SpringTemplateEngine templateEngine;
//
//	public void sendOrderReceivedMail(final String recipientName, final String recipientEmail, List<CartItem> items,
//			final Locale locale) throws MessagingException {
//
//		// Prepare message using a Spring helper
//		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//		message.setSubject("Order Confirmation");
//
//		String htmlContent = "Order has been sent successfully. "
//				+ "You will get another email regarding Pick up information when your order is ready. "
//				+ "Have a great week be safe.";
//		// Prepare the evaluation context
//		final Context thymeContext = new Context(locale);
//		thymeContext.setVariable("name", recipientName);
//
//		message.setText(htmlContent, true /* isHtml */);
//		message.addInline("MIUShop", new ClassPathResource(MIUSHOP), JPG_MIME);
//
//		this.mailSender.send(mimeMessage);
//
//	}
//
//}
