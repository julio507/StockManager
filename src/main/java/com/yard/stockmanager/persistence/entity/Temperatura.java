package com.yard.stockmanager.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name="temperatura"
        ,catalog="stockmanager"
)
public class Temperatura {

    private int id;
    private Sensor sensor;
    private String temperatura;
    private String humidade;


    public Temperatura() {
    }

    public Temperatura(int id, Sensor sensor, String temperatura, String humidade) {
        this.id = id;
        this.sensor = sensor;
        this.temperatura = temperatura;
        this.humidade = humidade;
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
    @JoinColumn(name="sensor_id", nullable=false, insertable=false, updatable=false)
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Column(name="temperatura", nullable=false, length=45)
    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    @Column(name="humidade", nullable=false, length=45)
    public String getHumidade() {
        return humidade;
    }

    public void setHumidade(String humidade) {
        this.humidade = humidade;
    }
}
