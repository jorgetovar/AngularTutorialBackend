package co.com.jetprogramming.mappers;

public interface EntityMapper<T, R> {

	T mapEntity(R dto);

	R mapDto(T entity);

}