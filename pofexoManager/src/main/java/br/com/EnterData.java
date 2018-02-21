package br.com;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.ctrl.PlayerCore;
import br.com.model.input.FullPlayer;
import br.com.model.input.PageIn;

@RestController
public class EnterData {
	
	@Autowired
	private PlayerCore plCore;
	
	private static final Logger log = LoggerFactory.getLogger(EnterData.class);

	@CrossOrigin
	@RequestMapping("/main/update")
	public void UpdateStoredPlayers() {
		log.info("inicio as: " + LocalDateTime.now());		
		RestTemplate rest = new RestTemplate();
//		PageIn pg = rest.getForObject("http://smartwaysolucoes.com/item.json", PageIn.class);
		PageIn pg = rest.getForObject("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=0", PageIn.class);
		
		while (pg.getPage() <= pg.getTotalPages()) {
			log.info("página: " + pg.getPage());

			for (FullPlayer fullPlay : pg.getItems()) {
				log.info(fullPlay.toString());

				if (plCore.validPlayer(fullPlay)) {
					plCore.persistImportPlayer(fullPlay);

					log.info("Gravou!");
				}
			}
			
		
			if ((pg.getPage() < pg.getTotalPages()))
				pg = rest.getForObject("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+ pg.getPage()+1, PageIn.class);
			else
				break;
		}
		
		log.info("término as: " + LocalDateTime.now());
	}
}
