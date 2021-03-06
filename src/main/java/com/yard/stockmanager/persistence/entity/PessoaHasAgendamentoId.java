package com.yard.stockmanager.persistence.entity;
// Generated 20/10/2019 19:04:16 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PessoaHasAgendamentoId generated by hbm2java
 */
@Embeddable
public class PessoaHasAgendamentoId  implements java.io.Serializable {


     private int pessoaId;
     private int agendamentoId;
     private int agendamentoFuncionarioId;

    public PessoaHasAgendamentoId() {
    }

    public PessoaHasAgendamentoId(int pessoaId, int agendamentoId, int agendamentoFuncionarioId) {
       this.pessoaId = pessoaId;
       this.agendamentoId = agendamentoId;
       this.agendamentoFuncionarioId = agendamentoFuncionarioId;
    }
   


    @Column(name="pessoa_id", nullable=false)
    public int getPessoaId() {
        return this.pessoaId;
    }
    
    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }


    @Column(name="agendamento_id", nullable=false)
    public int getAgendamentoId() {
        return this.agendamentoId;
    }
    
    public void setAgendamentoId(int agendamentoId) {
        this.agendamentoId = agendamentoId;
    }


    @Column(name="agendamento_funcionario_id", nullable=false)
    public int getAgendamentoFuncionarioId() {
        return this.agendamentoFuncionarioId;
    }
    
    public void setAgendamentoFuncionarioId(int agendamentoFuncionarioId) {
        this.agendamentoFuncionarioId = agendamentoFuncionarioId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PessoaHasAgendamentoId) ) return false;
		 PessoaHasAgendamentoId castOther = ( PessoaHasAgendamentoId ) other; 
         
		 return (this.getPessoaId()==castOther.getPessoaId())
 && (this.getAgendamentoId()==castOther.getAgendamentoId())
 && (this.getAgendamentoFuncionarioId()==castOther.getAgendamentoFuncionarioId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPessoaId();
         result = 37 * result + this.getAgendamentoId();
         result = 37 * result + this.getAgendamentoFuncionarioId();
         return result;
   }   


}


