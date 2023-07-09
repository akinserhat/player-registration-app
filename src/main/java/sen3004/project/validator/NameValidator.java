package sen3004.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sen3004.project.model.Player;

@Component
public class NameValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Player.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Player player = (Player)target;
		if(player.getPlayerFirstName().startsWith("Ğ") || player.getPlayerFirstName().startsWith("ğ")) {
			errors.rejectValue("playerFirstName", "firstName.custom.error");
		}
		if(player.getPlayerLastName().startsWith("Ğ") || player.getPlayerLastName().startsWith("ğ")) {
			errors.rejectValue("playerLastName", "lastName.custom.error");
		}
		
	}

}
