package com.br.maplink.desafio_dev.vos;

/**
 * 
 * @author felipe.pelichero
 *
 * 7 de jun de 2017
 */
public class Address {
	
	private String nomeRua;
	private String nomeAvenida;
	private Integer numero;
	private String cidade;
	private String estado;
	
	public Address(AddressBuilder addressBuilder) {
		nomeRua = addressBuilder.nomeRua;
		nomeAvenida = addressBuilder.nomeAvenida;
		numero = addressBuilder.numero;
		cidade = addressBuilder.cidade;
		estado = addressBuilder.estado;
	}
	
	public static class AddressBuilder{
		private String nomeRua;
		private String nomeAvenida;
		private Integer numero;
		private String cidade;
		private String estado;
		
		public AddressBuilder nomeRua(String nomeRua){
			this.nomeRua = nomeRua;
			return this;
		}
		
		public AddressBuilder nomeAvenida(String nomeAvenida){
			this.nomeAvenida = nomeAvenida;
			return this;
		}
		
		public AddressBuilder cidade(String cidade){
			this.cidade = cidade;
			return this;
		}
		
		public AddressBuilder estado(String estado){
			this.estado = estado;
			return this;
		}
		
		public AddressBuilder numero(Integer numero){
			this.numero = numero;
			return this;
		}
		
		
		public Address build(){
			return new Address(this);
		}
	}
	
	public String getNomeRua() {
		return nomeRua;
	}

	public String getNomeAvenida() {
		return nomeAvenida;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}
	
	public String getFullAddress(){
		return new StringBuilder().append(this.nomeRua).append(",").append(this.numero).append(",").append(this.cidade).append(",").append(this.estado).toString();
	}
	
	@Override
	public String toString() {
		return "Address [nomeRua=" + nomeRua + ", nomeAvenida=" + nomeAvenida + ", numero=" + numero + ", cidade="
				+ cidade + ", estado=" + estado + "]";
	}
}