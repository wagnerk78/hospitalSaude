package com.medicina.saude.controller;


import com.medicina.saude.dto.BeneficiarioDTO;
import com.medicina.saude.dto.DocumentoDTO;
import com.medicina.saude.exceptions.EntidadeEmUsoException;
import com.medicina.saude.exceptions.EntidadeNaoEncontrada;
import com.medicina.saude.model.Beneficiario;
import com.medicina.saude.model.Documento;
import com.medicina.saude.repository.BeneficiarioRepository;
import com.medicina.saude.service.CadastroBeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private CadastroBeneficiarioService cadastroBeneficiarioService;

    @Operation(summary = "Lista todos os beneficiários",
            description = "Retorna uma lista de todos os beneficiários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de beneficiários retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BeneficiarioDTO.class)))
    @GetMapping
    public ResponseEntity<List<BeneficiarioDTO>> listar() {
        List<Beneficiario> beneficiarios = beneficiarioRepository.findAll();
        List<BeneficiarioDTO> beneficiarioDTOs = beneficiarios.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(beneficiarioDTOs);
    }


    @GetMapping("/{beneficiarioId}")
    public ResponseEntity<BeneficiarioDTO> buscar(@PathVariable("beneficiarioId") Long id) {
        Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);

        if (beneficiarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Beneficiario beneficiario = beneficiarioOptional.get();
        BeneficiarioDTO beneficiarioDTO = mapToDTO(beneficiario);

        return ResponseEntity.ok(beneficiarioDTO);
    }


    @PostMapping
    public ResponseEntity<Object> adicionar(@RequestBody Beneficiario beneficiario) {
        try {
            cadastroBeneficiarioService.salvar(beneficiario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{beneficiarioId}")
    public ResponseEntity<BeneficiarioDTO> atualizar(@PathVariable Long beneficiarioId, @RequestBody Beneficiario beneficiario) {
        Optional<Beneficiario> beneficiarioAtual = beneficiarioRepository.findById(beneficiarioId);

        if (beneficiarioAtual.isPresent()) {
            Beneficiario beneficiarioSalvo = beneficiarioAtual.get();


            if (beneficiario.getDtNascimento() != null) {
                beneficiarioSalvo.setDtNascimento(beneficiario.getDtNascimento());
            }
            if (beneficiario.getTelefone() != null) {
                beneficiarioSalvo.setTelefone(beneficiario.getTelefone());
            }
            if (beneficiario.getNome() != null) {
                beneficiarioSalvo.setNome(beneficiario.getNome());
            }

            beneficiarioSalvo = cadastroBeneficiarioService.salvar(beneficiarioSalvo);
            BeneficiarioDTO beneficiarioDTO = mapToDTO(beneficiarioSalvo);

            return ResponseEntity.ok(beneficiarioDTO);
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{beneficiarioId}/documentos")
    public ResponseEntity<BeneficiarioDTO> atualizarDocumentos(@PathVariable Long beneficiarioId, @RequestBody List<DocumentoDTO> documentosDTO) {
        Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(beneficiarioId);

        if (beneficiarioOptional.isPresent()) {
            Beneficiario beneficiario = beneficiarioOptional.get();

            List<Documento> documentos = documentosDTO.stream()
                    .map(dto -> {
                        Documento documento = new Documento();
                        documento.setDocumento(dto.getDocumento());
                        documento.setDescricao(dto.getDescricao());
                        documento.setBeneficiario(beneficiario);
                        return documento;
                    })
                    .toList();


            for (Documento doc : documentos) {
                boolean exists = false;
                for (Documento existingDoc : beneficiario.getDocumentos()) {
                    if (existingDoc.getDescricao().equals(doc.getDescricao())) {
                        existingDoc.setDocumento(doc.getDocumento());
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    beneficiario.getDocumentos().add(doc);
                }
            }

            cadastroBeneficiarioService.salvar(beneficiario);
            BeneficiarioDTO beneficiarioDTO = mapToDTO(beneficiario);

            return ResponseEntity.ok(beneficiarioDTO);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{beneficiarioId}")
    public ResponseEntity<Void> excluir(@PathVariable Long beneficiarioId) {
        try {
            Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(beneficiarioId);
            if (beneficiario.isEmpty()) {
                throw new EntidadeNaoEncontrada("Beneficiário não encontrado com o ID " + beneficiarioId);
            }
            cadastroBeneficiarioService.excluir(beneficiarioId);
            return ResponseEntity.ok().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private BeneficiarioDTO mapToDTO(Beneficiario beneficiario) {
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setNome(beneficiario.getNome());
        beneficiarioDTO.setTelefone(beneficiario.getTelefone());
        beneficiarioDTO.setDtNascimento(beneficiario.getDtNascimento());

        List<DocumentoDTO> documentosDTO = beneficiario.getDocumentos().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        beneficiarioDTO.setDocumentos(documentosDTO);
        return beneficiarioDTO;
    }

    private DocumentoDTO mapToDTO(Documento documento) {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setDocumento(documento.getDocumento());
        documentoDTO.setDescricao(documento.getDescricao());
        return documentoDTO;
    }
}
