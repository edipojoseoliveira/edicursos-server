package com.edicursos.edicursos.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edicursos.edicursos.json.AlunoAulaJson;
import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.AlunoAula;
import com.edicursos.edicursos.rn.AlunoAulaRN;

/**
*
* @author edipo
*/
@Path("/aluno-aula")
public class AlunoAulaWS {

	@GET
    @Path("/consultar")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response consultar(@QueryParam("idAluno") Integer idAluno,
                          @QueryParam("idAula") Integer idAula) {
		if (idAluno != null && idAula != null) {
			AlunoAulaRN alunoAulaRN = new AlunoAulaRN();
			AlunoAula alunoAula = alunoAulaRN.consultar(idAluno, idAula);
			if (alunoAula != null) {
				AlunoAulaJson json = new AlunoAulaJson("Registro encontrado!", alunoAula.getSituacao());
                return Response.ok(json).status(Response.Status.OK).build();
			} else {
				MensagemJson json = new MensagemJson("Registro não encontrar!");
                return Response.ok(json).status(Response.Status.OK).build();
			}
		} else {
			MensagemJson json = new MensagemJson("Erro ao carregar o aluno e a aula!");
            return Response.ok(json).status(Response.Status.OK).build();
		}
	}
	
	@POST
    @Path("/salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(AlunoAula alunoAula) {
		try {
			AlunoAulaRN alunoAulaRN = new AlunoAulaRN();
			AlunoAula alunoAulaSalvo = alunoAulaRN.consultar(alunoAula.getAluno().getId(), alunoAula.getAula().getId());
			if (alunoAulaSalvo != null) {
				alunoAulaSalvo.setSituacao(alunoAula.getSituacao());
				alunoAulaRN.salvar(alunoAulaSalvo);
				AlunoAulaJson alunoAulaJson = new AlunoAulaJson("Registro salvo com sucesso!", alunoAulaSalvo.getSituacao());
				return Response.ok(alunoAulaJson).status(Response.Status.OK).build();
			} else {
				alunoAulaRN.salvar(alunoAula);
				AlunoAulaJson alunoAulaJson = new AlunoAulaJson("Registro salvo com sucesso!", alunoAula.getSituacao());
				return Response.ok(alunoAulaJson).status(Response.Status.OK).build();
			}
		} catch (Exception e) {
			MensagemJson json = new MensagemJson("Erro ao efetuar a conclusão da aula!");
            return Response.ok(json).status(Response.Status.OK).build();
		}
	}
	
}
