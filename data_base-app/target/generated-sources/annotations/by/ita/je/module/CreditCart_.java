package by.ita.je.module;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCart.class)
public abstract class CreditCart_ {

	public static volatile SingularAttribute<CreditCart, Long> id;
	public static volatile SingularAttribute<CreditCart, Integer> cash;
	public static volatile SingularAttribute<CreditCart, User> user;

	public static final String ID = "id";
	public static final String CASH = "cash";
	public static final String USER = "user";

}

