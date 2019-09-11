/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "estoque_has_produto")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstoqueHasProduto.findAll", query = "SELECT e FROM EstoqueHasProduto e"),
    @NamedQuery(name = "EstoqueHasProduto.findByEstoqueid", query = "SELECT e FROM EstoqueHasProduto e WHERE e.estoqueHasProdutoPK.estoqueid = :estoqueid"),
    @NamedQuery(name = "EstoqueHasProduto.findByProdutoid", query = "SELECT e FROM EstoqueHasProduto e WHERE e.estoqueHasProdutoPK.produtoid = :produtoid"),
    @NamedQuery(name = "EstoqueHasProduto.findByQuantidade", query = "SELECT e FROM EstoqueHasProduto e WHERE e.quantidade = :quantidade")
})
public class EstoqueHasProduto implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstoqueHasProdutoPK estoqueHasProdutoPK;
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private double quantidade;
    @JoinColumn(name = "Estoque_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estoque estoque;
    @JoinColumn(name = "Produto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produto produto;

    public EstoqueHasProduto()
    {
    }

    public EstoqueHasProduto(EstoqueHasProdutoPK estoqueHasProdutoPK)
    {
        this.estoqueHasProdutoPK = estoqueHasProdutoPK;
    }

    public EstoqueHasProduto(EstoqueHasProdutoPK estoqueHasProdutoPK, double quantidade)
    {
        this.estoqueHasProdutoPK = estoqueHasProdutoPK;
        this.quantidade = quantidade;
    }

    public EstoqueHasProduto(int estoqueid, int produtoid)
    {
        this.estoqueHasProdutoPK = new EstoqueHasProdutoPK(estoqueid, produtoid);
    }

    public EstoqueHasProdutoPK getEstoqueHasProdutoPK()
    {
        return estoqueHasProdutoPK;
    }

    public void setEstoqueHasProdutoPK(EstoqueHasProdutoPK estoqueHasProdutoPK)
    {
        this.estoqueHasProdutoPK = estoqueHasProdutoPK;
    }

    public double getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(double quantidade)
    {
        this.quantidade = quantidade;
    }

    public Estoque getEstoque()
    {
        return estoque;
    }

    public void setEstoque(Estoque estoque)
    {
        this.estoque = estoque;
    }

    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (estoqueHasProdutoPK != null ? estoqueHasProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoqueHasProduto))
        {
            return false;
        }
        EstoqueHasProduto other = (EstoqueHasProduto) object;
        if ((this.estoqueHasProdutoPK == null && other.estoqueHasProdutoPK != null) || (this.estoqueHasProdutoPK != null && !this.estoqueHasProdutoPK.equals(other.estoqueHasProdutoPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.EstoqueHasProduto[ estoqueHasProdutoPK=" + estoqueHasProdutoPK + " ]";
    }
    
}
