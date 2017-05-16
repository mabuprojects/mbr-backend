package es.mabu.mr.product;

/*Si se añaden nuevos estados tener en cuenta a la hora de crear las orderlines en orderService
 * ya que ahi se hace una validación*/
public enum ProductState {
	VISIBLE, HIDDEN, HISTORICAL
}
