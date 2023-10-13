package br.demattos.iury.msproposals.entities;

import br.demattos.iury.msproposals.enums.EResult;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "rejected")
public class Rejected extends Proposal {}
