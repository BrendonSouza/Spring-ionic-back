package com.cepedi.curso.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double disconto;
  private Integer quantidade;
  private Double preco;

  public ItemPedido() {

  }

  public ItemPedido(Pedido pedido, Produto produto, Double disconto, Integer quantidade, Double preco) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.disconto = disconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }
public double getSubTotal(){
  return (preco-disconto)*quantidade;
}
  public Double getDisconto() {
    return disconto;
  }

  public void setDisconto(Double disconto) {
    this.disconto = disconto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public ItemPedidoPK getId() {
    return id;
  }

  public void setId(ItemPedidoPK id) {
    this.id = id;
  }

  public Produto getProduto() {
    return id.getProduto();
  }
  public void setProduto(Produto produto){
    id.setProduto(produto);
  }

  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  public void setPedido(Pedido pedido){
    id.setPedido(pedido);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemPedido other = (ItemPedido) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
