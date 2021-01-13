package it.alessandromodica.giococarte.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alessandromodica.giococarte.exceptions.ServiceException;
import it.alessandromodica.giococarte.interfaces.IMainService;
import it.alessandromodica.giococarte.model.po.GestioneUtenti;
import it.alessandromodica.product.persistence.exceptions.RepositoryException;
import it.alessandromodica.product.persistence.repo.BaseRepository;
import it.alessandromodica.product.persistence.searcher.YAFilterSearchApp;
import it.alessandromodica.product.persistence.searcher.YAFilterSerializeCriteria;

/**
 * Classe che rappresenta il modulo service principale. Viene esteso da tutti
 * gli altri service e implementa metodi comuni a tutti i servizi, come ad
 * esempio il recupero dell'utente corrente o il logging di accesso su una
 * tabella dedicata. Il metodo logAccesso non ha alcuna correlazione con gli
 * strumenti log4j, si limita a scrivere in modo personalizzato una tracciatura
 * di accesso utente, permettendo una estrema personalizzazione di tracciamento
 * attivita degli utenti.
 * 
 * @author Alessandro
 *
 */
@Service
@SuppressWarnings({"unchecked","rawtypes"})
public class MainService<T> implements IMainService<T> {

	@Autowired
	protected BaseRepository repository;

	private static final Logger log = Logger.getLogger(MainService.class);

	// XXX: questa classe rappresenta il contesto in cui sono implementati i
	// servizi usati dai componenti di consultazione e storage
	// consultazione storage seguono il pattern command query responsability
	// segregation
	// i servizi possono provvedere a dati di natura eterogenea (da database o
	// altri servizi esterni) oppure agire su dati stessi
	// i servizi sono trasversali e iniettati dove serve.

	/**
	 * Recupera le informazioni dell'utente corrente
	 */
	public GestioneUtenti getUtente(String nickname) throws ServiceException {
		YAFilterSearchApp search = new YAFilterSearchApp(GestioneUtenti.class);
		search.setNickname(nickname);
		try {

			GestioneUtenti result;
			try {
				result = (GestioneUtenti) repository//.setEntity(GestioneUtenti.class)
						.getSingleOrDefault(search.getSerialized());
				return result;

			} catch (RepositoryException e) {
				throw new ServiceException(e);
			}

		} catch (

		ServiceException ex) {
			log.error(ex);
			throw ex;
		}
	}

	@Override
	public List<T> search(YAFilterSerializeCriteria searcher) throws RepositoryException {
		// TODO Auto-generated method stub
		return repository.search(searcher);
	}

	@Override
	public int count(YAFilterSerializeCriteria searcher) throws RepositoryException{
		// TODO Auto-generated method stub
		return repository.getCount(searcher);
	}

	@Override
	public T getById(Object objId, Class<T> classEntity) throws RepositoryException {
		// TODO Auto-generated method stub
		return (T) repository.getById(objId, classEntity);
	}

	@Override
	public void add(T obj) throws RepositoryException {
		// TODO Auto-generated method stub
		repository.add(obj);
	}

}
