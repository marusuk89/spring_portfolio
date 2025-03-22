package com.example.course_registration.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.example.course_registration.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tutors")
public class Tutor
        extends BaseEntity
{
    @Column(nullable = false)
    private String name;
}
