/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "telefones")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Telefones.findAll", query = "SELECT t FROM Telefones t"),
    @NamedQuery(name = "Telefones.findById", query = "SELECT t FROM Telefones t WHERE t.id = :id"),
    @NamedQuery(name = "Telefones.findByNumero", query = "SELECT t FROM Telefones t WHERE t.numero = :numero")
})
public class Telefones implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Numero")
    private String numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telefones")
    private Collection<PessoaHasTelefones> pessoaHasTelefonesCollection;

    public Telefones()
    {
    }

    public Telefones(Integer id)
    {
        this.id = id;
    }

    public Telefones(Integer id, String numero)
    {
        this.id = id;
        this.numero = numero;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<PessoaHasTelefones> getPessoaHasTelefonesCollection()
    {
        return pessoaHasTelefonesCollection;
    }

    public void setPessoaHasTelefonesCollection(Collection<PessoaHasTelefones> pessoaHasTelefonesCollection)
    {
        this.pessoaHasTelefonesCollection = pessoaHasTelefonesCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Telefones))
        {
            return false;
        }
        Telefones other = (Telefones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Telefones[ id=" + id + " ]";
    }
    
}
