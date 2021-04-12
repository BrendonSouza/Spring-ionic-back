package com.cepedi.curso.services;

import java.util.List;
import java.util.Optional;

import com.cepedi.curso.domain.Categoria;
import com.cepedi.curso.repositories.CategoriaRepository;
import com.cepedi.curso.services.exceptions.DataIntegrityException;
import com.cepedi.curso.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repo;

  public Categoria find(Integer id) {
    Optional<Categoria> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado cara! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }

  public Categoria insert(Categoria obj) {
    obj.setId(null);
    return repo.save(obj);
  }

  public Categoria update(Categoria obj) {
    find(obj.getId());
    return repo.save(obj);
  }

  public void delete(Integer id) {
    find(id);
    try {

      repo.deleteById(id);
    }
    // quando for capturada a exceção do spring boot, lançaremos a nossa exceção
    // personalizada
    catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possua produtos cadastrados");
    }
  }

  public List<Categoria> findAll() {
    return repo.findAll();
  }

}
