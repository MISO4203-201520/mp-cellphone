/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.services;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpcellphone.api.IQuestionLogic;
import co.edu.uniandes.csw.mpcellphone.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpcellphone.dtos.QuestionDTO;
import co.edu.uniandes.csw.mpcellphone.providers.StatusCreated;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author m.amaya11
 */
@Path("/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionService {
    
    @Inject private IQuestionLogic questionLogic;
    
    private final ProviderDTO provider = (ProviderDTO)SecurityUtils.getSubject().getSession().getAttribute("Provider");
     
    
    /**
     * @param dto
     * @return 
     * @generated
     */
    @POST
    @StatusCreated
    @MPLoCAnn(tier="Services", reqId="REQ-12")
    public QuestionDTO createQuestion(QuestionDTO dto) { 
        if(dto.getFather() != null){
            dto.setDate(new Date());
            return questionLogic.createAnswer(dto);
        }
        else{
            return questionLogic.createQuestion(dto);
        }
    }
    
    /**
     * Metodo PUT, encargado de actualizar informacion de la pregunta
     *
     * @param id
     * @param dto
     * @return
     */
    @PUT
    @Path("{id: \\d+}")
    @MPLoCAnn(tier="Services", reqId="REQ-12")
    public QuestionDTO updateQuestion(@PathParam("id") Long id, QuestionDTO dto) {
        dto.setId(id);
        return questionLogic.updateQuestion(dto);
    }
    
    /**
     * @param id
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    @MPLoCAnn(tier="Services", reqId="REQ-12")
    public void deleteQuestion(@PathParam("id") Long id) {
        questionLogic.deleteQuestion(id);        
    }
    
    
    /**
     * @return 
     * @generated
     */
    @GET
    @MPLoCAnn(tier="Services", reqId="REQ-12")
    public List<QuestionDTO> getQuestions() {
        return questionLogic.getByProviderId(provider.getId());
    }

    /**
     * @param id
     * @return 
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    @MPLoCAnn(tier="Services", reqId="REQ-12")
    public QuestionDTO getQuestion(@PathParam("id") Long id) {
        return questionLogic.getQuestion(id);
    }
    
    @GET
    @Path("/byFather/{id: \\d+}")
    public List<QuestionDTO> getByFatherId(@PathParam("id") Long id) {  
        return questionLogic.getByFatherId(id);
    }
    
}
