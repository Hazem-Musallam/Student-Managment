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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@SequenceGenerator(name = "STUDENTS_SEQ", sequenceName = "STUDENTS_SEQ")
@Table(name = "Students")
public class Student {
	@Id
	@GeneratedValue(generator = "STUDENTS_SEQ", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	@CreatedDate
	private Instant created;

	@NotBlank
	private String nameArabic;
	@NotBlank
	private String nameEnglish;
	@NotBlank
	private String address;
	@NotBlank
	private String phoneNumber;
	@Column(unique = true)
	@NotBlank
	private String email;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
	private List<StudentCoursesRealtion> courses = new ArrayList<StudentCoursesRealtion>();

}
