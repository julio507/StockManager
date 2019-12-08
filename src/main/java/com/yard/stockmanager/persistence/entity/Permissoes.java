package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
@Entity
@Table(name="permissoes"
        ,catalog="stockmanager"
)
public class Permissoes {
    private int id;
    private Funcionario funcionario;
    private String regra;
    private char ativo;

    public Permissoes() {
    }

    public Permissoes( int id, Funcionario funcionario, String regra, char ativo) {
        this.id = id;
        this.funcionario = funcionario;
        this.regra = regra;
        this.ativo = ativo;
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
    @JoinColumn(name="funcionario_id", nullable=false, insertable=false, updatable=false)
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Column(name="regra", nullable=false, length=100)
    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    @Column(name="ativo", nullable=false, length=1)
    public char isAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
