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
@Table(name = "endereco")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id"),
    @NamedQuery(name = "Endereco.findByEndereco", query = "SELECT e FROM Endereco e WHERE e.endereco = :endereco"),
    @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua"),
    @NamedQuery(name = "Endereco.findByNumero", query = "SELECT e FROM Endereco e WHERE e.numero = :numero"),
    @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep"),
    @NamedQuery(name = "Endereco.findByComplementos", query = "SELECT e FROM Endereco e WHERE e.complementos = :complementos")
})
public class Endereco implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "Rua")
    private String rua;
    @Basic(optional = false)
    @Column(name = "Numero")
    private String numero;
    @Column(name = "Bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "CEP")
    private String cep;
    @Column(name = "Complementos")
    private String complementos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoid")
    private Collection<Pessoa> pessoaCollection;
    @JoinColumn(name = "Cidade_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidadeid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoid")
    private Collection<Estoque> estoqueCollection;

    public Endereco()
    {
    }

    public Endereco(Integer id)
    {
        this.id = id;
    }

    public Endereco(Integer id, String endereco, String rua, String numero, String cep)
    {
        this.id = id;
        this.endereco = endereco;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getComplementos()
    {
        return complementos;
    }

    public void setComplementos(String complementos)
    {
        this.complementos = complementos;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection()
    {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection)
    {
        this.pessoaCollection = pessoaCollection;
    }

    public Cidade getCidadeid()
    {
        return cidadeid;
    }

    public void setCidadeid(Cidade cidadeid)
    {
        this.cidadeid = cidadeid;
    }

    @XmlTransient
    public Collection<Estoque> getEstoqueCollection()
    {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection)
    {
        this.estoqueCollection = estoqueCollection;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco))
        {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Endereco[ id=" + id + " ]";
    }
    
}
