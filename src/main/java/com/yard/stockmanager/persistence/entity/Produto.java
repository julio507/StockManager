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
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id"),
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Produto.findByQuantidade", query = "SELECT p FROM Produto p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "Produto.findByCustoUnitario", query = "SELECT p FROM Produto p WHERE p.custoUnitario = :custoUnitario")
})
public class Produto implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "Quantidade")
    private String quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CustoUnitario")
    private BigDecimal custoUnitario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<EstoqueHasProduto> estoqueHasProdutoCollection;
    @JoinColumn(name = "Categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaid;
    @JoinColumn(name = "Departamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departamento departamentoid;
    @JoinColumn(name = "Marca_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Marca marcaid;
    @JoinColumn(name = "Unidade_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Unidade unidadeid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<NfeHasProduto> nfeHasProdutoCollection;

    public Produto()
    {
    }

    public Produto(Integer id)
    {
        this.id = id;
    }

    public Produto(Integer id, String nome, String descricao, String quantidade, BigDecimal custoUnitario)
    {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.custoUnitario = custoUnitario;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(String quantidade)
    {
        this.quantidade = quantidade;
    }

    public BigDecimal getCustoUnitario()
    {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario)
    {
        this.custoUnitario = custoUnitario;
    }

    @XmlTransient
    public Collection<EstoqueHasProduto> getEstoqueHasProdutoCollection()
    {
        return estoqueHasProdutoCollection;
    }

    public void setEstoqueHasProdutoCollection(Collection<EstoqueHasProduto> estoqueHasProdutoCollection)
    {
        this.estoqueHasProdutoCollection = estoqueHasProdutoCollection;
    }

    public Categoria getCategoriaid()
    {
        return categoriaid;
    }

    public void setCategoriaid(Categoria categoriaid)
    {
        this.categoriaid = categoriaid;
    }

    public Departamento getDepartamentoid()
    {
        return departamentoid;
    }

    public void setDepartamentoid(Departamento departamentoid)
    {
        this.departamentoid = departamentoid;
    }

    public Marca getMarcaid()
    {
        return marcaid;
    }

    public void setMarcaid(Marca marcaid)
    {
        this.marcaid = marcaid;
    }

    public Unidade getUnidadeid()
    {
        return unidadeid;
    }

    public void setUnidadeid(Unidade unidadeid)
    {
        this.unidadeid = unidadeid;
    }

    @XmlTransient
    public Collection<NfeHasProduto> getNfeHasProdutoCollection()
    {
        return nfeHasProdutoCollection;
    }

    public void setNfeHasProdutoCollection(Collection<NfeHasProduto> nfeHasProdutoCollection)
    {
        this.nfeHasProdutoCollection = nfeHasProdutoCollection;
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
        if (!(object instanceof Produto))
        {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.yard.stockmanager.persistence.entity.Produto[ id=" + id + " ]";
    }
    
}
