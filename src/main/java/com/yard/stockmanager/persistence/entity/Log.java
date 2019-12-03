package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="log"
        ,catalog="stockmanager"
)
public class Log {

    private int id;
    private Funcionario funcionario;
    private Date data;
    private char tipo;
    private String mensagem;

    public Log() {
    }

    public Log(int id, Funcionario funcionario, Date data, char tipo, String mensagem) {
        this.id = id;
        this.funcionario = funcionario;
        this.data = data;
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    @Id


    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="funcionario_id", nullable=false)
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data", nullable=false, length=19)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Column(name="tipo", nullable=false, length=1)
    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    @Column(name="mensagem", length=65535)
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
