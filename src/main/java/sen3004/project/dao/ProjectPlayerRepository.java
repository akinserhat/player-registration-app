package sen3004.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sen3004.project.model.Player;


@Repository
public class ProjectPlayerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Player> findAll() {
		return entityManager.createQuery("from Player", Player.class).getResultList();
	}
	
	public Player findById(long id) {
		return entityManager.find(Player.class, id);
	}
	
	public List<Player> findByPosition(String position) {
		return entityManager.createQuery("from Player where position = :position", Player.class)
				.setParameter("position", position).getResultList();
	}
	
	public void create(Player player) {
		entityManager.persist(player);
	}
	
	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Player.class, id));
	}
	
	public void update(Player player) {
		entityManager.merge(player);
	}
}
