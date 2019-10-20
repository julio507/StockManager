package com.yard.stockmanager.persistence.entity;
// Generated 20/10/2019 19:04:16 by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PessoaHasTipo generated by hbm2java
 */
@Entity
@Table(name="pessoa_has_tipo"
    ,catalog="stockmanager"
)
public class PessoaHasTipo  implements java.io.Serializable {


     private PessoaHasTipoId id;
     private Pessoa pessoa;
     private Tipo tipo;
     private char ativo;

    public PessoaHasTipo() {
    }

    public PessoaHasTipo(PessoaHasTipoId id, Pessoa pessoa, Tipo tipo, char ativo) {
       this.id = id;
       this.pessoa = pessoa;
       this.tipo = tipo;
       this.ativo = ativo;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pessoaId", column=@Column(name="pessoa_id", nullable=false) ), 
        @AttributeOverride(name="tipoId", column=@Column(name="tipo_id", nullable=false) ) } )
    public PessoaHasTipoId getId() {
        return this.id;
    }
    
    public void setId(PessoaHasTipoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pessoa_id", nullable=false, insertable=false, updatable=false)
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipo_id", nullable=false, insertable=false, updatable=false)
    public Tipo getTipo() {
        return this.tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="ativo", nullable=false, length=1)
    public char getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }




}

