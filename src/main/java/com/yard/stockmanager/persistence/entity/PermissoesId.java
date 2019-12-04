package com.yard.stockmanager.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PermissoesId implements java.io.Serializable{

    private int funcionarioId;

    public PermissoesId() {
    }

    public PermissoesId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Column(name="funcionario_id", nullable=false)
    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissoesId)) return false;
        PermissoesId that = (PermissoesId) o;
        return getFuncionarioId() == that.getFuncionarioId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFuncionarioId());
    }
}
