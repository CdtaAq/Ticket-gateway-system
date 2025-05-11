# Ticketing Gateway System - Project Report & Demo Script

## 📦 Project Overview
The Ticketing Gateway System is a role-based ticket management system designed to streamline IT support requests. The system includes user authentication, ticket lifecycle management, automated notifications, and ActiveMQ messaging.

### 🔥 Objective:
- Enable users to create, track, and resolve support tickets.
- Provide managers with ticket approval/rejection capabilities.
- Notify users via email at key ticket stages (creation, approval, resolution).
- Automate ticket closures after a specified time period.

---

## ✅ Feature Breakdown
1. **User Authentication:**
   - Role-based login for USER, MANAGER, and ADMIN.
   - Redirection to appropriate dashboards.

2. **Ticket Lifecycle Management:**
   - Ticket creation with attachment upload.
   - Approval/Rejection by MANAGER.
   - Resolution and PDF generation by ADMIN.
   - Reopen/Close functionality for unresolved tickets.

3. **Notifications:**
   - Email notifications for ticket creation, approval, and resolution.
   - Automated CRON job for unresolved tickets.

4. **ActiveMQ Messaging:**
   - Inter-microservice communication for ticket status updates.

---

## 🚀 Demonstration Steps
1. **Login as USER:**
   - Navigate to `/login` and enter credentials.
   - Create a new ticket via `/tickets/create`.

2. **Login as MANAGER:**
   - Review newly created tickets via `/tickets/pending`.
   - Approve or reject tickets.

3. **Login as ADMIN:**
   - View assigned tickets and update resolution status.
   - Generate and send PDF resolution reports.

4. **Notification Demonstration:**
   - Check email inbox for notifications at each ticket stage.

5. **Error Handling & Logs:**
   - View error logs in the console for failed API calls.

---

## 📷 Screenshots (Placeholders):
- Login Page (Insert Screenshot)
- Dashboard View (Insert Screenshot)
- Ticket Creation Form (Insert Screenshot)
- Email Notification (Insert Screenshot)

