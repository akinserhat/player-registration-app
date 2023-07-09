package sen3004.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sen3004.project.dao.ProjectPlayerRepository;
import sen3004.project.dao.ProjectTeamRepository;
import sen3004.project.model.Player;
import sen3004.project.model.Team;

@Service
@Transactional
public class ProjectService {
	
	@Autowired
	private ProjectPlayerRepository playerRepository;
	
	@Autowired
	private ProjectTeamRepository teamRepository;
	
	public List<Player> findAllPlayers() {
		return playerRepository.findAll();
	}
	
	public Player findPlayerById(long id) {
		return playerRepository.findById(id);
	}
	
	public List<Player> findPlayerByPosition(String position) {
		return playerRepository.findByPosition(position);
	}

	public void createPlayer(Player player) {
		playerRepository.create(player);
	}

	public void createTeam(Team team) {

		teamRepository.create(team);
	}

	public void deletePlayer(long id) {
		teamRepository.deleteByPlayerId(id);
		playerRepository.delete(id);
	}

	public void deleteTeam(long id) {
		teamRepository.delete(id);
	}
}
