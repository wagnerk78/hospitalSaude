package com.medicina.saude.service;


import com.medicina.saude.exceptions.EntidadeEmUsoException;
import com.medicina.saude.exceptions.EntidadeNaoEncontrada;
import com.medicina.saude.model.Beneficiario;
import com.medicina.saude.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroBeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public Beneficiario salvar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }


    public void excluir(Long beneficiarioId) {
        try {
            beneficiarioRepository.deleteById(beneficiarioId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe um beneficiário com o código %d", beneficiarioId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Beneficiário de código %d não pode ser removido, pois está em uso", beneficiarioId));
        }

    }
}
