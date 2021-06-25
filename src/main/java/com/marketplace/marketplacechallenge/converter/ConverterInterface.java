package com.marketplace.marketplacechallenge.converter;

/**
 * Interface que define o metódo para os conversores.
 * @param <E> entidade a ser convertida
 * @param <D> Dto para conversão
 */
public interface ConverterInterface<E, D> {

    E toEntity(D d);

    public D toDto(E e);
}
