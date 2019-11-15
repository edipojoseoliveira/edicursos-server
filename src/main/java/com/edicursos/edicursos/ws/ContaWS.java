package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.json.ContaJson;
import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.model.Conta;
import com.edicursos.edicursos.rn.AlunoRN;
import com.edicursos.edicursos.rn.ContaRN;
import com.edicursos.edicursos.util.EmailUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response entrar(@QueryParam("email") String email,
                          @QueryParam("senha") String senha) {
        if (email != null && senha != null) {
            ContaRN contaRN = new ContaRN();
            Conta conta = contaRN.entrar(email, senha);
            if (conta != null) {
                AlunoRN alunoRN = new AlunoRN();
                Aluno aluno = alunoRN.carregarPorConta(conta);
                if (aluno != null) {
                    ContaJson json = new ContaJson("Usuário encontrado!", aluno.getNome(), String.valueOf(aluno.getId()));
                    return Response.ok(json).status(Response.Status.OK).build();
                } else {
                    MensagemJson json = new MensagemJson("Usuário não encontrar!");
                    return Response.ok(json).status(Response.Status.OK).build();
                }
            } else {
                MensagemJson json = new MensagemJson("Usuário ou senha inválidos!");
                return Response.ok(json).status(Response.Status.OK).build();
            }
        } else {
            MensagemJson json = new MensagemJson("Informe o usuário e a senha!");
            return Response.ok(json).status(Response.Status.OK).build();
        }
    }
    
    @GET
    @Path("/enviar-email-redefinir-senha")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response enviarEmailRedefinirSenha(@QueryParam("email") String email) {
        if (email != null) {
            ContaRN contaRN = new ContaRN();
            Conta conta = contaRN.carregarPorEmail(email);
            if (conta != null) {
                try {
					EmailUtil emailUtil = new EmailUtil();
					emailUtil.enviarEmail("edipodeoliveira46@gmail.com", email, "Redefinir senha", 
							"Você solicitou a redefinição da sua senha na plataforma de ensino EdiCursos");
					
					MensagemJson json = new MensagemJson("Uma mensagem com o link para redefinir a senha foi enviado para seu e-mail!");
	                return Response.ok(json).status(Response.Status.OK).build();
				} catch (Exception e) {
					MensagemJson json = new MensagemJson("Ocorreu um erro ao tentar enviar o e-mail para redefinir sua senha!");
	                return Response.ok(json).status(Response.Status.OK).build();
				}
            } else {
                MensagemJson json = new MensagemJson("Não existe uma conta com esse e-mail!");
                return Response.ok(json).status(Response.Status.OK).build();
            }
        } else {
            MensagemJson json = new MensagemJson("Informe o e-mail!");
            return Response.ok(json).status(Response.Status.OK).build();
        }
    }
    
}
