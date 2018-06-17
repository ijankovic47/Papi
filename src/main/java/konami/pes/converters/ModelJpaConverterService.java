package konami.pes.converters;

public interface ModelJpaConverterService<T1, T2> {

	T2 convertJpaToModel(T1 jpa);
	T1 convertModelToJpa(T2 model);
}
