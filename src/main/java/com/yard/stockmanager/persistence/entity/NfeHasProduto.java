/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "nfe_has_produto")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "NfeHasProduto.findAll", query = "SELECT n FROM NfeHasProduto n"),
    @NamedQuery(name = "NfeHasProduto.findByNFEid", query = "SELECT n FROM NfeHasProduto n WHERE n.nfeHasProdutoPK.nFEid = :nFEid"),
    @NamedQuery(name = "NfeHasProduto.findByNFEPessoaid", query = "SELECT n FROM NfeHasProduto n WHERE n.nfeHasProdutoPK.nFEPessoaid = :nFEPessoaid"),
    @NamedQuery(name = "NfeHasProduto.findByProdutoid", query = "SELECT n FROM NfeHasProduto n WHERE n.nfeHasProdutoPK.produtoid = :produtoid"),
    @NamedQuery(name = "NfeHasProduto.findByQuantidade", query = "SELECT n FROM NfeHasProduto n WHERE n.quantidade = :quantidade"),
    @NamedQuery(name = "NfeHasProduto.findByValorProdutos", query = "SELECT n FROM NfeHasProduto n WHERE n.valorProdutos = :valorProdutos")
})
public class NfeHasProduto implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NfeHasProdutoPK nfeHasProdutoPK;
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private double quantidade;
    @Basic(optional = false)
    @Column(name = "ValorProdutos")
    private double valorProdutos;
    @JoinColumns(
    {
        @JoinColumn(name = "NFE_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "NFE_Pessoa_id", referencedColumnName = "Pessoa_id", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Nfe nfe;
    @JoinColumn(name = "Produto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public NfeHasProduto()
    {
    }

    public NfeHasProduto(NfeHasProdutoPK nfeHasProdutoPK)
    {
        this.nfeHasProdutoPK = nfeHasProdutoPK;
    }

    public NfeHasProduto(NfeHasProdutoPK nfeHasProdutoPK, double quantidade, double valorProdutos)
    {
        this.nfeHasProdutoPK = nfeHasProdutoPK;
        this.quantidade = quantidade;
        this.valorProdutos = valorProdutos;
    }

    public NfeHasProduto(int nFEid, int nFEPessoaid, int produtoid)
    {
        this.nfeHasProdutoPK = new NfeHasProdutoPK(nFEid, nFEPessoaid, produtoid);
    }

    public NfeHasProdutoPK getNfeHasProdutoPK()
    {
        return nfeHasProdutoPK;
    }

    public void setNfeHasProdutoPK(NfeHasProdutoPK nfeHasProdutoPK)
    {
        this.nfeHasProdutoPK = nfeHasProdutoPK;
    }

    public double getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(double quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getValorProdutos()
    {
        return valorProdutos;
    }

    public void setValorProdutos(double valorProdutos)
    {
        this.valorProdutos = valorProdutos;
    }

    public Nfe getNfe()
    {
        return nfe;
    }

    public void setNfe(Nfe nfe)
    {
        this.nfe = nfe;
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
        hash += (nfeHasProdutoPK != null ? nfeHasProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfeHasProduto))
        {
            return false;
        }
        NfeHasProduto other = (NfeHasProduto) object;
        if ((this.nfeHasProdutoPK == null && other.nfeHasProdutoPK != null) || (this.nfeHasProdutoPK != null && !this.nfeHasProdutoPK.equals(other.nfeHasProdutoPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.NfeHasProduto[ nfeHasProdutoPK=" + nfeHasProdutoPK + " ]";
    }
    
}
