/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import entidades.Contato;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Contatos")
public class ContatoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AgendaResource
     */
    public ContatoResource() {
    }
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContatos() {        
        Gson gson = new Gson();
        ContatoDao ct = new ContatoDao();
        return gson.toJson(ct.listar());
    }
    
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public boolean inserirContato(String content) {
        Gson gson = new Gson();
        ContatoDao ctdao = new ContatoDao();
        Contato ct = (Contato) gson.fromJson(content, Contato.class);
        return ctdao.salvar(ct);
    }

    @Path("{contatoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getContato(@PathParam("contatoId") String id) {        
        Gson gson = new Gson();
        ContatoDao ctdao = new ContatoDao();
        Contato ct = ctdao.listar(Integer.parseInt(id));
        return gson.toJson(ct);     
    }
    
    @Path("excluir/{contatoId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean excluir(@PathParam("contatoId") String id) {
      Gson gson = new Gson();
      ContatoDao ctdao = new ContatoDao();
      //Contato ct = (Contato) gson.fromJson(content, Contato.class);
      return ctdao.excluir(Integer.parseInt(id));
    }    
     
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean alterarContato(String content) {
        Gson gson = new Gson();
        ContatoDao ctdao = new ContatoDao();
        Contato ct = (Contato) gson.fromJson(content, Contato.class);
        return ctdao.editar(ct);
    }
}
