/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.entities;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author g.gonzalez10
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Question.getByProviderId", 
            query = "select q from QuestionEntity q WHERE q.provider.id = :idProvider and q.father is null"),
    @NamedQuery(name = "Question.getByFatherId", 
            query = "select q from QuestionEntity q WHERE q.father like :idFather"),
})
public class QuestionEntity implements Serializable {
    
    @Id
    @GeneratedValue(generator = "Question")
    private Long id;
    private String question;
    private String state;
    private String father;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    private ClientEntity client;
    
    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private ProviderEntity provider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    
    @MPLoCAnn(tier="Back-end", reqId="REQ-12")
    public String getState() {
        return state;
    }

    @MPLoCAnn(tier="Back-end", reqId="REQ-12")
    public void setState(String state) {
        this.state = state;
    }

    @MPLoCAnn(tier="Back-end", reqId="REQ-12")
    public String getFather() {
        return father;
    }

    @MPLoCAnn(tier="Back-end", reqId="REQ-12")
    public void setFather(String father) {
        this.father = father;
    }
    
    

}
