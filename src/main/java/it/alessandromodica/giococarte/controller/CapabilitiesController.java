package it.alessandromodica.giococarte.controller;

import java.util.EnumSet;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.alessandromodica.giococarte.app.AppContext;
import it.alessandromodica.giococarte.app.MainApplication;
import it.alessandromodica.giococarte.app.RequestVariable;
import it.alessandromodica.giococarte.exceptions.BusinessException;
import it.alessandromodica.giococarte.model.bo.InputData;
import it.alessandromodica.giococarte.model.bo.MagicString;

@RestController
@RequestMapping(value = "/capabilities")
public class CapabilitiesController {

	private static final Logger log = Logger.getLogger(CapabilitiesController.class);

	@Autowired
	private MainApplication mainApp;

	@RequestMapping(value = "/context", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Object capabilities(@RequestParam Map<String, String> info, @RequestHeader Map<String, String> headers)
			throws BusinessException {

		String inforemote = MainController.getInfoRemote(headers);

		AppContext context = null;
		String contesto = info.get(MagicString.ProtocolloApp.P_CONTEXT);
		try {
			context = AppContext.valueOf(contesto);

		} catch (Exception ex) {
			String msg = "Attenzione il contesto " + contesto + " ricevuto in input non e' valido ";
			log.warn(msg);
			throw new BusinessException(msg, ex);
		}

		InputData inputData = new InputData();

		EnumSet.allOf(RequestVariable.class)
				.forEach(currentEnum -> inputData.getMapRequestData().put(currentEnum, info.get(currentEnum.name())));

		return mainApp.processAction(inputData, context);

	}
}
