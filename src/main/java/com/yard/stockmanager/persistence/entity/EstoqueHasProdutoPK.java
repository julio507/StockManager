/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Julio
 */
@Embeddable
public class EstoqueHasProdutoPK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "Estoque_id")
    private int estoqueid;
    @Basic(optional = false)
    @Column(name = "Produto_id")
    private int produtoid;

    public EstoqueHasProdutoPK()
    {
    }

    public EstoqueHasProdutoPK(int estoqueid, int produtoid)
    {
        this.estoqueid = estoqueid;
        this.produtoid = produtoid;
    }

    public int getEstoqueid()
    {
        return estoqueid;
    }

    public void setEstoqueid(int estoqueid)
    {
        this.estoqueid = estoqueid;
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
        hash += (int) estoqueid;
        hash += (int) produtoid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoqueHasProdutoPK))
        {
            return false;
        }
        EstoqueHasProdutoPK other = (EstoqueHasProdutoPK) object;
        if (this.estoqueid != other.estoqueid)
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
        return "com.yard.stockmanager.persistence.entity.EstoqueHasProdutoPK[ estoqueid=" + estoqueid + ", produtoid=" + produtoid + " ]";
    }
    
}
