package com.cepedi.curso.services;

import java.util.List;
import java.util.Optional;


import com.cepedi.curso.domain.Cliente;
import com.cepedi.curso.dto.ClienteDTO;
import com.cepedi.curso.repositories.ClienteRepository;
import com.cepedi.curso.services.exceptions.DataIntegrityException;
import com.cepedi.curso.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;

  public Cliente find(Integer id) {
    Optional<Cliente> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "cliente não encontrado cara! Id: " + id + ", Tipo: " + Cliente.class.getName()));

  }

  public Cliente update(Cliente obj) {
    Cliente newObj= find(obj.getId());
    updateData(newObj,obj);
    return repo.save(newObj);
  }

  private void updateData(Cliente newObj, Cliente obj) {
    newObj.setNome(obj.getNome());
    newObj.setEmail(obj.getEmail());
  }

  public void delete(Integer id) {
    find(id);
    try {

      repo.deleteById(id);
    }
    // quando for capturada a exceção do spring boot, lançaremos a nossa exceção
    // personalizada
    catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma Cliente que possua pedidos e endereços cadastrados");
    }
  }

  public List<Cliente> findAll() {
    return repo.findAll();
  }
//paginação de Clientes
  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return repo.findAll(pageRequest);
  }
  public Cliente fromDTO(ClienteDTO objDTO){
     return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
  }

}
