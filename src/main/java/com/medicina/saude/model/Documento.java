package com.medicina.saude.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@NoArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "dt_inclusao", nullable = false, updatable = false)
    private LocalDate dtInclusao;

    @Column(name = "dt_atualizacao", nullable = false)
    private LocalDate dtAtualizacao;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;


    public Documento(Long id, String documento, String descricao, LocalDate dtInclusao, LocalDate dtAtualizacao, Beneficiario beneficiario) {
        this.id = id;
        this.documento = documento;
        this.descricao = descricao;
        this.dtInclusao = dtInclusao;
        this.dtAtualizacao = dtAtualizacao;
        this.beneficiario = beneficiario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public LocalDate getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(LocalDate dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento documento1)) return false;
        return Objects.equals(getId(), documento1.getId()) && Objects.equals(getDocumento(), documento1.getDocumento()) && Objects.equals(getDescricao(), documento1.getDescricao()) && Objects.equals(getDtInclusao(), documento1.getDtInclusao()) && Objects.equals(getDtAtualizacao(), documento1.getDtAtualizacao()) && Objects.equals(getBeneficiario(), documento1.getBeneficiario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumento(), getDescricao(), getDtInclusao(), getDtAtualizacao(), getBeneficiario());
    }


    @PrePersist
    protected void onCreate() {
        LocalDate now = LocalDate.now();
        this.dtInclusao = now;
        this.dtAtualizacao = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dtAtualizacao = LocalDate.now();
    }


}
