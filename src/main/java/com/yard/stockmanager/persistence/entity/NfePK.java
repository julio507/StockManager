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
public class NfePK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Pessoa_id")
    private int pessoaid;

    public NfePK()
    {
    }

    public NfePK(int id, int pessoaid)
    {
        this.id = id;
        this.pessoaid = pessoaid;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPessoaid()
    {
        return pessoaid;
    }

    public void setPessoaid(int pessoaid)
    {
        this.pessoaid = pessoaid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) id;
        hash += (int) pessoaid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfePK))
        {
            return false;
        }
        NfePK other = (NfePK) object;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.pessoaid != other.pessoaid)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.NfePK[ id=" + id + ", pessoaid=" + pessoaid + " ]";
    }
    
}
