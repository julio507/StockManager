/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "agendamento")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Agendamento.findAll", query = "SELECT a FROM Agendamento a"),
    @NamedQuery(name = "Agendamento.findById", query = "SELECT a FROM Agendamento a WHERE a.agendamentoPK.id = :id"),
    @NamedQuery(name = "Agendamento.findByNFEid", query = "SELECT a FROM Agendamento a WHERE a.agendamentoPK.nFEid = :nFEid"),
    @NamedQuery(name = "Agendamento.findByNFEPessoaid", query = "SELECT a FROM Agendamento a WHERE a.agendamentoPK.nFEPessoaid = :nFEPessoaid"),
    @NamedQuery(name = "Agendamento.findByFuncionarioid", query = "SELECT a FROM Agendamento a WHERE a.agendamentoPK.funcionarioid = :funcionarioid"),
    @NamedQuery(name = "Agendamento.findByDescricao", query = "SELECT a FROM Agendamento a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Agendamento.findByData", query = "SELECT a FROM Agendamento a WHERE a.data = :data"),
    @NamedQuery(name = "Agendamento.findByEstado", query = "SELECT a FROM Agendamento a WHERE a.estado = :estado")
})
public class Agendamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AgendamentoPK agendamentoPK;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "Estado")
    private Character estado;
    @JoinColumn(name = "Funcionario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @JoinColumns(
    {
        @JoinColumn(name = "NFE_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "NFE_Pessoa_id", referencedColumnName = "Pessoa_id", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Nfe nfe;

    public Agendamento()
    {
    }

    public Agendamento(AgendamentoPK agendamentoPK)
    {
        this.agendamentoPK = agendamentoPK;
    }

    public Agendamento(AgendamentoPK agendamentoPK, Character estado)
    {
        this.agendamentoPK = agendamentoPK;
        this.estado = estado;
    }

    public Agendamento(int id, int nFEid, int nFEPessoaid, int funcionarioid)
    {
        this.agendamentoPK = new AgendamentoPK(id, nFEid, nFEPessoaid, funcionarioid);
    }

    public AgendamentoPK getAgendamentoPK()
    {
        return agendamentoPK;
    }

    public void setAgendamentoPK(AgendamentoPK agendamentoPK)
    {
        this.agendamentoPK = agendamentoPK;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Character getEstado()
    {
        return estado;
    }

    public void setEstado(Character estado)
    {
        this.estado = estado;
    }

    public Funcionario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
    }

    public Nfe getNfe()
    {
        return nfe;
    }

    public void setNfe(Nfe nfe)
    {
        this.nfe = nfe;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (agendamentoPK != null ? agendamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agendamento))
        {
            return false;
        }
        Agendamento other = (Agendamento) object;
        if ((this.agendamentoPK == null && other.agendamentoPK != null) || (this.agendamentoPK != null && !this.agendamentoPK.equals(other.agendamentoPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Agendamento[ agendamentoPK=" + agendamentoPK + " ]";
    }
    
}
