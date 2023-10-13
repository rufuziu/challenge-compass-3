package br.demattos.iury.msproposals.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity(name = "approved")
public class Approved extends Proposal {}
