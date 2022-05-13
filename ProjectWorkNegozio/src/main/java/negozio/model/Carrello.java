package negozio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "carrello")
public class Carrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_carrello", nullable = true)
	private Integer codCarrello;

	@JoinColumn(name = "cod_utente")
	private Integer codUtente;

	@JoinColumn(name = "cod_prodotto")
	private Integer codProdotto;

	@Column(name = "quantita")
	private Integer quantita;

}
