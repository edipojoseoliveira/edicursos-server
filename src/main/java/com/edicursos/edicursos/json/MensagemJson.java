package com.edicursos.edicursos.json;

import java.util.Objects;

/**
 *
 * @author edipo
 */
public class MensagemJson {
    private String mensagem;

    public MensagemJson(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.mensagem);
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
        final MensagemJson other = (MensagemJson) obj;
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        return true;
    }
    
}
