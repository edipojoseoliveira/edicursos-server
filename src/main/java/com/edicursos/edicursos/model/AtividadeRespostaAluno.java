package com.edicursos.edicursos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author edipo
 */
@Entity(name = "atividade_resposta_aluno")
public class AtividadeRespostaAluno implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "idatividadequestao")
    private AtividadeQuestao atividadeQuestao;
    @ManyToOne @JoinColumn(name = "idalunoaula")
    private AlunoAula alunoAula;
    @Column(name = "resposta", length = 100)
    private String resposta;
    @Column(name = "certa")
    private boolean certa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AtividadeQuestao getAtividadeQuestao() {
        return atividadeQuestao;
    }

    public void setAtividadeQuestao(AtividadeQuestao atividadeQuestao) {
        this.atividadeQuestao = atividadeQuestao;
    }

    public AlunoAula getAlunoAula() {
        return alunoAula;
    }

    public void setAlunoAula(AlunoAula alunoAula) {
        this.alunoAula = alunoAula;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isCerta() {
        return certa;
    }

    public void setCerta(boolean certa) {
        this.certa = certa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.atividadeQuestao);
        hash = 89 * hash + Objects.hashCode(this.alunoAula);
        hash = 89 * hash + Objects.hashCode(this.resposta);
        hash = 89 * hash + (this.certa ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AtividadeRespostaAluno other = (AtividadeRespostaAluno) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.atividadeQuestao, other.atividadeQuestao)) {
            return false;
        }
        if (!Objects.equals(this.alunoAula, other.alunoAula)) {
            return false;
        }
        if (!Objects.equals(this.resposta, other.resposta)) {
            return false;
        }
        if (this.certa != other.certa) {
            return false;
        }
        return true;
    }
    
}
