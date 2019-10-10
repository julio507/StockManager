/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "unidade")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Unidade.findAll", query = "SELECT u FROM Unidade u"),
    @NamedQuery(name = "Unidade.findById", query = "SELECT u FROM Unidade u WHERE u.id = :id"),
    @NamedQuery(name = "Unidade.findByNome", query = "SELECT u FROM Unidade u WHERE u.nome = :nome"),
    @NamedQuery(name = "Unidade.findBySigla", query = "SELECT u FROM Unidade u WHERE u.sigla = :sigla")
})
public class Unidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Sigla")
    private String sigla;
    @Lob
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeid")
    private Collection<Produto> produtoCollection;

    public Unidade()
    {
    }

    public Unidade(Integer id)
    {
        this.id = id;
    }

    public Unidade(Integer id, String nome, String sigla)
    {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSigla()
    {
        return sigla;
    }

    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Produto> getProdutoCollection()
    {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection)
    {
        this.produtoCollection = produtoCollection;
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
        if (!(object instanceof Unidade))
        {
            return false;
        }
        Unidade other = (Unidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Unidade[ id=" + id + " ]";
    }
    
}
