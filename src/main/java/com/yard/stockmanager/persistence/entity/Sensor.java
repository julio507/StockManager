package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Sensor {

    private int id;
    private Estoque estoque;
    private String nome;
    private String mac;
    private String ip;
    private Set<Temperatura> temperatura = new HashSet<Temperatura>(0);

    public Sensor() {
    }

    public Sensor(int id, Estoque estoque, String nome, String mac, String ip) {
        this.id = id;
        this.estoque = estoque;
        this.nome = nome;
        this.mac = mac;
        this.ip = ip;
    }

    public Sensor(int id, Estoque estoque, String nome, String mac, String ip, Set<Temperatura> temperatura) {
        this.id = id;
        this.estoque = estoque;
        this.nome = nome;
        this.mac = mac;
        this.ip = ip;
        this.temperatura = temperatura;
    }

    @Id


    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="estoque_id", nullable=false, insertable=false, updatable=false)
    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @Column(name="nome", nullable=false, length=200)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name="mac", nullable=false, length=20)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Column(name="ip", nullable=false, length=20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="sensor")
    public Set<Temperatura> getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Set<Temperatura> temperatura) {
        this.temperatura = temperatura;
    }
}
