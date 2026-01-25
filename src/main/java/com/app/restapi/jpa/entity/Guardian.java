package com.app.restapi.jpa.entity;

import java.util.Objects;

import com.app.restapi.model.Relation;

import jakarta.persistence.*;

@Entity
public class Guardian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String phone;

	@Enumerated(EnumType.STRING)
	private Relation relation;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Guardian() {}
	public Guardian(String name, String email, String phone, Relation relation) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.relation = relation;
	}
	public Guardian(Long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public Guardian setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Guardian setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Guardian setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Guardian setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Relation getRelation() {
		return relation;
	}

	public Guardian setRelation(Relation relation) {
		this.relation = relation;
		return this;
	}

	public Student getStudent() {
		return student;
	}

	public Guardian setStudent(Student student) {
		this.student = student;
		return this;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Guardian setEmployee(Employee employee) {
		this.employee = employee;
		return this;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return Objects.equals(id, guardian.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
