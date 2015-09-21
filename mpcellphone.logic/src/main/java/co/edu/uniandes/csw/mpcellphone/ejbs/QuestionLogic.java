/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.ejbs;

import co.edu.uniandes.csw.mpcellphone.api.IQuestionLogic;
import co.edu.uniandes.csw.mpcellphone.converters.QuestionConverter;
import co.edu.uniandes.csw.mpcellphone.dtos.QuestionDTO;
import co.edu.uniandes.csw.mpcellphone.entities.QuestionEntity;
import co.edu.uniandes.csw.mpcellphone.persistence.QuestionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author g.gonzalez10
 */
@Stateless
public class QuestionLogic implements IQuestionLogic {
    
    @Inject private QuestionPersistence persistence;
    
    /**
     * Metodo encargado de obtener las �rdenes de un cliente
     * @param page
     * @param maxRecords
     * @return 
     */
    @Override
    public List<QuestionDTO> getQuestions(Integer page, Integer maxRecords) {
        return QuestionConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    @Override
    public QuestionDTO getQuestion(Long id) {
        return QuestionConverter.fullEntity2DTO(persistence.find(id));
    }

    @Override
    public int countQuestion() {
        return persistence.count();
    }

    /**
     * @param dto
     * @return 
     */
    @Override
    public QuestionDTO createQuestion(QuestionDTO dto) {
        QuestionEntity entity = QuestionConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        //Send email
        String emailMsg="<html><body><br />Se�or(a) "+dto.getProvider().getName() +
                "<br /><br />" +
                "Usted ha recibido una nueva pregunta: <br /><br /> " +
                "<br />Producto: " + dto.getProduct().getName() + 
                "<br />Cliente: " + dto.getClient().getName() + 
                "<br />Pregunta: "+entity.getQuestion() +
                "<br /><br />Atentamente," + 
                "<br /><br /><br />MarketPhone";
        String subject = "Ha recibido un mensaje de MarketPhone";
        mailUtilsMP.sendEmailMP(emailMsg, dto.getProvider().getEmail(), subject);
        return QuestionConverter.fullEntity2DTO(entity);
    }

    
}
