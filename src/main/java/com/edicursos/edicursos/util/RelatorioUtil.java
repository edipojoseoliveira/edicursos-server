package com.edicursos.edicursos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class RelatorioUtil {

	public static final int RELATORIO_PDF = 1;
	public static final int RELATORIO_EXCEL = 2;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorio, 
			Integer tipoRelatorio, List listaInformacoes, String caminhoRelatorio, String imagemFundo) {
		boolean relatorioGerado = false;
		
		try {
			String caminhoArquivoJasper = caminhoRelatorio + nomeRelatorio + ".jasper";
			
			if (imagemFundo != null && imagemFundo != "" && !imagemFundo.trim().isEmpty()) {
				byte[] bFile = readBytesFromFile(caminhoRelatorio + imagemFundo.trim());
				if (bFile != null) {
					parametrosRelatorio.put("IMAGEM_FUNDO", bFile);
				}
			}
			
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio,
					new JRBeanCollectionDataSource(listaInformacoes, false));
			
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			jasperPrints.add(impressoraJasper);
			
			Exporter tipoArquivoExportado = null;
			String extensaoArquivoExportado = "";
			File arquivoGerado = null;
			
			switch (tipoRelatorio) {
			case RelatorioUtil.RELATORIO_PDF:
				tipoArquivoExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			case RelatorioUtil.RELATORIO_EXCEL:
				tipoArquivoExportado = new JRXlsExporter();
				extensaoArquivoExportado = "xls";
				break;
			default:
				tipoArquivoExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			}
			
			caminhoRelatorio = caminhoRelatorio + nomeRelatorio + "." + extensaoArquivoExportado;
			arquivoGerado = new File(caminhoRelatorio);
			
			tipoArquivoExportado.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
			tipoArquivoExportado.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
			
			tipoArquivoExportado.exportReport();
			arquivoGerado.deleteOnExit();
			
			relatorioGerado = true;
		} catch (JRException e) {
			e.printStackTrace();
			relatorioGerado = false;
		} catch (Exception e) {
			e.printStackTrace();
			relatorioGerado = false;
		}
		
		return relatorioGerado;
	}
	
	private static byte[] readBytesFromFile(String filePath) {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return bytesArray;
	}
	
}
