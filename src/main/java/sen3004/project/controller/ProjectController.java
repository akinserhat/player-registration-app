package sen3004.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sen3004.project.model.Player;
import sen3004.project.model.Team;
import sen3004.project.service.ProjectService;
import sen3004.project.validator.NameValidator;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@Autowired
	NameValidator nameValidator;
	
	@GetMapping({"/new-player", "new-player.html"})
	public ModelAndView addPlayer() {
		ModelAndView mv = new ModelAndView("new-player");
		mv.addObject("player", new Player());
		return mv;
	}
	
	@PostMapping("/add-player")
	public ModelAndView addPlayer(@Valid @ModelAttribute Player player, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("player", player);
		
		nameValidator.validate(player, result);
		
		if (result.hasErrors())
			mv.setViewName("new-player");
		else {
			mv.setViewName("player-list");
			service.createPlayer(player);
			mv.addObject("players", service.findAllPlayers());
		}
		return mv;
	}
	
	@GetMapping("/list-players")
	public ModelAndView listPlayers() {
		ModelAndView mv = new ModelAndView("player-list");
		mv.addObject("players", service.findAllPlayers());
		return mv;
	}
	
	@GetMapping("/find-player/{position}")
	public ModelAndView listByPosition(@PathVariable String position) {
		ModelAndView mv = new ModelAndView("player-list");
		mv.addObject("players", service.findPlayerByPosition(position));
		return mv;
	}
	
	@GetMapping("/player/{id}")
	public ModelAndView viewPlayer(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("view-player");
		Player player = service.findPlayerById(id);
		mv.addObject("player", player);
		mv.addObject("team", new Team(player));
		return mv;
	}
	
	@GetMapping("/player/delete/{id}")
	public ModelAndView deletePlayer(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("player-list");
		service.deletePlayer(id);
		mv.addObject("players", service.findAllPlayers());
		return mv;
	}
	
	@PostMapping("/player/team/add")
	public ModelAndView addTeamToPlayer(@Valid @ModelAttribute Team team, BindingResult result) {
		ModelAndView mv = new ModelAndView("view-player");
		Player player = service.findPlayerById(team.getPlayer().getId());
		if(result.hasErrors() == false) {
			service.createTeam(team);
			mv.addObject("team", new Team(player));
		} else 
			mv.addObject("team", team);
		
		mv.addObject("player", player);
		return mv;
	}

	@GetMapping("/player/team/delete/{pid}/{tid}")
	public ModelAndView deleteTeam(@PathVariable long pid, @PathVariable long tid) {
		ModelAndView mv = new ModelAndView("view-player");
		service.deleteTeam(tid);
		Player player = service.findPlayerById(pid);
		mv.addObject("player", player);
		mv.addObject("team", new Team(player));
		return mv;
	}
}
