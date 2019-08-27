package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.AtividadeQuestao;
import com.edicursos.edicursos.model.Aula;
import com.edicursos.edicursos.model.Curso;
import com.edicursos.edicursos.rn.CursoRN;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/curso")
public class CursoWS {
    
    @POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(Curso curso) {
        try {
            CursoRN cursoRN = new CursoRN();
            cursoRN.salvar(curso);
            
            //Tira o curso das aulas para poder retornar o JSON sem loop infinito
            for (int i = 0; i < curso.getAulas().size(); i++) {
                Aula aula = curso.getAulas().get(i);
                aula.setCurso(null);
                for (int j = 0; j < aula.getQuestoes().size(); j++) {
                    AtividadeQuestao questao = aula.getQuestoes().get(j);
                    questao.setAula(null);
                    aula.getQuestoes().set(j, questao);
                }
                curso.getAulas().set(i, aula);
            }
            
            return Response.ok(curso).status(Response.Status.OK).build();
        } catch (Exception e) {
            MensagemJson json = new MensagemJson("Erro ao salvar o curso!");
            return Response.ok(json).status(Response.Status.OK).build();
        }
    }
    
    @GET
    @Path("/carregar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response carregar(@PathParam("id") Integer id) {
        try {
            CursoRN cursoRN = new CursoRN();
            Curso curso = cursoRN.carregar(id);
            
            return Response.ok(curso).status(Response.Status.OK).build();
        } catch (Exception e) {
            MensagemJson json = new MensagemJson("Erro ao carregar o curso!");
            return Response.ok(json).status(Response.Status.OK).build();
        }
    }
    
    @DELETE
    @Path("/deletar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) {
        try {
            CursoRN cursoRN = new CursoRN();
            cursoRN.excluir(id);
            
            MensagemJson json = new MensagemJson("Curso excluido com sucesso!");
            return Response.ok(json).status(Response.Status.OK).build();
        } catch (Exception e) {
            MensagemJson json = new MensagemJson("Erro ao excluir o curso!");
            return Response.ok(json).status(Response.Status.OK).build();
        }
    }
    
}
