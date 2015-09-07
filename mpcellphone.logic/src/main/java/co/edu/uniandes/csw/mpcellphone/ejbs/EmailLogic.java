/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.ejbs;

import co.edu.uniandes.csw.mpcellphone.api.IEmailLogic;
import co.edu.uniandes.csw.mpcellphone.converters.EmailConverter;
import co.edu.uniandes.csw.mpcellphone.dtos.EmailDTO;
import co.edu.uniandes.csw.mpcellphone.entities.EmailEntity;
import co.edu.uniandes.csw.mpcellphone.persistence.EmailPersistence;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author g.gonzalez10
 */
@Stateless
public class EmailLogic implements IEmailLogic {
    
    @Inject private EmailPersistence persistence;
    
    /**
     * @generated
     */
    public int countEmail() {
        return persistence.count();
    }


    /**
     * @generated
     */
    public EmailDTO createMessage(EmailDTO dto) {
        EmailEntity entity = EmailConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        //Send email
        String emailMsg="<html><body><br />Se�or(a) "+dto.getProvider().getName() +
                "<br /><br />" +
                "Usted ha recibido una nueva pregunta: <br /><br /> " +
                "<br />Producto: " + entity.getProduct().getName() + 
                "<br />Cliente: " + dto.getClient().getName() + 
                "<br />Pregunta: "+entity.getQuestion() +
                "<br /><br />Atentamente," + 
                "<br /><br /><br />MarketPhone";
        String subject = "Ha reibido un mensaje de MarketPhone";
        generateAndSendEmail(emailMsg, dto.getProvider().getEmail(), subject);
        return EmailConverter.fullEntity2DTO(entity);
    }

    // L�gica para generar el email
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    

    public static void generateAndSendEmail(String message, String emailRecipient, String subject) {

        try {
            
            //loadProperties();

            //Step1		
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            //Step2		
            System.out.println("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject(subject);
            String emailBody = message;
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            //Step3		
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "marketphone201520@gmail.com", "Market2015");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    private static void loadProperties() {

        Properties datos = new Properties( );
        String data="";
        try
        {
                FileInputStream input = new FileInputStream( "src/main/resources/admin_email.properties" );
                datos.load( input );
                //adminEmail=datos.getProperty("admin.email");
        }
        catch( Exception e )
        {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
