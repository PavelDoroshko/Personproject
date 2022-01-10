package by.ita.je.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BestAnnouncement.class)
public abstract class BestAnnouncement_ {

	public static volatile SingularAttribute<BestAnnouncement, Long> id;
	public static volatile SingularAttribute<BestAnnouncement, User> user;
	public static volatile SingularAttribute<BestAnnouncement, Announcement> announcement;

	public static final String ID = "id";
	public static final String USER = "user";
	public static final String ANNOUNCEMENT = "announcement";

}

