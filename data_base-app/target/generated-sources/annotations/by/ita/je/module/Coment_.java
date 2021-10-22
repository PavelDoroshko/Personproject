package by.ita.je.module;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Coment.class)
public abstract class Coment_ {

	public static volatile SingularAttribute<Coment, Long> id;
	public static volatile SingularAttribute<Coment, String> message;
	public static volatile SingularAttribute<Coment, Announcement> announcement;

	public static final String ID = "id";
	public static final String MESSAGE = "message";
	public static final String ANNOUNCEMENT = "announcement";

}

