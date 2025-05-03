@RestController
@RequestMapping("/notify")
public class NotificationController {
    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody NotificationRequest req) {
        // Stub for sending email
        return ResponseEntity.ok("Email sent to " + req.getTo());
    }
}
