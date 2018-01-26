package br.com;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.input.FullPlayer;
import br.com.model.input.PageIn;
import br.com.model.misc.PageIterator;
import br.com.model.misc.PlayerCore;

@RestController
public class EnterData {

	@CrossOrigin
	@RequestMapping("/main/update")
	public void UpdateStoredPlayers() {

		PlayerCore plCore = new PlayerCore();
		PageIterator pages = plCore.initializeImport();

		while (pages.hasNext()) {
			PageIn pg = pages.next();

			for (FullPlayer fullPlay : pg.getItems())
				if (plCore.validPlayer(fullPlay))
					plCore.persistImportPlayer(fullPlay);

		}
	}
}
