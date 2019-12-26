/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.orm.test.metamodel.mapping.inheritance;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.testing.orm.junit.DomainModel;
import org.hibernate.testing.orm.junit.ServiceRegistry;
import org.hibernate.testing.orm.junit.SessionFactory;
import org.hibernate.testing.orm.junit.SessionFactoryScope;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Laurent Almeras
 */
@DomainModel(
		annotatedClasses = {
				OneToManyToInheritedTypeTest.SuperType.class,
				OneToManyToInheritedTypeTest.TypeA.class,
				OneToManyToInheritedTypeTest.TypeB.class,
				OneToManyToInheritedTypeTest.LinkedEntity.class
		}
)
@ServiceRegistry
@SessionFactory
public class OneToManyToInheritedTypeTest {
	@Test
	public void basicTest(SessionFactoryScope scope) {
	}

	@Entity(name = "SuperType")
	public static class SuperType {
		private Integer id;

		public SuperType() {
		}

		@Id
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}

	@Entity(name = "TypeA")
	public static class TypeA extends SuperType {
	}

	@Entity(name = "TypeB")
	public static class TypeB extends SuperType {
	}

	@Entity(name = "LinkedEntity")
	public static class LinkedEntity {
		private Integer id;

		@OneToMany
		private List<SuperType> superTypes;

		public LinkedEntity() {
		}

		@Id
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@OneToMany
		public List<SuperType> getSuperTypes() {
			return superTypes;
		}

		public void setSuperTypes(List<SuperType> superTypes) {
			this.superTypes = superTypes;
		}
	}
}
