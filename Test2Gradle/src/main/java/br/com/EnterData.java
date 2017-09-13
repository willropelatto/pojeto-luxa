package br.com;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.in.model.Attributes;
import br.com.in.model.FullPlayer;
import br.com.in.model.League;
import br.com.in.model.PageIn;
import br.com.model.LeagueTite;
import br.com.model.LeagueTiteRepository;
import br.com.model.PlayerAttributes;
import br.com.model.PlayerAttributesRepository;
import br.com.model.PlayerTite;
import br.com.model.PlayerTiteRepository;

@RestController
public class EnterData {

	@Autowired
	private PlayerTiteRepository playerDao;
	@Autowired
	private LeagueTiteRepository leagueDao;
	@Autowired
	private PlayerAttributesRepository attributesDao;

	private PlayerTite convertPlayer(FullPlayer fullpl) {
		PlayerTite player = new PlayerTite();
		Integer idLeague = new Integer(fullpl.getLeague().getId());
		player.setId(0);
		player.setName(fullpl.getName());
		player.setPosition(fullpl.getPosition());
		player.setBaseId(fullpl.getBaseId());
		player.setRating(fullpl.getRating());
		player.setIdLeague(idLeague);
		player.setOriginalId(fullpl.getId());
		player.setHasBid(false);		
		
		if (fullpl.getClub() != null) {
			player.setClubName(fullpl.getClub().getName());
		} else {
			player.setClubName("Undefined");
		}
		
		return player;
	}

	@CrossOrigin
	@RequestMapping("/main/update")
	public void UpdateStoredPlayers() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=0");
		String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		Gson gson = new Gson();
		PageIn pg = gson.fromJson(json, PageIn.class);
		PlayerTite player;
		PlayerTite plcomp;
		FullPlayer fullPlay;
		List<PlayerAttributes> attributes;
		ArrayList<League> leagues = new ArrayList<League>();

		while ((pg != null) && (pg.getTotalPages() >= pg.getPage())) {
			for (int i = 0; i < pg.getItems().length; i++) {
				fullPlay = pg.getItems()[i];

				if ((fullPlay.getPlayerType().equals("rare") || fullPlay.getPlayerType().equals("standard"))
						&& !fullPlay.getColor().isEmpty()) {
					League league = fullPlay.getLeague();

					if (!leagues.contains(league)) {
						if (leagueDao.findOneByOriginalId(league.getId()) == null) {
							leagueDao.save(convertLeague(league));
						}
						leagues.add(league);
					}

					player = convertPlayer(fullPlay);
					plcomp = playerDao.findOneByBaseId(player.getBaseId());

					if (savePlayer(player, plcomp)) {
						System.out.println(player);
						attributes = (convertAttributes(fullPlay.getAttributes()));
						attributesDao.save(attributes);
						player.setAttributes(attributes);
						playerDao.save(player);
					}
				}

			}

			int pageToGo = pg.getPage() + 1;

			if (pg.getPage() < pg.getTotalPages()) {
				System.out.println("Indo para a pagina:" + pageToGo);
				try {
					target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=" + pageToGo);
					json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
					gson = new Gson();
					pg = gson.fromJson(json, PageIn.class);
				} catch (Exception e) {
					System.out.println("ERRO " + e.getMessage());
				}

			}
		}
	}

	private boolean savePlayer(PlayerTite player, PlayerTite plbase) {

		if (plbase == null) {
			return true;
		}

		Integer ori, dst;

		ori = Integer.parseInt(player.getOriginalId());
		dst = Integer.parseInt(plbase.getOriginalId());

		if (Integer.compare(ori, dst) > 0) {
			player.setId(plbase.getId());
			return true;
		}

		return false;
	}

	private LeagueTite convertLeague(League league) {
		LeagueTite entity = new LeagueTite();
		entity.setId(0);
		entity.setAbbrName(league.getAbbrName());
		entity.setImgUrl(league.getImgUrl());
		entity.setName(league.getName());
		entity.setOriginalId(league.getId());

		return entity;
	}

	private List<PlayerAttributes> convertAttributes(Attributes[] attributes) {
		List<PlayerAttributes> entity = new ArrayList<PlayerAttributes>();
		for (Attributes attributes2 : attributes) {
			PlayerAttributes plAtt = new PlayerAttributes();
			plAtt.setId(0);
			String name = attributes2.getName();
			name.replaceAll("fut.attribute.", "");
			plAtt.setName(name);
			plAtt.setValue(attributes2.getValue());
			entity.add(plAtt);
		}
		return entity;
	}

}
