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
public class AgendamentoPK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "NFE_id")
    private int nFEid;
    @Basic(optional = false)
    @Column(name = "NFE_Pessoa_id")
    private int nFEPessoaid;
    @Basic(optional = false)
    @Column(name = "Funcionario_id")
    private int funcionarioid;

    public AgendamentoPK()
    {
    }

    public AgendamentoPK(int id, int nFEid, int nFEPessoaid, int funcionarioid)
    {
        this.id = id;
        this.nFEid = nFEid;
        this.nFEPessoaid = nFEPessoaid;
        this.funcionarioid = funcionarioid;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public int getFuncionarioid()
    {
        return funcionarioid;
    }

    public void setFuncionarioid(int funcionarioid)
    {
        this.funcionarioid = funcionarioid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) id;
        hash += (int) nFEid;
        hash += (int) nFEPessoaid;
        hash += (int) funcionarioid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendamentoPK))
        {
            return false;
        }
        AgendamentoPK other = (AgendamentoPK) object;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.nFEid != other.nFEid)
        {
            return false;
        }
        if (this.nFEPessoaid != other.nFEPessoaid)
        {
            return false;
        }
        if (this.funcionarioid != other.funcionarioid)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.AgendamentoPK[ id=" + id + ", nFEid=" + nFEid + ", nFEPessoaid=" + nFEPessoaid + ", funcionarioid=" + funcionarioid + " ]";
    }
    
}
