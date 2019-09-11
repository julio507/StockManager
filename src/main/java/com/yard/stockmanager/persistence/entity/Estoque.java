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
import javax.persistence.Lob;
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
@Table(name = "estoque")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e"),
    @NamedQuery(name = "Estoque.findById", query = "SELECT e FROM Estoque e WHERE e.id = :id"),
    @NamedQuery(name = "Estoque.findByNome", query = "SELECT e FROM Estoque e WHERE e.nome = :nome"),
    @NamedQuery(name = "Estoque.findByTelefone", query = "SELECT e FROM Estoque e WHERE e.telefone = :telefone")
})
public class Estoque implements Serializable
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
    @Lob
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Telefone")
    private String telefone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estoque", fetch = FetchType.LAZY)
    private List<EstoqueHasProduto> estoqueHasProdutoList;
    @JoinColumn(name = "Endereco_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco enderecoid;

    public Estoque()
    {
    }

    public Estoque(Integer id)
    {
        this.id = id;
    }

    public Estoque(Integer id, String nome)
    {
        this.id = id;
        this.nome = nome;
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    @XmlTransient
    public List<EstoqueHasProduto> getEstoqueHasProdutoList()
    {
        return estoqueHasProdutoList;
    }

    public void setEstoqueHasProdutoList(List<EstoqueHasProduto> estoqueHasProdutoList)
    {
        this.estoqueHasProdutoList = estoqueHasProdutoList;
    }

    public Endereco getEnderecoid()
    {
        return enderecoid;
    }

    public void setEnderecoid(Endereco enderecoid)
    {
        this.enderecoid = enderecoid;
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
        if (!(object instanceof Estoque))
        {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Estoque[ id=" + id + " ]";
    }
    
}
