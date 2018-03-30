package com.cromey.identity.webflux.validator;

import com.cromey.identity.webflux.model.Profile;
import com.cromey.identity.webflux.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileValidator(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Profile profile = (Profile) target;

        if(profile.getEmail() == null || profile.getEmail().isEmpty()){
            errors.reject("Email cannot be empty");
        }
    }
}
