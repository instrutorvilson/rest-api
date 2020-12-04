/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import entidades.Compromisso;
import entidades.Contato;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Compromisso")
public class CompromissoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Compromisso
     */
    public CompromissoResource() {
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompromissos() {
        Gson gson = new Gson();
        CompromissoDao cdao = new CompromissoDao();
        return gson.toJson(cdao.listar());
    }

   @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public boolean inserirCompromiso(String content) {
        Gson gson = new Gson();
        CompromissoDao ctdao = new CompromissoDao();
        Compromisso ct = (Compromisso) gson.fromJson(content, Compromisso.class);
        return ctdao.salvar(ct);
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
