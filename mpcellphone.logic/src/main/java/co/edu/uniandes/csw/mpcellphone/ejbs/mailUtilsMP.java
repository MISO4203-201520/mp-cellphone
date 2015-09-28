/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.ejbs;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author g.gonzalez10
 */
@Stateless
public class mailUtilsMP {


    // L�gica para generar el email
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static MimeMultipart generateMultiPartMessage;
    static BodyPart generateBodyPartMessage;
    static BodyPart generateBodyPartAttach;

    
    
    /**
     * Metodo para env�o de correo sin anexos
     * @param message
     * @param emailRecipient
     * @param subject 
     */
    public static void sendEmailMP(String message, String emailRecipient, String subject) {
        try {
            
            //Step1		
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            //Step2		
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject(subject);
            String emailBody = message;
            generateMailMessage.setContent(emailBody, "text/html");

            //Step3		
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "marketphone201520@gmail.com", "Market2015");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /**
     * Metodo para env�o de correo con anexos
     * @param message
     * @param emailRecipient
     * @param subject 
     * @param fileAttach 
     */
    public static void sendEmailMPAttach(String message, String emailRecipient, String subject, String fileAttach) {
        try {
            
            //Step1		
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            //Construir el mensaje con el archivo adjunto
            generateBodyPartMessage = new MimeBodyPart();
            generateBodyPartMessage.setContent(message, "text/html"); 
            generateBodyPartAttach = new MimeBodyPart();
            generateBodyPartAttach.setDataHandler(new DataHandler(new FileDataSource(fileAttach)));
            generateBodyPartAttach.setFileName(fileAttach);
            generateMultiPartMessage = new MimeMultipart();
            generateMultiPartMessage.addBodyPart(generateBodyPartMessage);
            generateMultiPartMessage.addBodyPart(generateBodyPartAttach);
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject(subject);
            generateMailMessage.setContent(generateMultiPartMessage); 
            
            //Enviar mensaje
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "marketphone201520@gmail.com", "Market2015");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }


}
