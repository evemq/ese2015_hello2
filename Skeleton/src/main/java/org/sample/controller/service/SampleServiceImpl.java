package org.sample.controller.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.exceptions.InvalidTeamException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.CreateTeamForm;
import org.sample.model.Address;
import org.sample.model.User;
import org.sample.model.Team;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.UserDao;
import org.sample.model.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired    TeamDao teamDao;
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }


        Address address = new Address();
        address.setStreet("TestStreet-foo");
        
        
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setAddress(address);
        Team team = teamDao.findById(Long.parseLong(signupForm.getTeam()));
        user.setTeam(team);
        user = userDao.save(user);   // save object to DB
        
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        signupForm.setId(user.getId());

        return signupForm;

    }

	public CreateTeamForm saveTeam(CreateTeamForm teamForm) throws InvalidTeamException {		
		if(teamDao.findByTeamName(teamForm.getTeamName())!=null){
			throw new InvalidTeamException("Sorry, Teamname already exists.");   // throw exception
		}

        Team team = new Team();
        team.setTeamName(teamForm.getTeamName());
        team.setDate(new Timestamp(new java.util.Date().getTime()));
        
        team = teamDao.save(team);   // save object to DB
        
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        teamForm.setId(team.getId());

        return teamForm;

	}
	
	public Iterable<Team> getTeamList(){
		return teamDao.findAll();
	}
	public User getUserById(long id){
		return userDao.findById(id);
	}
}
