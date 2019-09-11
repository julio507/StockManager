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
public class PessoaHasTelefonesPK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "Pessoa_id")
    private int pessoaid;
    @Basic(optional = false)
    @Column(name = "Telefones_id")
    private int telefonesid;

    public PessoaHasTelefonesPK()
    {
    }

    public PessoaHasTelefonesPK(int pessoaid, int telefonesid)
    {
        this.pessoaid = pessoaid;
        this.telefonesid = telefonesid;
    }

    public int getPessoaid()
    {
        return pessoaid;
    }

    public void setPessoaid(int pessoaid)
    {
        this.pessoaid = pessoaid;
    }

    public int getTelefonesid()
    {
        return telefonesid;
    }

    public void setTelefonesid(int telefonesid)
    {
        this.telefonesid = telefonesid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) pessoaid;
        hash += (int) telefonesid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaHasTelefonesPK))
        {
            return false;
        }
        PessoaHasTelefonesPK other = (PessoaHasTelefonesPK) object;
        if (this.pessoaid != other.pessoaid)
        {
            return false;
        }
        if (this.telefonesid != other.telefonesid)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.PessoaHasTelefonesPK[ pessoaid=" + pessoaid + ", telefonesid=" + telefonesid + " ]";
    }
    
}
