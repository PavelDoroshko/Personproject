package by.ita.je.module;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Integer> pasword;
	public static volatile ListAttribute<User, Announcement> announcementList;
	public static volatile SingularAttribute<User, Integer> balance;
	public static volatile SingularAttribute<User, Long> user_id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile ListAttribute<User, BestAnnouncement> bestAnnouncements;
	public static volatile SingularAttribute<User, CreditCart> creditCart;

	public static final String PASWORD = "pasword";
	public static final String ANNOUNCEMENT_LIST = "announcementList";
	public static final String BALANCE = "balance";
	public static final String USER_ID = "user_id";
	public static final String LOGIN = "login";
	public static final String BEST_ANNOUNCEMENTS = "bestAnnouncements";
	public static final String CREDIT_CART = "creditCart";

}

