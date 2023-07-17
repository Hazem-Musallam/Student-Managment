package com.jo.student.managment.api.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import io.micrometer.core.annotation.Counted;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "COURSES_SEQ", sequenceName = "COURSES_SEQ")
@Table(name = "Courses")
@Where(clause = "deleted=false")
public class Course {

	@Id
	@GeneratedValue(generator = "COURSES_SEQ", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	@CreatedDate
	private Instant created;

	private String courseName;
	@Column(unique = true)
	private String number;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
	private List<StudentCoursesRealtion> students = new ArrayList<StudentCoursesRealtion>();
	private boolean deleted;

}
