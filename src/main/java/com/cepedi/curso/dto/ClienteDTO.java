package com.cepedi.curso.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cepedi.curso.domain.Cliente;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private Integer id;
  @NotEmpty(message="Nome não pode ser vazio")
  @Length(min=5,max=120,message="O nome deve ter entre 5 e 120 char")
  private String nome;

  @NotEmpty(message="email não pode ser vazio")
  @Email(message="Email inválido")
  private String email;
  
  public ClienteDTO(){}

  public ClienteDTO(Cliente obj){
    id = obj.getId();
    nome = obj.getNome();
    email=obj.getEmail();
    
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  

  
}
