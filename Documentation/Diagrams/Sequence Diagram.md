sequenceDiagram
autonumber
Frontend->>Backend: Request
loop
    Backend->>Backend: Process request
end
Backend-->>Database: Send relevant queries
Database->>Backend: Receive response to queries
loop
    Backend->>Backend: Process response
end
Backend-->>Frontend: Return response
