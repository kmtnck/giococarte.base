package it.alessandromodica.giococarte.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import it.alessandromodica.giococarte.exceptions.BusinessException;
import it.alessandromodica.giococarte.model.bo.AdapterGson;
import it.alessandromodica.giococarte.model.bo.InputData;
import it.alessandromodica.product.persistence.repo.BaseRepository;

/**
 * E' il componente principale dell'applicazione. E' contenuto l'intero set di
 * funzionalita' dell'applicazione. E' istanziabile da un contesto spring e da
 * esso e' possibile estrarre tutti i bean per poter fare testing e a sua volta
 * farli interagire in altri componenti.
 * 
 * Può essere fatto partire in qualsiasi contesto runtime java platform.
 * 
 * Eredita il MainContext il quale rappresenta lo scope comune di tutti i
 * controller che manipolano dati a seconda le esigenze.
 * 
 * La superclasse GoToBusiness è condivisa sia dal maincontext che dal
 * mainservice in cui sono raccolti i componenti repository utilizzati per
 * accedere alle risorse su un datastorage.
 * 
 * Questa gerarchia ad albero permette di avere un controllo efficente di tutte
 * le componenti distribuendo gli scope di funzionalità in base alle mutevoli
 * esigenze
 * 
 * @author Alessandro
 *
 */
@SpringBootApplication
@Component
@SuppressWarnings("rawtypes")
@ComponentScan(basePackages = "it.alessandromodica.product")
public class MainApplication  {

	public final static String packagePO = "it.alessandromodica.product.model.po";
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
	// XXX: classe principale e cardine di tutto il progetto
	// deve essere visto come il cappello di riferimento per tutta la gerarchia
	// del software
	@Autowired
	protected BaseRepository repository;

	private static final Logger log = Logger.getLogger(MainApplication.class);


	@SuppressWarnings("unchecked")
	public static Object getPojo(String rawData, Class clazz) {
		log.debug(rawData);
		Object result = (AdapterGson.GetInstance().fromJson(rawData, clazz));
		return result;
	}

	/**
	 * Handler di accesso a tutte le richieste esterne online. Ciascuna richiesta
	 * viene processata a partire da questo metodo
	 * 
	 * @param inputData
	 * @param remoteAddrs
	 * @param referer
	 * @param useragent
	 * @param rawUtente
	 * @param appcontext
	 * @return
	 * @throws BusinessException
	 */
	public Object processAction(InputData inputData, AppContext appcontext) throws BusinessException {
		try {

			Object dataToSendClient = accessCapabilities(inputData, appcontext);

			return dataToSendClient;

		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		}
	}

	/**
	 * Metodo di fattor comune delle capabilities supportate dall'applicazione. Per
	 * capabilities si intende qualsiasi funzionalità che dia esperienza utente sia
	 * in termini di produttività che di intrattenimento.
	 * 
	 * @param inputData
	 * @param currentUtente
	 * @param appcontext
	 * @return
	 * @throws BusinessException
	 */
	private Object accessCapabilities(InputData inputData, AppContext appcontext)
			throws BusinessException {

		
		Object dataToSendClient = null;

		switch (appcontext) {

		default:

			break;
		}

		return dataToSendClient;
	}

}
