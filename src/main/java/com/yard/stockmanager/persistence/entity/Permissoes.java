package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
@Entity
@Table(name="permissoes"
        ,catalog="stockmanager"
)
public class Permissoes {
    private PermissoesId id;
    private Funcionario funcionario;
    private String regra;

    public Permissoes() {
    }

    public Permissoes(Funcionario funcionario, String regra) {
        this.funcionario = funcionario;
        this.regra = regra;
    }

    @EmbeddedId
    @AttributeOverrides( {
            @AttributeOverride(name="funcionarioId", column=@Column(name="funcionario_id", nullable=false) ) } )
    public PermissoesId getId() {
        return id;
    }

    public void setId(PermissoesId id) {
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


}
