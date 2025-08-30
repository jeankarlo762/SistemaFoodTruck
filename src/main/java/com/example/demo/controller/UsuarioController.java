package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos().stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    // GET /usuarios/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(usuario));
    }

    // POST /usuarios
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.cadastrar(usuarioDTO.toEntity());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(usuario));
    }

    // PUT /usuarios/{id}
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Integer id,
                                                    @Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id); // garante que o ID do DTO é o mesmo do path
        Usuario usuarioAtualizado = usuarioService.atualizar(usuarioDTO.toEntity());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(usuarioAtualizado));
    }

    // DELETE /usuarios/{id}
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
