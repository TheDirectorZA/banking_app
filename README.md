## File Structure

banking-app/
│
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── client/        // Client-side code
│   │   │   │   ├── ClientApp.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── BankClient.java
│   │   │   │   └── util/
│   │   │   │       └── ClientUtils.java
│   │   │   ├── server/        // Server-side code
│   │   │   │   ├── ServerApp.java
│   │   │   │   ├── api/
│   │   │   │   │   ├── AccountController.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── BankService.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Account.java
│   │   │   │   ├── repository/
│   │   │   │       ├── AccountRepository.java
│   └── resources/
│       ├── db/
│       │   ├── schema.sql
│       │   ├── data.sql


