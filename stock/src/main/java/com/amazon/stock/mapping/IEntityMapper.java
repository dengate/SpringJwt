package com.amazon.stock.mapping;

import java.util.List;

public interface IEntityMapper<R, E> {
    R toResource(E e);
    E toEntity(R r);

    List<R> toResource(List<E> eList);
    List<E> toEntity(List<R> rList);
}
