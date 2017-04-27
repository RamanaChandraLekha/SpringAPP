package com.full;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class HelperClass {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public boolean userRegistration(User users) {

		boolean mailIdFound = false;
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);

		for (Entity user : pq.asIterable()) {

			String mailId = user.getProperty("Mail").toString();
			if (users.getEmail().equals(mailId))
				mailIdFound = true;

		}

		if (mailIdFound == false) {
			Entity newUser = new Entity("User");

			newUser.setProperty("Name", users.getUsername());
			newUser.setProperty("Mail", users.getEmail());
			newUser.setProperty("password", users.getPassword());
			newUser.setProperty("CompanyName", users.getCompanyName());
			newUser.setProperty("Designation", users.getDesignation());
			newUser.setProperty("experience", users.getExp());
			newUser.setProperty("city", users.getCity());
			newUser.setProperty("Stream", users.getStream());

			datastore.put(newUser);

		}

		return mailIdFound;

	}

	public boolean verifyinDetails(String email, String pwd) {
		boolean flag = false;

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);

		for (Entity user : pq.asIterable()) {

			String mailId = user.getProperty("Mail").toString();
			String password = user.getProperty("password").toString();

			if ((email.equals(mailId)) && (pwd.equals(password)))

			{

				flag = true;
			}

		}
		return flag;

	}

}
