package com.yard.stockmanager.persistence.entity;
// Generated 20/10/2019 19:04:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Unidade generated by hbm2java
 */
@Entity
@Table(name="unidade"
    ,catalog="stockmanager"
    , uniqueConstraints = {@UniqueConstraint(columnNames="nome"), @UniqueConstraint(columnNames="sigla")} 
)
public class Unidade  implements java.io.Serializable {


     private int id;
     private String nome;
     private String sigla;
     private String descricao;
     private char ativo;
     private Set<Produto> produtos = new HashSet<Produto>(0);

    public Unidade() {
    }

	
    public Unidade(int id, String nome, String sigla, char ativo) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.ativo = ativo;
    }
    public Unidade(int id, String nome, String sigla, String descricao, char ativo, Set<Produto> produtos) {
       this.id = id;
       this.nome = nome;
       this.sigla = sigla;
       this.descricao = descricao;
       this.ativo = ativo;
       this.produtos = produtos;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="nome", unique=true, nullable=false, length=45)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="sigla", unique=true, nullable=false, length=2)
    public String getSigla() {
        return this.sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
    @Column(name="descricao", length=65535)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="ativo", nullable=false, length=1)
    public char getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="unidade")
    public Set<Produto> getProdutos() {
        return this.produtos;
    }
    
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return nome;
    }
}


