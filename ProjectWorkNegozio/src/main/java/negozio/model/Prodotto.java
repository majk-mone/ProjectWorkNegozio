package negozio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "prodotto")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prodotto")
	private Integer codProdotto;

	@Column(name = "nome_prodotto")
	private String nome;

	@Column(name = "descrizione_prodotto")
	private String descrizione;

	@Column(name = "prezzo_prodotto")
	private double prezzo;

}
