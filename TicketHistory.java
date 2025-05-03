@Entity
public class TicketHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private LocalDateTime timestamp;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Employee performedBy;
}
