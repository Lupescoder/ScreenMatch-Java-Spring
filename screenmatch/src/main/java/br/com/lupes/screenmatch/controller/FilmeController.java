package br.com.lupes.screenmatch.controller;

import br.com.lupes.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.lupes.screenmatch.domain.filme.DadosEditarFilme;
import br.com.lupes.screenmatch.domain.filme.Filme;
import br.com.lupes.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping("/formulario")
    public String formulario(Long id, Model model) {
        if(id != null){
            var filme = filmeRepository.getReferenceById(id);
            model.addAttribute("filme",filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String listagem(Model model) {
        model.addAttribute("lista",filmeRepository.findAll());

        return "filmes/listagem";
    }
    @PostMapping
    @Transactional
    public String adicionarFilme(DadosCadastroFilme dados) {
        Filme filme = new Filme(dados);
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String editarFilme(DadosEditarFilme dados) {
        var filme = filmeRepository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String removerFilme(Long id){
        filmeRepository.deleteById(id);

        return "redirect:/filmes";
    }


}
