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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findById", query = "SELECT f FROM Funcionario f WHERE f.id = :id"),
    @NamedQuery(name = "Funcionario.findByLogin", query = "SELECT f FROM Funcionario f WHERE f.login = :login"),
    @NamedQuery(name = "Funcionario.findBySenha", query = "SELECT f FROM Funcionario f WHERE f.senha = :senha"),
    @NamedQuery(name = "Funcionario.findByFuncao", query = "SELECT f FROM Funcionario f WHERE f.funcao = :funcao"),
    @NamedQuery(name = "Funcionario.findByNivelAcesso", query = "SELECT f FROM Funcionario f WHERE f.nivelAcesso = :nivelAcesso")
})
public class Funcionario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @Column(name = "Senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "Funcao")
    private String funcao;
    @Basic(optional = false)
    @Column(name = "NivelAcesso")
    private int nivelAcesso;
    @JoinColumn(name = "Pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Agendamento> agendamentoList;

    public Funcionario()
    {
    }

    public Funcionario(Integer id)
    {
        this.id = id;
    }

    public Funcionario(Integer id, String login, String senha, String funcao, int nivelAcesso)
    {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.funcao = funcao;
        this.nivelAcesso = nivelAcesso;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getFuncao()
    {
        return funcao;
    }

    public void setFuncao(String funcao)
    {
        this.funcao = funcao;
    }

    public int getNivelAcesso()
    {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso)
    {
        this.nivelAcesso = nivelAcesso;
    }

    public Pessoa getPessoaid()
    {
        return pessoaid;
    }

    public void setPessoaid(Pessoa pessoaid)
    {
        this.pessoaid = pessoaid;
    }

    @XmlTransient
    public List<Agendamento> getAgendamentoList()
    {
        return agendamentoList;
    }

    public void setAgendamentoList(List<Agendamento> agendamentoList)
    {
        this.agendamentoList = agendamentoList;
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
        if (!(object instanceof Funcionario))
        {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Funcionario[ id=" + id + " ]";
    }
    
}
