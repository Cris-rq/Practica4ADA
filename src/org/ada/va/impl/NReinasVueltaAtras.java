package org.ada.va.impl;

import java.util.Arrays;

/**
 * Implementación del algoritmo de las n-reinas con la técnica de vuelta atrás.
 * Tiene dos métodos de entrada a la funcionalidad proporcionados por la clase que implementa:
 * - init: devuelve la primera solución para el problema
 * - initTodas: devuelve todas las soluciones para el problema
 * El modificador verbose nos permite controlar el nivel de salida de la clase
 * @author Unknown
 */
public class NReinasVueltaAtras extends NReinasAbstract {
	
	/**
	 * Constructor de la clase.
	 * @param dimension tamaño del tablero y número de reinas a colocar
	 */
	public NReinasVueltaAtras(Integer dimension) {
		super(dimension); 
	}

	/**
	 * Calcula la solución para una etapa concreta.
	 * @param etapa etapa para la que queremos calcular la solución.
	 */
	public void vueltaAtras(int etapa) {
		if(etapa < this.getDimension()){
			for(int i = 0; (i < this.getDimension()) && (!this.isExito()); i++){
				this.solucion[etapa] = i;
				if(valido(etapa)){
					if(etapa < this.getDimension() -1){
						vueltaAtras(etapa + 1);
					} else {
						this.setExito(Boolean.TRUE);
					}
				}
			}
		}
	}
	
	/**
	 * Indica si la posible solución es válida para una etapa concreta
	 * @param  etapa etapa para la que queremos validar la solución
	 * @return si la solución es correcta
	 */
	protected Boolean valido(int etapa) { 
		for(int i = 0; i < etapa; i++){
			if((this.solucion[i] == this.solucion[etapa]) || 
			   (valAbs(this.solucion[i] - this.solucion[etapa]) == valAbs(i - etapa))){
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * Método que calcula todas las soluciones posibles para una etapa.
	 * 
	 * @param etapa etapa
	 */
	public void vaTodas(int etapa) {

		Integer[] auxArr;

		if(etapa < this.getDimension()){ // Las reinas colocadas son menores al número total de reinas por colocar
			for(int i = 0; i < this.getDimension(); i++){ // Bucle para probar TODOS los posibles estados de una misma posición del vector solución
				
				auxArr = Arrays.copyOf(this.getSolucion(), this.getSolucion().length);

				this.getSolucion()[etapa] = i; // Guardamos el POSIBLE estado (falta comprobar su validez)
				
				if(this.valido(etapa)){// Si el estado es valido entonces... (quiere decir que ninguna reina ataca a otra)
					if(etapa < this.getDimension() - 1){ // Si aún me quedan reinas por colocar entonces...
						vaTodas(etapa+1); // Busco la siguiente combinación considerando las reinas ya colocadas
					}
					else { // Si esta era la última reina que debía colocar entonces... (etapa = this.getDimension()-1)
						this.getSoluciones().addElement(this.getSolucion()); // Guardo la solución encontrada en mi vector de soluciones completas
					}

					this.setSolucion(auxArr);
				}
			}
		}
	}
}