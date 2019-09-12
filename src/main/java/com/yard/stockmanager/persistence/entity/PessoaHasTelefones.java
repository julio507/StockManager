/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "pessoa_has_telefones")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PessoaHasTelefones.findAll", query = "SELECT p FROM PessoaHasTelefones p"),
    @NamedQuery(name = "PessoaHasTelefones.findByPessoaid", query = "SELECT p FROM PessoaHasTelefones p WHERE p.pessoaHasTelefonesPK.pessoaid = :pessoaid"),
    @NamedQuery(name = "PessoaHasTelefones.findByTelefonesid", query = "SELECT p FROM PessoaHasTelefones p WHERE p.pessoaHasTelefonesPK.telefonesid = :telefonesid")
})
public class PessoaHasTelefones implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PessoaHasTelefonesPK pessoaHasTelefonesPK;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "Pessoa_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "Telefones_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Telefones telefones;

    public PessoaHasTelefones()
    {
    }

    public PessoaHasTelefones(PessoaHasTelefonesPK pessoaHasTelefonesPK)
    {
        this.pessoaHasTelefonesPK = pessoaHasTelefonesPK;
    }

    public PessoaHasTelefones(int pessoaid, int telefonesid)
    {
        this.pessoaHasTelefonesPK = new PessoaHasTelefonesPK(pessoaid, telefonesid);
    }

    public PessoaHasTelefonesPK getPessoaHasTelefonesPK()
    {
        return pessoaHasTelefonesPK;
    }

    public void setPessoaHasTelefonesPK(PessoaHasTelefonesPK pessoaHasTelefonesPK)
    {
        this.pessoaHasTelefonesPK = pessoaHasTelefonesPK;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    public Telefones getTelefones()
    {
        return telefones;
    }

    public void setTelefones(Telefones telefones)
    {
        this.telefones = telefones;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pessoaHasTelefonesPK != null ? pessoaHasTelefonesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaHasTelefones))
        {
            return false;
        }
        PessoaHasTelefones other = (PessoaHasTelefones) object;
        if ((this.pessoaHasTelefonesPK == null && other.pessoaHasTelefonesPK != null) || (this.pessoaHasTelefonesPK != null && !this.pessoaHasTelefonesPK.equals(other.pessoaHasTelefonesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.PessoaHasTelefones[ pessoaHasTelefonesPK=" + pessoaHasTelefonesPK + " ]";
    }
    
}
