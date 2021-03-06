package com.yard.stockmanager.persistence.entity;
// Generated 20/10/2019 19:04:16 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Nfe generated by hbm2java
 */
@Entity
@Table(name="nfe"
    ,catalog="stockmanager"
)
public class Nfe  implements java.io.Serializable {


     private int id;
     private Endereco enderecoByEnderecodestId;
     private Endereco enderecoByEnderecoremId;
     private Pessoa pessoaByPessoadestId;
     private Pessoa pessoaByPessoaremId;
     private Integer numnf;
     private Date dataemissao;
     private BigDecimal valornota;
     private char tipo;
     private String observacoes;
     private char ativo;
     private Set<NfeHasProduto> nfeHasProdutos = new HashSet<NfeHasProduto>(0);

    public Nfe() {
    }

	
    public Nfe(int id, Endereco enderecoByEnderecodestId, Endereco enderecoByEnderecoremId, Pessoa pessoaByPessoadestId, Pessoa pessoaByPessoaremId, Date dataemissao, BigDecimal valornota, char tipo, char ativo) {
        this.id = id;
        this.enderecoByEnderecodestId = enderecoByEnderecodestId;
        this.enderecoByEnderecoremId = enderecoByEnderecoremId;
        this.pessoaByPessoadestId = pessoaByPessoadestId;
        this.pessoaByPessoaremId = pessoaByPessoaremId;
        this.dataemissao = dataemissao;
        this.valornota = valornota;
        this.tipo = tipo;
        this.ativo = ativo;
    }
    public Nfe(int id, Endereco enderecoByEnderecodestId, Endereco enderecoByEnderecoremId, Pessoa pessoaByPessoadestId, Pessoa pessoaByPessoaremId, Integer numnf, Date dataemissao, BigDecimal valornota, char tipo, String observacoes, char ativo, Set<NfeHasProduto> nfeHasProdutos) {
       this.id = id;
       this.enderecoByEnderecodestId = enderecoByEnderecodestId;
       this.enderecoByEnderecoremId = enderecoByEnderecoremId;
       this.pessoaByPessoadestId = pessoaByPessoadestId;
       this.pessoaByPessoaremId = pessoaByPessoaremId;
       this.numnf = numnf;
       this.dataemissao = dataemissao;
       this.valornota = valornota;
       this.tipo = tipo;
       this.observacoes = observacoes;
       this.ativo = ativo;
       this.nfeHasProdutos = nfeHasProdutos;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="enderecodest_id", nullable=false)
    public Endereco getEnderecoByEnderecodestId() {
        return this.enderecoByEnderecodestId;
    }
    
    public void setEnderecoByEnderecodestId(Endereco enderecoByEnderecodestId) {
        this.enderecoByEnderecodestId = enderecoByEnderecodestId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="enderecorem_id", nullable=false)
    public Endereco getEnderecoByEnderecoremId() {
        return this.enderecoByEnderecoremId;
    }
    
    public void setEnderecoByEnderecoremId(Endereco enderecoByEnderecoremId) {
        this.enderecoByEnderecoremId = enderecoByEnderecoremId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pessoadest_id", nullable=false)
    public Pessoa getPessoaByPessoadestId() {
        return this.pessoaByPessoadestId;
    }
    
    public void setPessoaByPessoadestId(Pessoa pessoaByPessoadestId) {
        this.pessoaByPessoadestId = pessoaByPessoadestId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pessoarem_id", nullable=false)
    public Pessoa getPessoaByPessoaremId() {
        return this.pessoaByPessoaremId;
    }
    
    public void setPessoaByPessoaremId(Pessoa pessoaByPessoaremId) {
        this.pessoaByPessoaremId = pessoaByPessoaremId;
    }

    
    @Column(name="numnf")
    public Integer getNumnf() {
        return this.numnf;
    }
    
    public void setNumnf(Integer numnf) {
        this.numnf = numnf;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dataemissao", nullable=false, length=10)
    public Date getDataemissao() {
        return this.dataemissao;
    }
    
    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    
    @Column(name="valornota", nullable=false, precision=13, scale=3)
    public BigDecimal getValornota() {
        return this.valornota;
    }
    
    public void setValornota(BigDecimal valornota) {
        this.valornota = valornota;
    }

    
    @Column(name="tipo", nullable=false, length=1)
    public char getTipo() {
        return this.tipo;
    }
    
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="observacoes", length=65535)
    public String getObservacoes() {
        return this.observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    
    @Column(name="ativo", nullable=false, length=1)
    public char getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="nfe")
    public Set<NfeHasProduto> getNfeHasProdutos() {
        return this.nfeHasProdutos;
    }
    
    public void setNfeHasProdutos(Set<NfeHasProduto> nfeHasProdutos) {
        this.nfeHasProdutos = nfeHasProdutos;
    }




}


