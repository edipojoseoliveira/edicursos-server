package com.edicursos.edicursos.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edicursos.edicursos.json.AlunoCursoJson;
import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.AlunoCurso;
import com.edicursos.edicursos.rn.AlunoCursoRN;

/**
*
* @author edipo
*/
@Path("/aluno-curso")
public class AlunoCursoWS {
	
	@GET
    @Path("/consultar")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response consultar(@QueryParam("idAluno") Integer idAluno,
                          @QueryParam("idCurso") Integer idCurso) {
		if (idAluno != null && idCurso != null) {
			AlunoCursoRN alunoCursoRN = new AlunoCursoRN();
			AlunoCurso alunoCurso = alunoCursoRN.consultar(idCurso, idAluno);
			if (alunoCurso != null) {
				AlunoCursoJson json = new AlunoCursoJson("Registro encontrado!", alunoCurso.getSituacao());
                return Response.ok(json).status(Response.Status.OK).build();
			} else {
				MensagemJson json = new MensagemJson("Registro não encontrar!");
                return Response.ok(json).status(Response.Status.OK).build();
			}
		} else {
			MensagemJson json = new MensagemJson("Erro ao carregar o aluno e o curso!");
            return Response.ok(json).status(Response.Status.OK).build();
		}
	}
	
	@POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(AlunoCurso alunoCurso) {
		try {
			AlunoCursoRN alunoCursoRN = new AlunoCursoRN();
			alunoCursoRN.salvar(alunoCurso);
			
			return Response.ok(alunoCurso).status(Response.Status.OK).build();
		} catch (Exception e) {
			MensagemJson json = new MensagemJson("Erro ao efetuar a inscrição!");
            return Response.ok(json).status(Response.Status.OK).build();
		}
	}
	
}
