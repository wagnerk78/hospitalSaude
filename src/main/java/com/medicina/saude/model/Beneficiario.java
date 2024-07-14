package com.medicina.saude.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Beneficiario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "dt_inclusao", nullable = false, updatable = false)
    private LocalDate dtInclusao;

    @Column(name = "dt_atualizacao", nullable = false)
    private LocalDate dtAtualizacao;

    public Beneficiario() {
    }


    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

    public Beneficiario(Long id, String nome, String telefone, LocalDate dtNascimento, LocalDate dtInclusao, LocalDate dtAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.dtInclusao = dtInclusao;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beneficiario that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getTelefone(), that.getTelefone()) && Objects.equals(getDtNascimento(), that.getDtNascimento()) && Objects.equals(getDtInclusao(), that.getDtInclusao()) && Objects.equals(getDtAtualizacao(), that.getDtAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTelefone(), getDtNascimento(), getDtInclusao(), getDtAtualizacao());
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

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
        for (Documento documento : documentos) {
            documento.setBeneficiario(this);
        }
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }
}
