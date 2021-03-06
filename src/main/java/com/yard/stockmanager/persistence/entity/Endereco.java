package com.yard.stockmanager.persistence.entity;
// Generated 20/10/2019 19:04:16 by Hibernate Tools 4.3.1


import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;
import org.hibernate.Session;

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

/**
 * Endereco generated by hbm2java
 */
@Entity
@Table(name="endereco"
    ,catalog="stockmanager"
)
public class Endereco  implements java.io.Serializable {


     private int id;
     private Cidade cidade;
    private String endereco;
     private String rua;
     private String numero;
     private String bairro;
     private String cep;
     private String complementos;
     private char ativo;
     private Set<Pessoa> pessoas = new HashSet<Pessoa>(0);
     private Set<Estoque> estoques = new HashSet<Estoque>(0);
     private Set<Nfe> nfesForEnderecodestId = new HashSet<Nfe>(0);
     private Set<Nfe> nfesForEnderecoremId = new HashSet<Nfe>(0);

    public Endereco() {
    }


    public Endereco(int id, Cidade cidade, String rua, String numero, String cep, char ativo) {
        this.id = id;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.ativo = ativo;
    }
    public Endereco(int id, Cidade cidade, String rua, String numero, String bairro, String cep, String complementos, char ativo, Set<Pessoa> pessoas, Set<Estoque> estoques, Set<Nfe> nfesForEnderecodestId, Set<Nfe> nfesForEnderecoremId) {
       this.id = id;
       this.cidade = cidade;
       this.rua = rua;
       this.numero = numero;
       this.bairro = bairro;
       this.cep = cep;
       this.complementos = complementos;
       this.ativo = ativo;
       this.pessoas = pessoas;
       this.estoques = estoques;
       this.nfesForEnderecodestId = nfesForEnderecodestId;
       this.nfesForEnderecoremId = nfesForEnderecoremId;
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
    @JoinColumn(name="cidade_id", nullable=false)
    public Cidade getCidade() {
        return this.cidade;
    }
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    
    @Column(name="rua", nullable=false, length=45)
    public String getRua() {
        return this.rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }

    @Column(name="endereco", nullable = false, length=100)
    public String getEndereco() { return this.endereco;}


    public void setEndereco(String endereco) { this.endereco = endereco; }

    @Column(name="numero", nullable=false, length=5)
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    @Column(name="bairro", length=45)
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    
    @Column(name="cep", nullable=false, length=10)
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    
    @Column(name="complementos", length=25)
    public String getComplementos() {
        return this.complementos;
    }
    
    public void setComplementos(String complementos) {
        this.complementos = complementos;
    }

    
    @Column(name="ativo", nullable=false, length=1)
    public char getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="endereco")
    public Set<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="endereco")
    public Set<Estoque> getEstoques() {
        return this.estoques;
    }
    
    public void setEstoques(Set<Estoque> estoques) {
        this.estoques = estoques;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="enderecoByEnderecodestId")
    public Set<Nfe> getNfesForEnderecodestId() {
        return this.nfesForEnderecodestId;
    }
    
    public void setNfesForEnderecodestId(Set<Nfe> nfesForEnderecodestId) {
        this.nfesForEnderecodestId = nfesForEnderecodestId;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="enderecoByEnderecoremId")
    public Set<Nfe> getNfesForEnderecoremId() {
        return this.nfesForEnderecoremId;
    }
    
    public void setNfesForEnderecoremId(Set<Nfe> nfesForEnderecoremId) {
        this.nfesForEnderecoremId = nfesForEnderecoremId;
    }

    @Override
    public String toString() {
        return rua +" - Nº "+ numero +", " + bairro +", "+ cidade;
    }
}


