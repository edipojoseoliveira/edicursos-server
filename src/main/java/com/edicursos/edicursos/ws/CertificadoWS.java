package com.edicursos.edicursos.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.edicursos.edicursos.util.RelatorioUtil;

/**
*
* @author edipo
*/
@Path("/certificado")
public class CertificadoWS {
	
	private String caminhoRelatorios = null;
	private String caminhoImagens = null;

	@SuppressWarnings("rawtypes")
	@GET
    @Path("/imprimir/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public synchronized Response imprimir(@PathParam("codigo") String codigo) {
		try {
			HashMap<String, String> parametrosRelatorio = this.carregaParametrosRelatorio();
			
			Integer tipo = 1;
			RelatorioUtil relatorioUtil = new RelatorioUtil();
			boolean relatorioGerado = relatorioUtil.geraRelatorio(parametrosRelatorio, "certificado-logica-programacao-app-inventor", 
					tipo, new ArrayList(), this.getCaminhoRelatorios(), null);
			
			if (relatorioGerado) {
				File file = new File(this.getCaminhoRelatorios() + "certificado-logica-programacao-app-inventor" + ".pdf");
				FileInputStream fileInputStream = new FileInputStream(file);
				ResponseBuilder responseBuilder = Response.ok((Object) fileInputStream);
				responseBuilder.type("application/pdf");
				responseBuilder.header("Content-Disposition", "filename=" + "certificado-logica-programacao-app-inventor" + ".pdf");
				
				return responseBuilder.build();
			} else {
				return Response.ok("ERROR").status(Response.Status.OK).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok("ERROR").status(Response.Status.OK).build();
		}
	}
	
	public HashMap<String, String> carregaParametrosRelatorio() {
		HashMap<String, String> parametrosRelatorio = new HashMap<>();
		try {
			parametrosRelatorio.put("PATH_IMAGE", this.getCaminhoImagens() + "modelo-certificado-01.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parametrosRelatorio;
	}

	public String getCaminhoRelatorios() throws IOException {
		if (this.caminhoRelatorios == null) {
			caminhoRelatorios = new File(".").getCanonicalPath().concat("\\webapps\\EdiCursos\\WEB-INF\\relatorios\\");
		}
		return caminhoRelatorios;
	}

	public void setCaminhoRelatorios(String caminhoRelatorios) {
		this.caminhoRelatorios = caminhoRelatorios;
	}

	public String getCaminhoImagens() throws IOException {
		if (this.caminhoImagens == null) {
			caminhoImagens = new File(".").getCanonicalPath().concat("\\webapps\\EdiCursos\\WEB-INF\\imagens\\");
		}
		return caminhoImagens;
	}

	public void setCaminhoImagens(String caminhoImagens) {
		this.caminhoImagens = caminhoImagens;
	}
	
}
