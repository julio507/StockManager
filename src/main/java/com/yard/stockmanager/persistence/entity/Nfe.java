/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "nfe")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Nfe.findAll", query = "SELECT n FROM Nfe n"),
    @NamedQuery(name = "Nfe.findById", query = "SELECT n FROM Nfe n WHERE n.nfePK.id = :id"),
    @NamedQuery(name = "Nfe.findByPessoaid", query = "SELECT n FROM Nfe n WHERE n.nfePK.pessoaid = :pessoaid"),
    @NamedQuery(name = "Nfe.findByNumNF", query = "SELECT n FROM Nfe n WHERE n.numNF = :numNF"),
    @NamedQuery(name = "Nfe.findByDataEmissao", query = "SELECT n FROM Nfe n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "Nfe.findByValorNota", query = "SELECT n FROM Nfe n WHERE n.valorNota = :valorNota"),
    @NamedQuery(name = "Nfe.findByTipo", query = "SELECT n FROM Nfe n WHERE n.tipo = :tipo")
})
public class Nfe implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NfePK nfePK;
    @Column(name = "NumNF")
    private Integer numNF;
    @Basic(optional = false)
    @Column(name = "DataEmissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "ValorNota")
    private BigDecimal valorNota;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private Character tipo;
    @Lob
    @Column(name = "observa\u00e7\u00f5es")
    private String observações;
    @JoinColumn(name = "Pessoa_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nfe", fetch = FetchType.LAZY)
    private List<NfeHasProduto> nfeHasProdutoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nfe", fetch = FetchType.LAZY)
    private List<Agendamento> agendamentoList;

    public Nfe()
    {
    }

    public Nfe(NfePK nfePK)
    {
        this.nfePK = nfePK;
    }

    public Nfe(NfePK nfePK, Date dataEmissao, BigDecimal valorNota, Character tipo)
    {
        this.nfePK = nfePK;
        this.dataEmissao = dataEmissao;
        this.valorNota = valorNota;
        this.tipo = tipo;
    }

    public Nfe(int id, int pessoaid)
    {
        this.nfePK = new NfePK(id, pessoaid);
    }

    public NfePK getNfePK()
    {
        return nfePK;
    }

    public void setNfePK(NfePK nfePK)
    {
        this.nfePK = nfePK;
    }

    public Integer getNumNF()
    {
        return numNF;
    }

    public void setNumNF(Integer numNF)
    {
        this.numNF = numNF;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao)
    {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorNota()
    {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota)
    {
        this.valorNota = valorNota;
    }

    public Character getTipo()
    {
        return tipo;
    }

    public void setTipo(Character tipo)
    {
        this.tipo = tipo;
    }

    public String getObservações()
    {
        return observações;
    }

    public void setObservações(String observações)
    {
        this.observações = observações;
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    @XmlTransient
    public List<NfeHasProduto> getNfeHasProdutoList()
    {
        return nfeHasProdutoList;
    }

    public void setNfeHasProdutoList(List<NfeHasProduto> nfeHasProdutoList)
    {
        this.nfeHasProdutoList = nfeHasProdutoList;
    }

    @XmlTransient
    public List<Agendamento> getAgendamentoList()
    {
        return agendamentoList;
    }

    public void setAgendamentoList(List<Agendamento> agendamentoList)
    {
        this.agendamentoList = agendamentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (nfePK != null ? nfePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nfe))
        {
            return false;
        }
        Nfe other = (Nfe) object;
        if ((this.nfePK == null && other.nfePK != null) || (this.nfePK != null && !this.nfePK.equals(other.nfePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Nfe[ nfePK=" + nfePK + " ]";
    }
    
}
