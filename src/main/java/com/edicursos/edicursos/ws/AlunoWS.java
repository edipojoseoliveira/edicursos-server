package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.rn.AlunoRN;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author edipo
 */
@Path("/aluno")
public class AlunoWS {
    
    @GET
    @Path("/carregar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response carregar(@PathParam("id") String id) {
        MensagemJson json = new MensagemJson("Aluno com id: " + id);
        return Response.ok(json).status(Response.Status.OK).build();
    }
    
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(Aluno aluno) {
        try {
            AlunoRN alunoRN = new AlunoRN();
            alunoRN.salvar(aluno);

            return Response.ok(aluno).status(Response.Status.OK).build();
        } catch (Exception e) {
            MensagemJson json = new MensagemJson("Erro ao salvar o aluno!");
            return Response.ok(json).status(Response.Status.OK).build();
        }    	
    }
    
}
