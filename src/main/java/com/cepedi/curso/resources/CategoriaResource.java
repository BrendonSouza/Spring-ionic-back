package com.cepedi.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.cepedi.curso.domain.Categoria;
import com.cepedi.curso.dto.CategoriaDTO;
import com.cepedi.curso.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
// adicionamos um ending point, famosa url
@RequestMapping(value = "/categorias")

public class CategoriaResource {
  @Autowired
  private CategoriaService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  // PathVariable serve para passar o que vier na url pra dentro do obj
  public ResponseEntity<Categoria> listar(@PathVariable Integer id) {

    Categoria obj = service.find(id);
    return ResponseEntity.ok().body(obj);

  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
    Categoria obj = service.fromDTO(objDTO);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  // PathVariable serve para passar o que vier na url pra dentro do obj
  public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
    Categoria obj = service.fromDTO(objDTO);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(method = RequestMethod.GET)
  // PathVariable serve para passar o que vier na url pra dentro do obj
  public ResponseEntity<List<CategoriaDTO>> findAll() {

    List<Categoria> list = service.findAll();
    List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);

  }

//pagina????o com parametros opcionais
  @RequestMapping(value = "/page", method = RequestMethod.GET)
  public ResponseEntity<Page<CategoriaDTO>> findPage(
    @RequestParam(value="page",defaultValue = "0") Integer page,
    @RequestParam(value="linesPerPage",defaultValue = "24")Integer linesPerPage, 
    @RequestParam(value="orderBy",defaultValue = "nome") String orderBy, 
    @RequestParam(value="direction",defaultValue = "ASC")String direction) {

    Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
    Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
    return ResponseEntity.ok().body(listDTO);

  }

}
