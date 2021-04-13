package com.cepedi.curso.services;

import java.util.List;
import java.util.Optional;

import com.cepedi.curso.domain.Categoria;
import com.cepedi.curso.dto.CategoriaDTO;
import com.cepedi.curso.repositories.CategoriaRepository;
import com.cepedi.curso.services.exceptions.DataIntegrityException;
import com.cepedi.curso.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
    Categoria newObj= find(obj.getId());
    updateData(newObj,obj);
    return repo.save(newObj);
  }

  private void updateData(Categoria newObj, Categoria obj) {
    newObj.setNome(obj.getNome());
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
//paginação de categorias
  public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return repo.findAll(pageRequest);
  }
  public Categoria fromDTO(CategoriaDTO objDTO){
    return new Categoria(objDTO.getId(),objDTO.getNome());
  }

}
