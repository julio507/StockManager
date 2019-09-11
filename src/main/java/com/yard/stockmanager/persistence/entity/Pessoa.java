/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByDenominacaoSocial", query = "SELECT p FROM Pessoa p WHERE p.denominacaoSocial = :denominacaoSocial"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email"),
    @NamedQuery(name = "Pessoa.findByCnpj", query = "SELECT p FROM Pessoa p WHERE p.cnpj = :cnpj"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf")
})
public class Pessoa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DenominacaoSocial")
    private String denominacaoSocial;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "CNPJ")
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "CPF")
    private String cpf;
    @Lob
    @Column(name = "Observacoes")
    private String observacoes;
    @JoinTable(name = "pessoa_has_tipo", joinColumns =
    {
        @JoinColumn(name = "Pessoa_id", referencedColumnName = "id")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Tipo_id", referencedColumnName = "id")
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tipo> tipoList;
    @JoinColumn(name = "Endereco_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco enderecoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Nfe> nfeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<PessoaHasTelefones> pessoaHasTelefonesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaid", fetch = FetchType.LAZY)
    private List<Funcionario> funcionarioList;

    public Pessoa()
    {
    }

    public Pessoa(Integer id)
    {
        this.id = id;
    }

    public Pessoa(Integer id, String denominacaoSocial, String nome, String telefone, String cnpj, String cpf)
    {
        this.id = id;
        this.denominacaoSocial = denominacaoSocial;
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDenominacaoSocial()
    {
        return denominacaoSocial;
    }

    public void setDenominacaoSocial(String denominacaoSocial)
    {
        this.denominacaoSocial = denominacaoSocial;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getObservacoes()
    {
        return observacoes;
    }

    public void setObservacoes(String observacoes)
    {
        this.observacoes = observacoes;
    }

    @XmlTransient
    public List<Tipo> getTipoList()
    {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList)
    {
        this.tipoList = tipoList;
    }

    public Endereco getEnderecoid()
    {
        return enderecoid;
    }

    public void setEnderecoid(Endereco enderecoid)
    {
        this.enderecoid = enderecoid;
    }

    @XmlTransient
    public List<Nfe> getNfeList()
    {
        return nfeList;
    }

    public void setNfeList(List<Nfe> nfeList)
    {
        this.nfeList = nfeList;
    }

    @XmlTransient
    public List<PessoaHasTelefones> getPessoaHasTelefonesList()
    {
        return pessoaHasTelefonesList;
    }

    public void setPessoaHasTelefonesList(List<PessoaHasTelefones> pessoaHasTelefonesList)
    {
        this.pessoaHasTelefonesList = pessoaHasTelefonesList;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList()
    {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList)
    {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa))
        {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Pessoa[ id=" + id + " ]";
    }
    
}
