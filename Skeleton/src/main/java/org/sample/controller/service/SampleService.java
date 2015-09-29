package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidTeamException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.CreateTeamForm;
import org.sample.model.Team;
import org.sample.model.User;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;
    public CreateTeamForm saveTeam(CreateTeamForm teamForm) throws InvalidTeamException;
	public Iterable<Team> getTeamList();
	public User getUserById(long id);
}