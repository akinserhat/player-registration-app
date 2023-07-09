package sen3004.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sen3004.project.model.Team;

@Repository
public class ProjectTeamRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Team> findAll() {
		return entityManager.createQuery("from Team", Team.class).getResultList();
	}
	
	public List<Team> findByPlayerId(long id) {
		return entityManager.createQuery("from Team where player.id = :id", Team.class)
				.setParameter("id", id).getResultList();
	}
	
	public void create(Team team) {
		entityManager.persist(team);
	}

	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Team.class, id));
	}

	public void deleteByPlayerId(long id) {
		entityManager.createQuery("delete from Team where player.id = :id")
				.setParameter("id", id).executeUpdate();
	}
}
