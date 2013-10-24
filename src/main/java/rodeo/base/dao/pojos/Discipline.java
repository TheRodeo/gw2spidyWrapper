package rodeo.base.dao.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "discipline")
@NamedQueries({ @NamedQuery(name = Discipline.FIND_DISCIPLINE_BY_NAME, query = "select d from Discipline d where d.name = :name"),
		@NamedQuery(name = Discipline.GET_ALL_DISCIPLINE_NAMES, query = "select d.name from Discipline d") })
public class Discipline implements java.io.Serializable {

	public static final String FIND_DISCIPLINE_BY_NAME = "Discipline.findDisciplineByName";

	public static final String GET_ALL_DISCIPLINE_NAMES = "Discipline.allDisciplines";

	@Id
	private Double id;

	@Column
	private String name;

	public Discipline() {
	}

	public Discipline(final String name, final Double id) {
		this.name = name;
		this.id = id;
	}

	public Double getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(final Double id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Discipline [id=" + id + ", name=" + name + "]";
	}

}
