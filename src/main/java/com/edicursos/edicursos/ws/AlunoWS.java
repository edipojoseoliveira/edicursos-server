package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.rn.AlunoRN;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public Response carregar(@PathParam("id") String id) {
        return Response.ok("{id: " + id + ", nome: edipo}").status(Response.Status.OK).build();
    }
    
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvar(Aluno aluno) {
        try {
            String result = "Nome: " + aluno.getNome() + 
                            ", Sobrenome: " + aluno.getSobrenome() + 
                            ", email: " + aluno.getConta().getEmail() +
                            ", senha: " + aluno.getConta().getSenha();

            AlunoRN alunoRN = new AlunoRN();
            alunoRN.salvar(aluno);

            return Response.status(Response.Status.OK).entity(result).build();
        } catch (Exception e) {
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }    	
    }
    
}
