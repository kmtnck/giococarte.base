package it.alessandromodica.giococarte.interfaces;

import java.util.List;

import it.alessandromodica.giococarte.exceptions.ServiceException;
import it.alessandromodica.giococarte.model.po.GestioneUtenti;
import it.alessandromodica.product.persistence.exceptions.RepositoryException;
import it.alessandromodica.product.persistence.searcher.YAFilterSerializeCriteria;

public interface IMainService<T> {

	List<T> search(YAFilterSerializeCriteria searcher) throws RepositoryException;

	int count(YAFilterSerializeCriteria searcher) throws RepositoryException;
	
	public T getById(Object objId, Class<T> classEntity) throws RepositoryException;

	public void add(T obj) throws RepositoryException;
}
