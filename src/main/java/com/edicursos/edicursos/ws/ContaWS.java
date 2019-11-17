package com.edicursos.edicursos.ws;

import com.edicursos.edicursos.json.ContaJson;
import com.edicursos.edicursos.json.MensagemJson;
import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.model.Conta;
import com.edicursos.edicursos.rn.AlunoRN;
import com.edicursos.edicursos.rn.ContaRN;
import com.edicursos.edicursos.util.EmailUtil;
import com.edicursos.edicursos.util.EncryptionUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
                	AlunoRN alunoRN = new AlunoRN();
                	Aluno aluno = alunoRN.carregarPorConta(conta);
                	
                	String mensagem = 
                	"<div style='font-family:Arial, Helvetica, sans-serif;'>" +
                		"<img style='margin-bottom: 10px;' src='https://www.edicursos.com.br/sources/logo-home-slide-um.png' width='160' alt='EdiCursos' />" +
                		"<hr />" +
                		"<p>Olá " + aluno.getNome() + ", tudo bem?</p>" +
                		"<p>Uma redefinição de senha foi solicitada para sua conta.</p>" +
                		"<p>Clique no botão abaixo para alterar sua senha.</p>" +
                		"<a target='_blank' href='https://www.edicursos.com.br/redefinir-senha.html?e=" + EncryptionUtil.encode(email) + "'>" +
                			"<button style='padding: 15px; border: 0;background-color: blue; color: white;'>Alterar minha senha</button>" +
                		"</a>" + 
                	"</div>";
                	
					EmailUtil emailUtil = new EmailUtil();
					emailUtil.enviarEmailComHtml("edicursosprofessor@gmail.com", email, "Redefinir senha", mensagem, null, null);
					
					MensagemJson json = new MensagemJson("E-mail enviado com sucesso!");
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
    
    @POST
    @Path("/redefinir-senha")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response redefinirSenha(Conta contaEditada) {
    	try {
			if (contaEditada != null && contaEditada.getEmail() != null && contaEditada.getSenha() != null) {
				String email = EncryptionUtil.decode(contaEditada.getEmail());
				
				ContaRN contaRN = new ContaRN();
				Conta contaAtual = contaRN.carregarPorEmail(email);
				
				contaAtual.setSenha(contaEditada.getSenha());
				contaRN.salvar(contaAtual);
				
				MensagemJson json = new MensagemJson("Senha redefinida com sucesso!");
	            return Response.ok(json).status(Response.Status.OK).build();
			} else {
				MensagemJson json = new MensagemJson("Não foi possível encontrar a conta para redefinir a senha!");
	            return Response.ok(json).status(Response.Status.OK).build();
			}
		} catch (Exception e) {
			MensagemJson json = new MensagemJson("Erro ao tentar redefinir a senha!");
            return Response.ok(json).status(Response.Status.OK).build();
		}
    }
    
}
