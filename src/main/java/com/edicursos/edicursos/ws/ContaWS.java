package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.model.Conta;
import com.edicursos.edicursos.rn.AlunoRN;
import com.edicursos.edicursos.rn.ContaRN;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author edipo
 */
@Path("/conta")
public class ContaWS {
    
    @GET
    @Path("/entrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response entrar(@QueryParam("email") String email,
                          @QueryParam("senha") String senha) {
        if (email != null && senha != null) {
            ContaRN contaRN = new ContaRN();
            Conta conta = contaRN.entrar(email, senha);
            if (conta != null) {
                AlunoRN alunoRN = new AlunoRN();
                Aluno aluno = alunoRN.carregarPorConta(conta);
                if (aluno != null) {
                    return Response.ok("{sucesso: 'Usuario encontrado!', nome: '" + aluno.getNome() + "', codigo: '" + aluno.getId() + "'}").status(Response.Status.OK).build();
                } else {
                    return Response.ok("{erro: 'Usuário não encontrar!'}").status(Response.Status.OK).build();
                }
            } else {
                return Response.ok("{erro: 'Usuário ou senha inválidos!'}").status(Response.Status.OK).build();
            }
        } else {
            return Response.ok("{erro: 'Informe o usuário e a senha!'}").status(Response.Status.OK).build();
        }
    }
    
}
