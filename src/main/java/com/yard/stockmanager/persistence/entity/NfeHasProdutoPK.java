/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Julio
 */
@Embeddable
public class NfeHasProdutoPK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "NFE_id")
    private int nFEid;
    @Basic(optional = false)
    @Column(name = "NFE_Pessoa_id")
    private int nFEPessoaid;
    @Basic(optional = false)
    @Column(name = "Produto_id")
    private int produtoid;

    public NfeHasProdutoPK()
    {
    }

    public NfeHasProdutoPK(int nFEid, int nFEPessoaid, int produtoid)
    {
        this.nFEid = nFEid;
        this.nFEPessoaid = nFEPessoaid;
        this.produtoid = produtoid;
    }

    public int getNFEid()
    {
        return nFEid;
    }

    public void setNFEid(int nFEid)
    {
        this.nFEid = nFEid;
    }

    public int getNFEPessoaid()
    {
        return nFEPessoaid;
    }

    public void setNFEPessoaid(int nFEPessoaid)
    {
        this.nFEPessoaid = nFEPessoaid;
    }

    public int getProdutoid()
    {
        return produtoid;
    }

    public void setProdutoid(int produtoid)
    {
        this.produtoid = produtoid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) nFEid;
        hash += (int) nFEPessoaid;
        hash += (int) produtoid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfeHasProdutoPK))
        {
            return false;
        }
        NfeHasProdutoPK other = (NfeHasProdutoPK) object;
        if (this.nFEid != other.nFEid)
        {
            return false;
        }
        if (this.nFEPessoaid != other.nFEPessoaid)
        {
            return false;
        }
        if (this.produtoid != other.produtoid)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.NfeHasProdutoPK[ nFEid=" + nFEid + ", nFEPessoaid=" + nFEPessoaid + ", produtoid=" + produtoid + " ]";
    }
    
}
